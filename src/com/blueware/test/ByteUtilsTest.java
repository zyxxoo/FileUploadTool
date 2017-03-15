package com.blueware.test;

import com.blueware.utils.ByteUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by zhangyan on 2017/3/14.
 */
public class ByteUtilsTest {
    @Test
    public void test1(){
        byte[] tobyte = ByteUtils.tobyte(5522l);
        long actual1 = ByteUtils.toLong(tobyte, 0);
        assertEquals(5522l, actual1);
        byte[] tobyte1 = ByteUtils.tobyte(2233);
        int actual = ByteUtils.toInteger(tobyte1, 0);
        assertEquals(2233, actual);
    }
}
