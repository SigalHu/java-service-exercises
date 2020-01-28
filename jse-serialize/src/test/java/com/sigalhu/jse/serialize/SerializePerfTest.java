package com.sigalhu.jse.serialize;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.BeanSerializer;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import lombok.Data;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * @author huxujun
 * @date 2019/9/29
 */
public class SerializePerfTest {

    private static long count = 1000L;
    private static int friends = 0;
    private static int size = 1000;
    private static int buff = 512 * 1024;
    private static Random random = new Random();
    private static Person person = new Person();

    private static Person randomPerson() {
        return randomPerson(friends);
    }

    private static Person randomPerson(int friends) {
        person.setId(random.nextLong());
        person.setName("person" + random.nextInt());
        person.setAge(random.nextInt());
        person.setHeight(random.nextDouble());
        person.setWeight(random.nextDouble());
        person.setPhone("" + random.nextInt());
        if (friends > 0) {
            List<Person> people = new ArrayList<>(friends);
            for (int i = 0; i < friends; i++) {
                people.add(person);
            }
            person.setFriends(people);
        }
        if (size > 0) {
            List<Long> longs = new ArrayList<>(size);
            Set<Double> set = new HashSet<>(size);
            Map<String, Integer> map = new HashMap<>(size);
            for (int i = 0; i < size; i++) {
                longs.add(random.nextLong());
                set.add(random.nextDouble());
                map.put("person" + random.nextInt(), random.nextInt());
            }
            person.setLongList(longs);
            person.setDoubleSet(set);
            person.setMap(map);
        }
        return person;
    }

    @Test
    public void test() throws Exception {
        long cost = 0L;
        double size = 0D;
        for (int i = 0; i < 10; i++) {
            Pair<Long, Double> pair = testProtobuf();
            cost += pair.getLeft();
            size += pair.getRight();
        }
        System.out.println("====== total cost and size ======");
        System.out.println("平均耗时：" + cost / 10D + "ms，平均大小：" + Math.round(size / 10D) + "B");
    }

    private static Pair<Long, Double> testCommon() throws Exception {
        byte[] bytes;
        Person actual, expected;

        System.out.println("====== test common ======");
        long cost = 0, start, end;
        double size = 0;
        for (int i = 0; i < count; i++) {
            expected = randomPerson();

            start = System.currentTimeMillis();
            bytes = commonSerialize(expected);
            actual = commonDeserialize(bytes);
            end = System.currentTimeMillis();

            cost += end - start;
            size += bytes.length;
            Assert.assertEquals(expected, actual);
        }
        size /= count;
        System.out.println("耗时：" + cost + "ms, 大小：" + Math.round(size) + "B");
        return Pair.of(cost, size);
    }

    private static Pair<Long, Double> testProtobuf() {
        byte[] bytes;
        Person actual, expected;

        System.out.println("====== test protobuf ======");
        long cost = 0, start, end;
        double size = 0;
        for (int i = 0; i < count; i++) {
            expected = randomPerson();

            start = System.currentTimeMillis();
            bytes = protobufSerialize(expected);
            actual = protobufDeserialize(bytes);
            end = System.currentTimeMillis();

            cost += end - start;
            size += bytes.length;
            Assert.assertEquals(expected, actual);
        }
        size /= count;
        System.out.println("耗时：" + cost + "ms, 大小：" + Math.round(size) + "B");
        return Pair.of(cost, size);
    }

    private static Pair<Long, Double> testProtostuff() {
        byte[] bytes;
        Person actual, expected;

        System.out.println("====== test protostuff ======");
        long cost = 0, start, end;
        double size = 0;
        for (int i = 0; i < count; i++) {
            expected = randomPerson();

            start = System.currentTimeMillis();
            bytes = protostuffSerialize(expected);
            actual = protostuffDeserialize(bytes);
            end = System.currentTimeMillis();

            cost += end - start;
            size += bytes.length;
            Assert.assertEquals(expected, actual);
        }
        size /= count;
        System.out.println("耗时：" + cost + "ms, 大小：" + Math.round(size) + "B");
        return Pair.of(cost, size);
    }

    private static Pair<Long, Double> testKryo() {
        byte[] bytes;
        Person actual, expected;

        System.out.println("====== test kryo ======");
        long cost = 0, start, end;
        double size = 0;
        for (int i = 0; i < count; i++) {
            expected = randomPerson();

            start = System.currentTimeMillis();
            bytes = kryoSerialize(expected);
            actual = kryoDeserialize(bytes);
            end = System.currentTimeMillis();

            cost += end - start;
            size += bytes.length;
            Assert.assertEquals(expected, actual);
        }
        size /= count;
        System.out.println("耗时：" + cost + "ms, 大小：" + Math.round(size) + "B");
        return Pair.of(cost, size);
    }

    private static byte[] commonSerialize(Person person) throws Exception {
        try (
                ByteArrayOutputStream buff = new ByteArrayOutputStream();
                ObjectOutputStream out = new ObjectOutputStream(buff)
        ) {
            out.writeObject(person);
            return buff.toByteArray();
        } finally {
        }
    }

    private static Person commonDeserialize(byte[] bytes) throws Exception {
        try (
                ByteArrayInputStream buff = new ByteArrayInputStream(bytes);
                ObjectInputStream in = new ObjectInputStream(buff)
        ) {
            return (Person) in.readObject();
        } finally {
        }
    }


    private static final Schema<Person> schema = RuntimeSchema.getSchema(Person.class);
    private static final LinkedBuffer buffer = LinkedBuffer.allocate(buff);

    private static byte[] protobufSerialize(Person person) {
        try {
            return ProtobufIOUtil.toByteArray(person, schema, buffer);
        } finally {
            buffer.clear();
        }
    }

    private static Person protobufDeserialize(byte[] bytes) {
        Person person = schema.newMessage();
        ProtobufIOUtil.mergeFrom(bytes, person, schema);
        return person;
    }

    private static byte[] protostuffSerialize(Person person) {
        try {
            return ProtostuffIOUtil.toByteArray(person, schema, buffer);
        } finally {
            buffer.clear();
        }
    }

    private static Person protostuffDeserialize(byte[] bytes) {
        Person person = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(bytes, person, schema);
        return person;
    }

    private static final Kryo kryo = new Kryo();
    private static final Output output = new Output(buff, buff);
    private static final Input input = new Input();

    static {
        kryo.register(Person.class, new BeanSerializer(kryo, Person.class));
        kryo.register(ArrayList.class);
        kryo.register(HashSet.class);
        kryo.register(HashMap.class);
    }

    private static byte[] kryoSerialize(Person person) {
        try {
            kryo.writeObject(output, person);
            return output.toBytes();
        } finally {
            output.reset();
        }
    }

    private static Person kryoDeserialize(byte[] bytes) {
        input.setBuffer(bytes);
        return kryo.readObject(input, Person.class);
    }

    @Data
    public static class Person implements Serializable {
        private static final long serialVersionUID = 3736890059433852945L;

        private Long id;
        private String name;
        private Integer age;
        private Double height;
        private Double weight;
        private String phone;
        private List<Person> friends;

        private List<Long> longList;
        private Set<Double> doubleSet;
        private Map<String, Integer> map;
    }
}
