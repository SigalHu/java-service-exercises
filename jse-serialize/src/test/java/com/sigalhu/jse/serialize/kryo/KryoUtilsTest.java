package com.sigalhu.jse.serialize.kryo;

import com.sigalhu.jse.serialize.SerializeTest;
import com.sigalhu.jse.serialize.beans.Person;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author huxujun
 * @date 2019/9/29
 */
public class KryoUtilsTest extends SerializeTest {

    @Test
    public void test() {
        byte[] bytes = KryoUtils.toBytes(person);
        Person p = KryoUtils.parseBytes(bytes);
        Assert.assertEquals(person, p);
    }
}