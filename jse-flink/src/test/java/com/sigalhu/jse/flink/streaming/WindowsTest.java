package com.sigalhu.jse.flink.streaming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;
import org.apache.flink.util.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author huxujun
 * @date 2019/11/11
 */
public class WindowsTest {

    private StreamExecutionEnvironment env;
    private DataStreamSource<String> text;

    @Before
    public void setUp() throws Exception {
        // 获取执行环境
        env = StreamExecutionEnvironment.getExecutionEnvironment();
        // 获取参数，运行之前控制台执行 nc -lk 9999
        int port = ParameterTool.fromSystemProperties().getInt("port", 9999);
        // 读取数据
        text = env.socketTextStream("localhost", port);
    }

    @After
    public void tearDown() throws Exception {
        env.execute("Flink Streaming Java API Skeleton");
    }

    @Test
    public void tumblingWindow() {
        text.flatMap(new FlatMapFunction<String, WordStatInfo>() {
            @Override
            public void flatMap(String line, Collector<WordStatInfo> collector) throws Exception {
                for (String word : line.toLowerCase().split(" ")) {
                    if (!StringUtils.isNullOrWhitespaceOnly(word)) {
                        collector.collect(new WordStatInfo(word, 1));
                    }
                }
            }
        }).keyBy("word").timeWindow(Time.seconds(5)).sum("count").print();
    }

    @Test
    public void slidingWindow() {
        text.flatMap(new FlatMapFunction<String, WordStatInfo>() {
            @Override
            public void flatMap(String line, Collector<WordStatInfo> collector) throws Exception {
                for (String word : line.toLowerCase().split(" ")) {
                    if (!StringUtils.isNullOrWhitespaceOnly(word)) {
                        collector.collect(new WordStatInfo(word, 1));
                    }
                }
            }
        }).keyBy("word").timeWindow(Time.seconds(5), Time.seconds(2)).sum("count").print();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WordStatInfo {
        private String word;
        private int count;
    }
}