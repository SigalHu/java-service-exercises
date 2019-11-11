package com.sigalhu.jse.flink.batch;

import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.shaded.guava18.com.google.common.io.Files;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author huxujun
 * @date 2019/11/11
 */
public class DistributedCacheTest {

    private ExecutionEnvironment env;
    private DataSource<String> text;

    @Before
    public void setUp() throws Exception {
        // 获取执行环境
        env = ExecutionEnvironment.getExecutionEnvironment();

        String filename = ClassLoader.getSystemResource("words.txt").getPath();
        // 注册本地或 HDFS 文件
        env.registerCachedFile(filename, "words");

        // 读取数据
        text = env.fromElements("Hello", "World", "Hello World");
    }

    @Test
    public void test() throws Exception {
        text.map(new RichMapFunction<String, String>() {

            @Override
            public void open(Configuration parameters) throws Exception {
                super.open(parameters);
                // 获取分布式缓存的内容
                File file = getRuntimeContext().getDistributedCache().getFile("words");
                List<String> lines = Files.readLines(file, StandardCharsets.UTF_8);
                System.out.println(lines);
            }

            @Override
            public String map(String s) throws Exception {
                return s;
            }
        }).print();
    }
}
