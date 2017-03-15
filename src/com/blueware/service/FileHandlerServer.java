package com.blueware.service;

import com.blueware.client.Main;
import com.blueware.domain.Data;
import com.blueware.domain.Head;
import com.blueware.utils.ClosableUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by zhangyan on 2017/3/14.
 */
public class FileHandlerServer {
    String filePath;
    int port;
    Executor executor;
    Main.View view;
    public FileHandlerServer(String filePath, String port) {
        this.filePath = filePath;
        this.port = Integer.parseInt(port);
        executor =  Executors.newFixedThreadPool(5);
        view = new Main.View();
    }


    public void start() throws Throwable{
        doStart();
    }

    private void doStart() throws IOException {
        ServerSocket serverSocket = new ServerSocket(this.port);
        while (true){
            final Socket socket = serverSocket.accept();
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream inputStream = socket.getInputStream();
                        while (true) {
                            Data data = new Data(inputStream);
                            view.showLn(data.getHead().toString());
                            if (data.getHead().getType() == Head.TYPE_EOF){
                                return;
                            }else if (data.getHead().getType() == Head.TYPE_RECV) {
                                if (data.getHead().getNamelength() > 0) {
                                    data.storeFile(String.format("%s%s%s", filePath, File.separator, data.getHead().getNames()), inputStream);
                                }else{
                                    throw new IllegalArgumentException("Head names not be empty!");
                                }
                            }
                        }
                    }catch (Throwable t){
                        t.printStackTrace();
                        OutputStream out = null;
                        try {
                            out = socket.getOutputStream();
                        }catch (Throwable tt){
                            if (out != null){
                                try {
                                    out.write(t.getCause().toString().getBytes());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }finally {
                            ClosableUtils.close(out);
                        }
                    }finally {
                       ClosableUtils.close(socket);
                    }
                }
            });
        }
    }

}
