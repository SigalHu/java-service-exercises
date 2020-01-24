package com.sigalhu.jse.flink.batch;

import org.apache.flink.api.common.functions.FlatJoinFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapPartitionFunction;
import org.apache.flink.api.common.operators.Order;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.util.Collector;
import org.junit.Test;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author huxujun
 * @date 2019/11/11
 */
public class TransformTest {

    private ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

    private DataSource<Tuple2<Integer, String>> text = env.fromElements(
            Tuple2.of(1, "Hadoop"), Tuple2.of(1, "Spark"), Tuple2.of(1, "Flink"),
            Tuple2.of(2, "Java"), Tuple2.of(2, "Spring Boot"), Tuple2.of(3, "Linux"),
            Tuple2.of(4, "Vue"));

    private DataSource<Tuple2<String, Integer>> anotherText = env.fromElements(
            Tuple2.of("another Hadoop", 1), Tuple2.of("another Spark", 1), Tuple2.of("another Flink", 1),
            Tuple2.of("another Java", 2), Tuple2.of("another Spring Boot", 2), Tuple2.of("another Linux", 3),
            Tuple2.of("another Vue", 4));

    @Test
    public void map() throws Exception {
        text.map(element -> element.f0 + 1).print();
    }

    @Test
    public void filter() throws Exception {
        text.filter(element -> element.f0 < 3).print();
    }

    @Test
    public void mapPartition() throws Exception {
        AtomicInteger count = new AtomicInteger(0);
        text.mapPartition(new MapPartitionFunction<Tuple2<Integer, String>, Tuple2<Integer, String>>() {
            @Override
            public void mapPartition(Iterable<Tuple2<Integer, String>> iterable, Collector<Tuple2<Integer, String>> collector) throws Exception {
                iterable.forEach(element -> {
                    System.out.println(String.format("mapPartition - %s: %s", count.getAndIncrement(), element));
                    if (element.f0 >= 3) {
                        collector.collect(element);
                    }
                });
            }
        }).setParallelism(4).print();
    }

    @Test
    public void first() throws Exception {
        text.first(2).print();
    }

    @Test
    public void group() throws Exception {
        // 先分组，然后每组取 top n
        text.groupBy(0).first(2).print();
    }

    @Test
    public void sortGroup() throws Exception {
        // 先分组，然后每组先排序再取 top n
        text.groupBy(0).sortGroup(1, Order.ASCENDING).first(2).print();
    }

    @Test
    public void flatMap() throws Exception {
        text.flatMap(new FlatMapFunction<Tuple2<Integer, String>, String>() {
            @Override
            public void flatMap(Tuple2<Integer, String> tuple2, Collector<String> collector) throws Exception {
                collector.collect(tuple2.f0.toString());
                collector.collect(tuple2.f1);
            }
        }).print();
    }

    @Test
    public void distinct() throws Exception {
        text.distinct(0).print();
    }

    @Test
    public void join() throws Exception {
        text.join(anotherText).where(0).equalTo(1).with(new FlatJoinFunction<Tuple2<Integer, String>, Tuple2<String, Integer>, Tuple3<Integer, String, String>>() {
            @Override
            public void join(Tuple2<Integer, String> tuple1, Tuple2<String, Integer> tuple2, Collector<Tuple3<Integer, String, String>> collector) throws Exception {
                collector.collect(Tuple3.of(tuple1.f0, tuple1.f1, tuple2.f0));
            }
        }).print();
    }

    @Test
    public void leftOuterJoin() throws Exception {
        text.leftOuterJoin(anotherText.first(2)).where(0).equalTo(1).with(new FlatJoinFunction<Tuple2<Integer, String>, Tuple2<String, Integer>, Tuple3<Integer, String, String>>() {
            @Override
            public void join(Tuple2<Integer, String> tuple1, Tuple2<String, Integer> tuple2, Collector<Tuple3<Integer, String, String>> collector) throws Exception {
                collector.collect(Tuple3.of(tuple1.f0, tuple1.f1, Objects.isNull(tuple2) ? null : tuple2.f0));
            }
        }).print();
    }

    @Test
    public void rightOuterJoin() throws Exception {
        text.rightOuterJoin(anotherText.first(2)).where(0).equalTo(1).with(new FlatJoinFunction<Tuple2<Integer, String>, Tuple2<String, Integer>, Tuple3<Integer, String, String>>() {
            @Override
            public void join(Tuple2<Integer, String> tuple1, Tuple2<String, Integer> tuple2, Collector<Tuple3<Integer, String, String>> collector) throws Exception {
                collector.collect(Tuple3.of(tuple2.f1, Objects.isNull(tuple1) ? null : tuple1.f1, tuple2.f0));
            }
        }).print();
    }

    @Test
    public void fullOuterJoin() throws Exception {
        text.fullOuterJoin(anotherText.first(2)).where(0).equalTo(1).with(new FlatJoinFunction<Tuple2<Integer, String>, Tuple2<String, Integer>, Tuple3<Integer, String, String>>() {
            @Override
            public void join(Tuple2<Integer, String> tuple1, Tuple2<String, Integer> tuple2, Collector<Tuple3<Integer, String, String>> collector) throws Exception {
                if (Objects.nonNull(tuple1)) {
                    collector.collect(Tuple3.of(tuple1.f0, tuple1.f1, Objects.isNull(tuple2) ? null : tuple2.f0));
                } else if (Objects.nonNull(tuple2)) {
                    collector.collect(Tuple3.of(tuple2.f1, null, tuple2.f0));
                }
            }
        }).print();
    }

    @Test
    public void cross() throws Exception {
        text.first(2).cross(anotherText.first(2)).print();
    }
}
