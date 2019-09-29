package com.sigalhu.jse.serialize.protostuff;

import com.sigalhu.jse.serialize.SerializeTest;
import com.sigalhu.jse.serialize.beans.Person;
import com.sigalhu.jse.serialize.protobuf.ProtobufUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author huxujun
 * @date 2019/9/28
 */
public class ProtostuffUtilsTest extends SerializeTest {

    @Test
    public void testProtobuf() {
        byte[] bytes = ProtostuffUtils.toBytes(person);
        Person p = ProtostuffUtils.parseBytes(bytes);
        Assert.assertNotEquals(person, p);
        person.setUpdateTime(null);
        Assert.assertEquals(person, p);
    }

    @Test
    public void testProtobufList() {
        byte[] bytes = ProtostuffUtils.toDelimitedBytes(people);
        List<Person> ps = ProtostuffUtils.parseDelimitedBytes(bytes);
        Assert.assertNotEquals(people, ps);
        people.forEach(p -> p.setUpdateTime(null));
        Assert.assertEquals(people, ps);
    }

    @Test
    public void testProtostuff() {
        byte[] bytes = ProtostuffUtils.toBytesV2(person);
        Person p = ProtostuffUtils.parseBytesV2(bytes);
        Assert.assertNotEquals(person, p);
        person.setUpdateTime(null);
        Assert.assertEquals(person, p);
    }

    @Test
    public void testProtostuffList() {
        byte[] bytes = ProtostuffUtils.toDelimitedBytesV2(people);
        List<Person> ps = ProtostuffUtils.parseDelimitedBytesV2(bytes);
        Assert.assertNotEquals(people, ps);
        people.forEach(p -> p.setUpdateTime(null));
        Assert.assertEquals(people, ps);
    }

    @Test
    public void testMix() {
        byte[] bytes = ProtostuffUtils.toBytes(person);
        Person p = ProtostuffUtils.parseBytesV2(bytes);
        Assert.assertNotEquals(person, p);
        person.setUpdateTime(null);
        Assert.assertEquals(person, p);
    }

    @Test(expected = RuntimeException.class)
    public void testMixList() {
        byte[] bytes = ProtostuffUtils.toDelimitedBytes(people);
        List<Person> ps = ProtostuffUtils.parseDelimitedBytesV2(bytes);
    }

    @Test
    public void testMixV2() {
        byte[] bytes = ProtostuffUtils.toBytesV2(person);
        Person p = ProtostuffUtils.parseBytes(bytes);
        Assert.assertNotEquals(person, p);
        person.setUpdateTime(null);
        Assert.assertEquals(person, p);
    }

    @Test(expected = RuntimeException.class)
    public void testMixListV2() {
        byte[] bytes = ProtostuffUtils.toDelimitedBytesV2(people);
        List<Person> ps = ProtostuffUtils.parseDelimitedBytes(bytes);
    }

    @Test
    public void testProto() {
        byte[] bytes = ProtobufUtils.toBytes(person);
        Person p = ProtostuffUtils.parseBytes(bytes);
        Assert.assertNotEquals(person, p);
        person.setUpdateTime(null);
        Assert.assertEquals(person, p);
    }

    @Test
    public void testProtoList() {
        byte[] bytes = ProtobufUtils.toDelimitedBytes(people);
        List<Person> ps = ProtostuffUtils.parseDelimitedBytes(bytes);
        Assert.assertNotEquals(people, ps);
        people.forEach(p -> p.setUpdateTime(null));
        Assert.assertEquals(people, ps);
    }

    @Test
    public void testProtoV2() throws Exception {
        byte[] bytes = ProtostuffUtils.toBytesV2(person);
        Person p = ProtobufUtils.parseBytes(bytes);
        Assert.assertNotEquals(person, p);
        person.setUpdateTime(null);
        Assert.assertEquals(person, p);
    }

    @Test(expected = RuntimeException.class)
    public void testProtoListV2() {
        byte[] bytes = ProtostuffUtils.toDelimitedBytesV2(people);
        List<Person> ps = ProtobufUtils.parseDelimitedBytes(bytes);
    }
}