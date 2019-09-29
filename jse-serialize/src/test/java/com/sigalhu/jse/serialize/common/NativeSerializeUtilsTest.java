package com.sigalhu.jse.serialize.common;

import com.sigalhu.jse.serialize.SerializeTest;
import com.sigalhu.jse.serialize.beans.Person;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * @author huxujun
 * @date 2019/9/29
 */
public class NativeSerializeUtilsTest extends SerializeTest {

    @Test
    public void test() throws IOException, ClassNotFoundException {
        byte[] bytes = NativeSerializeUtils.toBytes(person);
        Person p = NativeSerializeUtils.parseBytes(bytes);
        Assert.assertEquals(person, p);
    }
}