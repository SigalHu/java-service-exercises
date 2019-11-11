package com.sigalhu.jse.flink.batch;

import org.apache.flink.api.common.JobExecutionResult;
import org.apache.flink.api.common.accumulators.IntCounter;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.core.fs.FileSystem;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author huxujun
 * @date 2019/11/11
 */
public class CounterTest {

    private ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
    private DataSource<String> text = env.fromElements("Hadoop", "Spark", "Flink", "Java", "Spring Boot", "Linux", "Vue");
    private Path path = Paths.get(ClassLoader.getSystemResource("words.txt").getPath()).getParent().resolve("counter");

    @Test
    public void test() throws Exception {
        text.map(new RichMapFunction<String, Integer>() {

            private IntCounter counter = new IntCounter();

            @Override
            public void open(Configuration parameters) throws Exception {
                super.open(parameters);
                getRuntimeContext().addAccumulator("CounterTest", counter);
            }

            @Override
            public Integer map(String s) throws Exception {
                counter.add(1);
                return counter.getLocalValue();
            }
        }).setParallelism(4).writeAsText(path.toString(), FileSystem.WriteMode.OVERWRITE);

        JobExecutionResult result = env.execute("Flink Batch Java API Skeleton");
        Integer count = result.getAccumulatorResult("CounterTest");
        System.out.println("count: " + count);
    }
}
