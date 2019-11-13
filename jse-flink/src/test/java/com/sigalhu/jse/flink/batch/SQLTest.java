package com.sigalhu.jse.flink.batch;

import lombok.Data;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.java.BatchTableEnvironment;
import org.apache.flink.types.Row;
import org.junit.Before;
import org.junit.Test;

/**
 * @author huxujun
 * @date 2019/11/13
 */
public class SQLTest {

    private BatchTableEnvironment tableEnv;

    @Before
    public void setUp() throws Exception {
        // 获取执行环境
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        tableEnv = TableEnvironment.getTableEnvironment(env);

        // 读取数据
        DataSource<Person> text = env.readCsvFile(ClassLoader.getSystemResource("people.csv").getPath())
                .ignoreFirstLine().pojoType(Person.class, "name", "age");
        tableEnv.registerTable("person", tableEnv.fromDataSet(text));
    }

    @Test
    public void test() throws Exception {
        Table result = tableEnv.sqlQuery("select name, age from person where age >= 30");
        tableEnv.toDataSet(result, Row.class).print();
    }

    @Data
    public static class Person {
        private String name;
        private Integer age;
    }
}
