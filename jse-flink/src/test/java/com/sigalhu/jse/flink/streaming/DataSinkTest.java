package com.sigalhu.jse.flink.streaming;

import lombok.Data;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author huxujun
 * @date 2019/11/13
 */
public class DataSinkTest {

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
    public void test() {
        text.map(new MapFunction<String, Person>() {
            @Override
            public Person map(String s) throws Exception {
                try {
                    String[] strings = s.split(",");
                    Person person = new Person();
                    person.setName(strings[0]);
                    person.setAge(Integer.valueOf(strings[1]));
                    return person;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return null;
            }
        }).addSink(new RichSinkFunction<Person>() {

            private StorageOperator operator;

            @Override
            public void open(Configuration parameters) throws Exception {
                super.open(parameters);
                operator = new StorageOperator();
            }

            @Override
            public void close() throws Exception {
                super.close();
                operator.close();
            }

            @Override
            public void invoke(Person value, Context context) throws Exception {
                operator.insert(value);
            }
        });
    }

    public static class StorageOperator {

        public StorageOperator() {
            System.out.println("The storage operator connect.");
        }

        void insert(Object o) {
            System.err.println(o);
        }

        public void close() {
            System.out.println("The storage operator disconnect.");
        }
    }

    @Data
    public static class Person {

        private String name;
        private Integer age;
    }
}
