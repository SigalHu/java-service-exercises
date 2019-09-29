package com.sigalhu.jse.serialize.protobuf;

import com.sigalhu.jse.serialize.SerializeTest;
import com.sigalhu.jse.serialize.beans.Person;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author huxujun
 * @date 2019/9/28
 */
public class ProtobufUtilsTest extends SerializeTest {

    @Test
    public void testProto2() throws Exception {
        byte[] bytes = ProtobufUtils.toBytes(person);
        Person p = ProtobufUtils.parseBytes(bytes);
        Assert.assertNotEquals(person, p);
        person.setUpdateTime(null);
        Assert.assertEquals(person, p);
    }

    @Test
    public void testProto2List() {
        byte[] bytes = ProtobufUtils.toDelimitedBytes(people);
        List<Person> ps = ProtobufUtils.parseDelimitedBytes(bytes);
        Assert.assertNotEquals(people, ps);
        people.forEach(p -> p.setUpdateTime(null));
        Assert.assertEquals(people, ps);
    }

    @Test
    public void testProto3() throws Exception {
        byte[] bytes = ProtobufUtils.toBytes3(person);
        Person p = ProtobufUtils.parseBytes3(bytes);
        Assert.assertNotEquals(person, p);
        person.setUpdateTime(null);
        Assert.assertEquals(person, p);
    }

    @Test
    public void testProto3List() {
        byte[] bytes = ProtobufUtils.toDelimitedBytes3(people);
        List<Person> ps = ProtobufUtils.parseDelimitedBytes3(bytes);
        Assert.assertNotEquals(people, ps);
        people.forEach(p -> p.setUpdateTime(null));
        Assert.assertEquals(people, ps);
    }

    @Test
    public void testProto2To3() throws Exception {
        byte[] bytes = ProtobufUtils.toBytes(person);
        Person p = ProtobufUtils.parseBytes3(bytes);
        Assert.assertNotEquals(person, p);
        person.setUpdateTime(null);
        Assert.assertEquals(person, p);
    }

    @Test
    public void testProto2To3List() {
        byte[] bytes = ProtobufUtils.toDelimitedBytes(people);
        List<Person> ps = ProtobufUtils.parseDelimitedBytes3(bytes);
        Assert.assertNotEquals(people, ps);
        people.forEach(p -> p.setUpdateTime(null));
        Assert.assertEquals(people, ps);
    }
}