package com.blueware.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeoutException;

/**
 * Created by zhangyan on 2017/3/14.
 */
public class ReaderStreamUtils {

    public static void read(byte[] buff, int start, int len, int timeout, InputStream in) throws IOException, TimeoutException {
        int readlen = 0;
        long startTime = System.currentTimeMillis();
        while (len > 0){

            readlen = in.read(buff, start, len);
            len -= readlen;
            start += readlen;

            if (len > 0 && System.currentTimeMillis() - startTime > timeout){
                throw new TimeoutException(String.format("timeout: %d", timeout));
            }
        }
    }
}
