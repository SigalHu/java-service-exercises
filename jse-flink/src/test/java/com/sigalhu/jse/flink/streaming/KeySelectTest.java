package com.sigalhu.jse.flink.streaming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
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
public class KeySelectTest {

    private StreamExecutionEnvironment env;
    private int port;
    private DataStreamSource<String> text;

    @Before
    public void setUp() throws Exception {
        // 获取执行环境
        env = StreamExecutionEnvironment.getExecutionEnvironment();
        // 获取参数，运行之前控制台执行 nc -lk 9999
        port = ParameterTool.fromSystemProperties().getInt("port", 9999);
        // 读取数据
        text = env.socketTextStream("localhost", port);
    }

    @After
    public void tearDown() throws Exception {
        env.execute("Flink Streaming Java API Skeleton");
    }

    @Test
    public void index() throws Exception {
        text.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public void flatMap(String line, Collector<Tuple2<String, Integer>> collector) throws Exception {
                for (String word : line.toLowerCase().split(" ")) {
                    if (!StringUtils.isNullOrWhitespaceOnly(word)) {
                        collector.collect(Tuple2.of(word, 1));
                    }
                }
            }
        }).keyBy(0).timeWindow(Time.seconds(5)).sum(1).print();
    }

    @Test
    public void pojo() {
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
    public void nestedPojo() {
        text.flatMap(new FlatMapFunction<String, NestedWordStatInfo>() {
            @Override
            public void flatMap(String line, Collector<NestedWordStatInfo> collector) throws Exception {
                for (String word : line.toLowerCase().split(" ")) {
                    if (!StringUtils.isNullOrWhitespaceOnly(word)) {
                        collector.collect(new NestedWordStatInfo(new ComplexNestedClass(1, 2, Tuple3.of(3L, 4L, word)), 1));
                    }
                }
            }
        }).keyBy("complex.word.f2").timeWindow(Time.seconds(5)).sum("complex.word.f1").print();
    }

    @Test
    public void keySelector() {
        text.flatMap(new FlatMapFunction<String, NestedWordStatInfo>() {
            @Override
            public void flatMap(String line, Collector<NestedWordStatInfo> collector) throws Exception {
                for (String word : line.toLowerCase().split(" ")) {
                    if (!StringUtils.isNullOrWhitespaceOnly(word)) {
                        collector.collect(new NestedWordStatInfo(new ComplexNestedClass(1, 2, Tuple3.of(3L, 4L, word)), 1));
                    }
                }
            }
        }).keyBy(new KeySelector<NestedWordStatInfo, String>() {
            @Override
            public String getKey(NestedWordStatInfo wordStatInfo) throws Exception {
                return wordStatInfo.getComplex().getWord().getField(2);
            }
        }).timeWindow(Time.seconds(5)).sum("complex.word.f1").print();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WordStatInfo {
        private String word;
        private int count;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NestedWordStatInfo {
        private ComplexNestedClass complex;
        private int count;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ComplexNestedClass {
        private Integer someNumber;
        private float someFloat;
        private Tuple3<Long, Long, String> word;
    }
}