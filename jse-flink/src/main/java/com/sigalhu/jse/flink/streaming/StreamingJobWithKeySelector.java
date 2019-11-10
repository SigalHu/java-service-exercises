package com.sigalhu.jse.flink.streaming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;
import org.apache.flink.util.StringUtils;

/**
 * 使用 {@link KeySelector} 实时统计词频，运行之前控制台执行 nc -lk 9999
 *
 * @author huxujun
 * @date 2019/11/8
 */
public class StreamingJobWithKeySelector {

    public static void main(String[] args) throws Exception {

        // 获取执行环境
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 获取参数
        ParameterTool tool = ParameterTool.fromArgs(args);
        int port = tool.getInt("port", 9999);

        // 读取数据
        DataStreamSource<String> text = env.socketTextStream("localhost", port);

        // transform
        text.flatMap(new FlatMapFunction<String, WordStatInfo>() {
            @Override
            public void flatMap(String line, Collector<WordStatInfo> collector) throws Exception {
                for (String word : line.toLowerCase().split(" ")) {
                    if (!StringUtils.isNullOrWhitespaceOnly(word)) {
                        collector.collect(new WordStatInfo(new ComplexNestedClass(1, 2, Tuple3.of(3L, 4L, word)), 1));
                    }
                }
            }
        }).keyBy(new KeySelector<WordStatInfo, String>() {
            @Override
            public String getKey(WordStatInfo wordStatInfo) throws Exception {
                return wordStatInfo.getComplex().getWord().getField(2);
            }
        }).timeWindow(Time.seconds(5)).sum("complex.word.f1").print();

        env.execute("Flink Streaming Java API Skeleton");
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WordStatInfo {
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
