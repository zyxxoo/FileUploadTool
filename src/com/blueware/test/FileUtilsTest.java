package com.blueware.test;

import com.blueware.utils.FileUtils;
import org.junit.Test;

import java.util.List;

/**
 * Created by zhangyan on 2017/3/14.
 */
public class FileUtilsTest {

    @Test
    public void test1() throws Exception {
        List<String> files = FileUtils.listFile("/Users/zhangyan/my_my");
        files.size();
    }
}
