package com.blueware.utils;

/**
 * Created by zhangyan on 2017/3/14.
 */
public class ByteUtils {

    public static int toInteger(byte[] buff, int start){
        int ret = 0;
        if (buff != null && buff.length >= 4 && start + 4 <= buff.length){
            ret = ((buff[start] & 0xFF) << 24) | ((buff[start + 1] & 0xFF) << 16) | ((buff[start + 2] & 0xFF) << 8) | (buff[start + 3] & 0xFF);
        }
        return ret;
    }

    public static byte[] tobyte(int i){
        byte[] ret = new byte[4];

        ret[0] = (byte) (i >> 24 & 0x000000FF);
        ret[1] = (byte) (i >> 16 & 0x000000FF);
        ret[2] = (byte) (i >> 8 & 0x000000FF);
        ret[3] = (byte) (i & 0x000000FF);

        return ret;
    }

    public static long toLong(byte[] buff, int start) {
        long ret = 0;
        if (buff != null && start + 8 <= buff.length){
            ret = ((buff[start] & 0xFFl) << 56) | ((buff[start + 1] & 0xFFl) << 48) | ((buff[start + 2] & 0xFFl) << 40) | ((buff[start + 3] & 0xFFl) << 32)
                | ((buff[start + 4] & 0xFFl) << 24) | ((buff[start + 5] & 0xFFl) << 16) | ((buff[start + 6] & 0xFFl) << 8) | (buff[start + 7] & 0xFFl);
        }
        return ret;
    }

    public static byte[] tobyte(long i){
        byte[] ret = new byte[8];

        ret[0] = (byte) (i >> 56 & 0x000000FF);
        ret[1] = (byte) (i >> 48 & 0x000000FF);
        ret[2] = (byte) (i >> 40 & 0x000000FF);
        ret[3] = (byte) (i >> 32 & 0x000000FF);
        ret[4] = (byte) (i >> 24 & 0x000000FF);
        ret[5] = (byte) (i >> 16 & 0x000000FF);
        ret[6] = (byte) (i >> 8 & 0x000000FF);
        ret[7] = (byte) (i & 0x000000FF);

        return ret;
    }
}
