package com.blueware.service;

import com.blueware.log.Log;
import com.blueware.utils.Params;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by zhangyan on 2017/3/13.
 */
public class Main {
    final static String TAG  = "service";
    static String filePath;
    static String port;
    static Properties config;
    public static void main(String[] argvs) throws Throwable{
        if (argvs == null || argvs.length < 1){
            Usage.print();
        }
        try {
            paraseParams(new Params(argvs));
            start_forever();
        }finally {
            Usage.print();
        }
    }

    private static void paraseParams(Params params) throws IOException {
        for (String s = params.shift(); !s.isEmpty(); s = params.shift()){

            if (s.startsWith("--config=")){
                config = new Properties();
                config.load(new FileInputStream(s.split("=")[1]));
            }
        }

        if (config != null){
            port = config.getProperty("server.port", "9090");
            filePath = config.getProperty("server.store_path", System.getProperty("user.dir"));
        }
    }

    private static void start_forever() throws Throwable {
        new FileHandlerServer(filePath, port).start();
    }


    /**
     * 使用提示
     */
    public static class Usage{
        static String usage;
        static {
            StringBuilder sb = new StringBuilder();
            sb.append("option --cofing=PrppertiesPath: input properties file path");
            usage = sb.toString();
        }


        public static void print(){
            Log.i(TAG, usage);
        }
    }
}
