package com.sigalhu.jse.flink.batch.transform;

import org.apache.flink.api.common.functions.MapPartitionFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.util.Collector;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author huxujun
 * @date 2019/11/10
 */
public class BatchJobWithTransform {

    public static void main(String[] args) throws Exception {

        // 获取执行环境
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        // 读取数据
        DataSource<Integer> text = env.fromElements(0, 1, 2, 3, 3, 4, 4);

        // transform
        System.out.println("text");
        text.print();
        System.out.println("map");
        text.map(element -> element + 1).print();
        System.out.println("filter");
        text.filter(element -> element < 3).print();
        System.out.println("mapPartition");
        AtomicInteger count = new AtomicInteger(0);
        text.mapPartition(new MapPartitionFunction<Integer, Integer>() {
            @Override
            public void mapPartition(Iterable<Integer> iterable, Collector<Integer> collector) throws Exception {
                iterable.forEach(element -> {
                    System.out.println(String.format("mapPartition - %s: %s", count.getAndIncrement(), element));
                    if (element >= 3) {
                        collector.collect(element);
                    }
                });
            }
        }).setParallelism(4).print();
    }
}
