package com.blueware.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by zhangyan on 2017/3/14.
 */
public class ClosableUtils {

    public static void close(Closeable closeable){
        if (closeable != null){
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
