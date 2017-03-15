package com.blueware.domain;

import com.blueware.service.ReaderStreamUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zhangyan on 2017/3/14.
 */
public class Data {
    Head head;

    public Data(Head head) {
        this.head = head;
    }

    public Data(InputStream inputStream) throws Exception{
        readHead(inputStream);
    }

    public void storeFile(String file, InputStream inputStream) throws IOException {
        File target_file = new File(file);
        target_file.deleteOnExit();
        target_file.getParentFile().mkdirs();
        target_file.createNewFile();
        byte[] buff = new byte[1024 * 100];
        int len = 0;
        long recv = 0;
        long left = head.dataLength - recv;
        FileOutputStream fileOutputStream = new FileOutputStream(target_file);
        try {
            if (left > 0) {
                while ((len = inputStream.read(buff, 0, (int) (buff.length < left ? buff.length : left))) != -1) {
                    fileOutputStream.write(buff, 0, len);
                    recv += len;
                    left = head.dataLength - recv;
                    if (left <= 0) break;
                }
            }
        }finally {
            fileOutputStream.close();
            if (left > 0){
                target_file.deleteOnExit();
                throw new IllegalArgumentException(String.format("file:%s recv error", file));
            }
        }

    }

    public Head getHead(){
        return this.head;
    }

    private void readHead(InputStream inputStream) throws Exception {
        byte[] headerbuff = new byte[24];
        ReaderStreamUtils.read(headerbuff, 0, 24, 5000, inputStream);
        head = Head.parse(headerbuff);
        if (head.namelength > 0){
            byte[] namebyte = new byte[head.namelength];
            ReaderStreamUtils.read(namebyte, 0, head.namelength, 5000, inputStream);
            head.names = new String(namebyte);
        }
    }
}
