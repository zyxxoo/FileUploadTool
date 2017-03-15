package com.blueware;

public class Main {

    public static void main(String[] args) throws Throwable {
	 //启动服务
        //1. 配置着路径
        String[] sArgs = new String[]{"--config=/Users/zhangyan/Documents/code/tools/resources/config.pro"};
        //2. 启动
        com.blueware.service.Main.main(sArgs);

     //启动客户端
        //1. 设置文件参数
        String[] cArgs = new String[]{"-h=127.0.0.1:9090", "-f=/Users/zhangyan/Documents/code/tools/resources/config.pro"};
        //2. 启动客户端
        com.blueware.client.Main.main(cArgs);
    }
}
