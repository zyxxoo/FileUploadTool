package com.blueware.utils;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhangyan on 2017/3/14.
 */
public class FileUtils {

    public static List<String> listFile(String targetFile) throws Exception {
        File target_file = new File(targetFile);
        List<String> files = new LinkedList<>();
        if (!target_file.exists()){
            System.out.println(String.format("%s not found", target_file));
            return Collections.emptyList();
        }
        if (target_file.isDirectory()){
            for (File file : target_file.listFiles()) {
                if (file.isFile()){
                    files.add(file.getAbsolutePath());
                }else{
                    files.addAll(listFile(file.getAbsolutePath()));
                }
            }
        }else{
            files.add(targetFile);
        }

        return files;
    }
}
