package com.blueware.test;

/**
 * Created by zhangyan on 2017/3/14.
 */
public class RunClientMain {
    public static void main(String[] args) throws Throwable {
        //启动客户端
        //1. 设置文件参数
        String[] cArgs = new String[]{"-h=10.128.6.79:9090", "-f=/Users/zhangyan/Documents/code/study/android_source"};
//        String[] cArgs = new String[]{"-h=127.0.0.1:9090", "-f=/Users/zhangyan/Documents/code/tools/resources"};
//        String[] cArgs = new String[]{"-h=10.128.6.79:9090", "-f=/Users/zhangyan/Documents/code/study/android_source/.repo/project-objects/device/huawei/angler-kernel.git/objects/pack"};
//        String[] cArgs = new String[]{"-h=127.0.0.1:9090", "-f=/Users/zhangyan/Documents/code/study/android_source/.repo/project-objects/device/huawei/angler-kernel.git/objects/pack/pack-2d590935e7e758a0bec9562e254350c698894fb9.pack"};
        //2. 启动客户端
        com.blueware.client.Main.main(cArgs);
    }
}
