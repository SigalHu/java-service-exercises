package com.sigalhu.jse.flink.streaming.keyselector;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;
import org.apache.flink.util.StringUtils;

/**
 * 实时统计词频，运行之前控制台执行 nc -lk 9999
 *
 * @author huxujun
 * @date 2019/11/8
 */
public class StreamingJobWithIndex {

    public static void main(String[] args) throws Exception {

        // 获取执行环境
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 获取参数
        ParameterTool tool = ParameterTool.fromArgs(args);
        int port = tool.getInt("port", 9999);

        // 读取数据
        DataStreamSource<String> text = env.socketTextStream("localhost", port);

        // transform
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

        env.execute("Flink Streaming Java API Skeleton");
    }
}
