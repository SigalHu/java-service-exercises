package com.sigalhu.jse.protobuf.beans;

import io.protostuff.*;
import io.protostuff.runtime.RuntimeSchema;
import lombok.Data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author huxujun
 * @date 2019/1/17
 */
@Data
public class Person {

    @Tag(1)
    private Long id;

    @Tag(2)
    private String name;

    @Tag(3)
    private Integer age;

    @Tag(4)
    private Double height;

    @Exclude
    private Date updateTime;

    @Tag(5)
    private Date createTime;

    public static void main(String[] args) throws Exception {
        Schema<Person> schema = RuntimeSchema.getSchema(Person.class);
        Person person = new Person();
        person.setId(12345L);
        person.setName("12345");
        person.setAge(12345);
        person.setHeight(12345D);

        LinkedBuffer buffer = LinkedBuffer.allocate(256);
        System.err.println();
        byte[] bytes = ProtobufIOUtil.toByteArray(person, schema, buffer);
        buffer.clear();
        System.err.println(Arrays.toString(bytes));

//        byte[] bytes = new byte[]{8, -71, 96, 18, 5, 49, 50, 51, 52, 53, 24, -71, 96, 33, 0, 0, 0, 0, -128, 28, -56, 64, 41, 34, 104, -24, 90, 104, 1, 0, 0};
//
//        Person person = new Person();
//        ProtobufIOUtil.mergeFrom(bytes, person, schema);
//        System.err.println(person);
//
//        List<Person> persons = new ArrayList<>();
//        for (int i = 0; i < 1000; i++) {
//            persons.add(person);
//        }
//
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        ProtobufIOUtil.writeListTo(stream, persons, schema, buffer);
//        bytes = stream.toByteArray();
//        buffer.clear();
//        stream.close();
//
//        persons = ProtobufIOUtil.parseListFrom(new ByteArrayInputStream(bytes), schema);
//        System.err.println(persons);
    }
}
