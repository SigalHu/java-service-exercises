package com.sigalhu.jse.flink.batch.datasource;

import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;

/**
 * 批处理 CSV 文件统计词频
 *
 * @author huxujun
 * @date 2019/11/8
 */
public class BatchJobFromCsvFile {

    public static void main(String[] args) throws Exception {

        // 获取执行环境
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        // 读取数据
        DataSource<Tuple2<String, Integer>> text = env.readCsvFile(ClassLoader.getSystemResource("people.csv").getPath())
                .ignoreFirstLine().types(String.class, Integer.class);

        // transform
        text.groupBy(0).sum(1).print();
    }
}
