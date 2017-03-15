package com.blueware.test;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by zhangyan on 2017/3/14.
 */
public class Main {

    @Test
    public void test1(){
        com.blueware.client.Main.parase(new String[]{"-f=filename", "-h=127.0.0.0:8080"});
        assertEquals(com.blueware.client.Main.file, "filename");
        assertEquals(com.blueware.client.Main.host, "127.0.0.0");
        assertEquals(com.blueware.client.Main.port, 8080);

        String name = com.blueware.client.Main.getRelativeName("/users/zhangyan", "/users/zhangyan/my_my");
        assertEquals("hehe/pipi/baba", name);
    }
}
