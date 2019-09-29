package com.sigalhu.jse.serialize.protostuff;

import com.sigalhu.jse.serialize.beans.Person;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author huxujun
 * @date 2019/9/28
 */
public class ProtostuffUtils {

    private static final Schema<Person> schema = RuntimeSchema.getSchema(Person.class);
    private static final LinkedBuffer buff = LinkedBuffer.allocate();

    public static byte[] toBytes(Person person) {
        try {
            return ProtobufIOUtil.toByteArray(person, schema, buff);
        } finally {
            buff.clear();
        }
    }

    public static Person parseBytes(byte[] bytes) {
        Person person = schema.newMessage();
        ProtobufIOUtil.mergeFrom(bytes, person, schema);
        return person;
    }

    public static byte[] toDelimitedBytes(List<Person> people) {
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            ProtobufIOUtil.writeListTo(output, people, schema, buff);
            return output.toByteArray();
        } catch (IOException ex) {
            throw new RuntimeException();
        } finally {
            buff.clear();
        }
    }

    public static List<Person> parseDelimitedBytes(byte[] bytes) {
        try (ByteArrayInputStream input = new ByteArrayInputStream(bytes)) {
            return ProtobufIOUtil.parseListFrom(input, schema);
        } catch (IOException ex) {
            throw new RuntimeException();
        }
    }

    public static byte[] toBytesV2(Person person) {
        try {
            return ProtostuffIOUtil.toByteArray(person, schema, buff);
        } finally {
            buff.clear();
        }
    }

    public static Person parseBytesV2(byte[] bytes) {
        Person person = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(bytes, person, schema);
        return person;
    }

    public static byte[] toDelimitedBytesV2(List<Person> people) {
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            ProtostuffIOUtil.writeListTo(output, people, schema, buff);
            return output.toByteArray();
        } catch (IOException ex) {
            throw new RuntimeException();
        } finally {
            buff.clear();
        }
    }

    public static List<Person> parseDelimitedBytesV2(byte[] bytes) {
        try (ByteArrayInputStream input = new ByteArrayInputStream(bytes)) {
            return ProtostuffIOUtil.parseListFrom(input, schema);
        } catch (IOException ex) {
            throw new RuntimeException();
        }
    }
}
