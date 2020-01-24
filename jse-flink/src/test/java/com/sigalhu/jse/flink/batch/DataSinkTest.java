package com.sigalhu.jse.flink.batch;

import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.core.fs.FileSystem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author huxujun
 * @date 2019/11/11
 */
public class DataSinkTest {

    private ExecutionEnvironment env;
    private DataSource<String> text;
    private Path sinkPath;

    @Before
    public void setUp() throws Exception {
        // 获取执行环境
        env = ExecutionEnvironment.getExecutionEnvironment();
        // 读取数据
        text = env.fromElements("Hello", "World", "Hello World");
        sinkPath = Paths.get(ClassLoader.getSystemResource("words.txt").getPath()).getParent().resolve("sinks");
    }

    @After
    public void tearDown() throws Exception {
        env.execute("Flink Batch Java API Skeleton");
    }

    @Test
    public void toTextFile() throws IOException {
        Path path = sinkPath.resolve("text");
        if (Files.notExists(path)) {
            Files.createDirectories(path);
        }
        text.writeAsText(path.toString(), FileSystem.WriteMode.OVERWRITE).setParallelism(2);
    }
}
