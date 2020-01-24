package com.sigalhu.jse.flink.batch;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.util.Collector;
import org.apache.flink.util.StringUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * @author huxujun
 * @date 2019/11/11
 */
public class DataSourceTest {

    private ExecutionEnvironment env;

    @Before
    public void setUp() throws Exception {
        // 获取执行环境
        env = ExecutionEnvironment.getExecutionEnvironment();
    }

    /**
     * 批处理集合统计词频
     *
     * @throws Exception
     */
    @Test
    public void fromCollection() throws Exception {
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

    /**
     * 批处理 CSV 文件统计词频
     *
     * @throws Exception
     */
    @Test
    public void fromCsvFile() throws Exception {
        // 读取数据
        DataSource<Tuple2<String, Integer>> text = env.readCsvFile(ClassLoader.getSystemResource("people.csv").getPath())
                .ignoreFirstLine().types(String.class, Integer.class);

        // transform
        text.groupBy(0).sum(1).print();
    }

    /**
     * 批处理压缩文件统计词频
     *
     * @throws Exception
     */
    @Test
    public void fromCompressionFile() throws Exception {
        // 读取数据
        DataSource<String> text = env.readTextFile(ClassLoader.getSystemResource("words.gz").getPath());

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

    /**
     * 批处理文本文件统计词频
     *
     * @throws Exception
     */
    @Test
    public void fromTextFile() throws Exception {
        // 读取数据
        DataSource<String> text = env.readTextFile(ClassLoader.getSystemResource("words.txt").getPath());

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

    /**
     * 批处理递归文本文件统计词频
     *
     * @throws Exception
     */
    @Test
    public void fromNestedTextFile() throws Exception {
        // 读取数据
        Configuration configuration = new Configuration();
        configuration.setBoolean("recursive.file.enumeration", true);
        DataSource<String> text = env.readTextFile(ClassLoader.getSystemResource("nested").getPath())
                .withParameters(configuration);

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