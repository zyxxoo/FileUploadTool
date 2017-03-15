package com.blueware.client;

import com.blueware.domain.Head;
import com.blueware.utils.ByteUtils;
import com.blueware.utils.ClosableUtils;
import com.blueware.utils.FileUtils;
import com.blueware.utils.Params;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhangyan on 2017/3/14.
 */
public class Main {
    public static String file;
    public static String host;
    public static int port;
    public static void main(String[] argvs) throws Exception {
        parase(argvs);

        commit();
    }

    public static class View{
        public void showLn(String s){
            System.out.println(s);
        }
        public void show(String s){
            System.out.print("\r"+s);
        }
    }

    private static void commit() throws Exception {
      File target_file = new File(file);
      View view = new View();
      List<String> files = new LinkedList<>();
      if (!target_file.exists()){
            view.showLn(String.format("file: %s not exists!", file));
            System.exit(1);
      }

      files.addAll(FileUtils.listFile(file));
      int total = files.size();
      Socket socket = new Socket(host, port);
      OutputStream outputStream = socket.getOutputStream();
      try {
          while (files.size() > 0) {
              view.showLn(String.format("left: %10d/%d", total - files.size(), total));
              upload(files.remove(0), outputStream);
          }

          sendEOF(outputStream);
          view.showLn(String.format("left: %10d/%d  success!", total - files.size(), total));
      }catch (Exception e){
          e.printStackTrace();
      }finally {
          ClosableUtils.close(outputStream);
          ClosableUtils.close(socket);
      }
    }

    private static void sendEOF(OutputStream outputStream) throws IOException {
        Head head = new Head();
        head.setType(Head.TYPE_EOF);
        sendHead(head, outputStream);
    }

    private static void upload(String remove, OutputStream outputStream) throws Exception {
        File file1 = new File(remove);
        Head head = new Head();
        head.setDataLength(file1.length());
        String relativeName = getRelativeName(file, remove);
        head.setNames(relativeName);
        head.setType(Head.TYPE_RECV);
        head.setNamelength(relativeName.getBytes().length);

        sendHead(head, outputStream);
        sendData(file1, outputStream);
    }

    private static void sendData(File file, OutputStream outputStream) throws Exception {
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(file);
            byte[] buff = new byte[1024 * 10];
            int len = 0;
            while ((len = fis.read(buff)) != -1){
                outputStream.write(buff, 0, len);
            }
        }finally {
            ClosableUtils.close(fis);
        }
    }

    private static void sendHead(Head head, OutputStream outputStream) throws IOException {
        outputStream.write(ByteUtils.tobyte(head.getMargic()));
        outputStream.write(ByteUtils.tobyte(head.getType()));
        outputStream.write(ByteUtils.tobyte(head.getVersion()));
        outputStream.write(ByteUtils.tobyte(head.getNamelength()));
        outputStream.write(ByteUtils.tobyte(head.getDataLength()));
        if (head.getNamelength() > 0) {
            outputStream.write(head.getNames().getBytes());
        }
    }

    public static String getRelativeName(String rootDir, String remove) {
        File f = new File(rootDir);
        if (f.isDirectory()){
            String pn = f.getName();
            int index = remove.indexOf(pn);
            return remove.substring(index);
        }else{
            return f.getName();
        }
    }

    public static void parase(String[] argvs) {
        Params params = new Params(argvs);
        do {
            String p = params.shift();
            if (p.isEmpty()){
                break;
            }

            if (p.startsWith("-f=")){
                file = p.split("=")[1].trim();
            }else if (p.startsWith("-h=")){
                host = p.split("=")[1].trim();
                int index = host.indexOf(':');
                if (index > 0 && port == 0){
                    port = Integer.parseInt(host.substring(index + 1).trim());
                }
                host = host.substring(0, index);
            }else if (p.startsWith("-p=")){
                port = Integer.parseInt(p.split("=")[1].trim());
            }
        }while (true);
    }
}
