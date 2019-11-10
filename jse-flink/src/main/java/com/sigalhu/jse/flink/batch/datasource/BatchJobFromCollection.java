package com.sigalhu.jse.flink.batch.datasource;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;
import org.apache.flink.util.StringUtils;

/**
 * 批处理统计词频
 * 
 * @author huxujun
 * @date 2019/11/8
 */
public class BatchJobFromCollection {

    public static void main(String[] args) throws Exception {

        // 获取执行环境
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        // 读取数据
        DataSource<String> text = env.fromElements("Hello", "World", "Hello World");

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
        }).groupBy(0).sum(1).print();
    }
}
