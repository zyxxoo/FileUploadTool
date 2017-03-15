package com.blueware.test;

/**
 * Created by zhangyan on 2017/3/14.
 */
public class RunServiceMain {
    public static void main(String[] args) throws Throwable {
        //启动服务
        //1. 配置着路径
        String[] sArgs = new String[]{"--config=/Users/zhangyan/Documents/code/tools/resources/config.pro"};
        //2. 启动
        com.blueware.service.Main.main(sArgs);
    }
}
