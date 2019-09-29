package com.sigalhu.jse.serialize.protobuf;

import com.sigalhu.jse.serialize.SerializeTest;
import com.sigalhu.jse.serialize.beans.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author huxujun
 * @date 2019/9/28
 */
public class ProtobufUtilsTest extends SerializeTest {

    @Test
    public void test() throws Exception {
        byte[] bytes = ProtobufUtils.toBytes(person);
        Person p = ProtobufUtils.parseBytes(bytes);
        Assert.assertNotEquals(person, p);
        person.setUpdateTime(null);
        Assert.assertEquals(person, p);
    }

    @Test
    public void testList() {
        byte[] bytes = ProtobufUtils.toDelimitedBytes(people);
        List<Person> ps = ProtobufUtils.parseDelimitedBytes(bytes);
        Assert.assertNotEquals(people, ps);
        people.forEach(p -> p.setUpdateTime(null));
        Assert.assertEquals(people, ps);
    }
}