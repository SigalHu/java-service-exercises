package com.sigalhu.jse.flink.streaming;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.flink.api.common.functions.FlatJoinFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapPartitionFunction;
import org.apache.flink.api.common.operators.Order;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.collector.selector.OutputSelector;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author huxujun
 * @date 2019/11/12
 */
public class TransformTest {

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
    public void map() {
        text.map(element -> "map: " + element).print();
    }

    @Test
    public void filter() {
        text.filter(NumberUtils::isDigits).print();
    }

    @Test
    public void union() {
        text.union(text).print();
    }

    @Test
    public void splitAndSelect() {
        text.split(new OutputSelector<String>() {
            @Override
            public Iterable<String> select(String value) {
                List<String> list = new ArrayList<>();
                if (NumberUtils.isDigits(value)) {
                    list.add("digits");
                } else {
                    list.add("string");
                }
                return list;
            }
        }).select("digits").print();
    }
}