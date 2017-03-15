package com.blueware.log;

/**
 * Created by zhangyan on 2017/3/13.
 */
public class Log implements ILog{
    static volatile ILog log = new Log();
    @Override
    public void info(String tag, String info) {
        System.out.println(String.format("OneAPM[%s]:%s", tag, info));
    }

    public static void i(String tag, String info){
        log.info(tag, info);
    }
}
