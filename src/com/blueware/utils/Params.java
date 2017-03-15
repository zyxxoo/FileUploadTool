package com.blueware.utils;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhangyan on 2017/3/13.
 */
public class Params {
    List<String> argvs = new LinkedList<>();
    public Params(String[] argvs){
        if (argvs != null){

            for (String s : argvs){
                this.argvs.add(s);
            }
        }
    }

    private String shift(int n){
        String ret = "";
       if (!argvs.isEmpty()){

           ret = argvs.remove(0);
       }
       return ret;
    };

    public String shift(){
        return shift(1);
    }
}
