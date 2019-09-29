package com.sigalhu.jse.serialize.common;

import com.sigalhu.jse.serialize.beans.Person;

import java.io.*;

/**
 * @author huxujun
 * @date 2019/9/29
 */
public class NativeSerializeUtils {

    public static byte[] toBytes(Person person) throws IOException {
        try (
                ByteArrayOutputStream buff = new ByteArrayOutputStream();
                ObjectOutputStream out = new ObjectOutputStream(buff)
        ) {
            out.writeObject(person);
            return buff.toByteArray();
        } finally {
        }
    }

    public static Person parseBytes(byte[] bytes) throws IOException, ClassNotFoundException {
        try (
                ByteArrayInputStream buff = new ByteArrayInputStream(bytes);
                ObjectInputStream in = new ObjectInputStream(buff)
        ) {
            return (Person) in.readObject();
        } finally {
        }
    }
}
