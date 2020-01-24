package com.sigalhu.jse.flink.streaming;

import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.ParallelSourceFunction;
import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author huxujun
 * @date 2019/11/12
 */
public class DataStreamTest {

    private StreamExecutionEnvironment env;
    private DataStreamSource<String> text;

    @Before
    public void setUp() throws Exception {
        // 获取执行环境
        env = StreamExecutionEnvironment.getExecutionEnvironment();
    }

    @After
    public void tearDown() throws Exception {
        env.execute("Flink Streaming Java API Skeleton");
    }

    @Test
    public void fromSocket() {
        // 获取参数，运行之前控制台执行 nc -lk 9999
        int port = ParameterTool.fromSystemProperties().getInt("port", 9999);
        // 读取数据
        text = env.socketTextStream("localhost", port);
        text.print();
    }

    @Test
    public void fromSourceFunction() {
        SourceFunction<String> function = new SourceFunction<String>() {
            private long count = 0;
            private volatile boolean isRunning = true;

            @Override
            public void run(SourceContext<String> ctx) throws Exception {
                while (isRunning) {
                    ctx.collect(String.valueOf(count));
                    count++;
                    Thread.sleep(1000);
                }
            }

            @Override
            public void cancel() {
                isRunning = false;
            }
        };

        text = env.addSource(function);
        text.print();
    }

    @Test
    public void fromParallelSourceFunction() {
        ParallelSourceFunction<String> function = new ParallelSourceFunction<String>() {
            private long count = 0;
            private volatile boolean isRunning = true;

            @Override
            public void run(SourceContext<String> ctx) throws Exception {
                while (isRunning) {
                    ctx.collect(String.valueOf(count));
                    count++;
                    Thread.sleep(1000);
                }
            }

            @Override
            public void cancel() {
                isRunning = false;
            }
        };

        text = env.addSource(function).setParallelism(2);
        text.print();
    }

    @Test
    public void fromRichParallelSourceFunction() {
        RichParallelSourceFunction<String> function = new RichParallelSourceFunction<String>() {
            private long count = 0;
            private volatile boolean isRunning = true;

            @Override
            public void run(SourceContext<String> ctx) throws Exception {
                while (isRunning) {
                    ctx.collect(String.valueOf(count));
                    count++;
                    Thread.sleep(1000);
                }
            }

            @Override
            public void cancel() {
                isRunning = false;
            }

            @Override
            public void open(Configuration parameters) throws Exception {
                super.open(parameters);
            }

            @Override
            public void close() throws Exception {
                super.close();
            }
        };

        text = env.addSource(function).setParallelism(2);
        text.print();
    }
}
