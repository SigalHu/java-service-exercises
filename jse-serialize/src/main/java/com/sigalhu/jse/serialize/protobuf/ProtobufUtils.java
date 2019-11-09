package com.sigalhu.jse.serialize.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;
import com.sigalhu.jse.serialize.beans.Person;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author huxujun
 * @date 2019/9/28
 */
public class ProtobufUtils {

    public static byte[] toBytes(Person person) {
        PersonProto.Person p = PersonProto.Person.newBuilder()
                .setId(person.getId())
                .setName(person.getName())
                .setAge(person.getAge())
                .setHeight(person.getHeight())
                .setCreateTime(person.getCreateTime().getTime())
                .build();
        return p.toByteArray();
    }

    public static Person parseBytes(byte[] bytes) throws InvalidProtocolBufferException {
        PersonProto.Person p = PersonProto.Person.parseFrom(bytes);
        Person person = new Person();
        person.setId(p.getId());
        person.setName(p.getName());
        person.setAge(p.getAge());
        person.setHeight(p.getHeight());
        person.setCreateTime(new Date(p.getCreateTime()));
        return person;
    }

    public static byte[] toDelimitedBytes(List<Person> people) {
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            for (Person person : people) {
                PersonProto.Person p = PersonProto.Person.newBuilder()
                        .setId(person.getId())
                        .setName(person.getName())
                        .setAge(person.getAge())
                        .setHeight(person.getHeight())
                        .setCreateTime(person.getCreateTime().getTime())
                        .build();
                p.writeDelimitedTo(output);
            }
            return output.toByteArray();
        } catch (IOException ex) {
            throw new RuntimeException();
        }
    }

    public static List<Person> parseDelimitedBytes(byte[] bytes) {
        try (ByteArrayInputStream input = new ByteArrayInputStream(bytes)) {
            List<Person> people = new ArrayList<>();
            while (input.available() > 0) {
                PersonProto.Person p = PersonProto.Person.parseDelimitedFrom(input);
                Person person = new Person();
                person.setId(p.getId());
                person.setName(p.getName());
                person.setAge(p.getAge());
                person.setHeight(p.getHeight());
                person.setCreateTime(new Date(p.getCreateTime()));
                people.add(person);
            }
            return people;
        } catch (IOException ex) {
            throw new RuntimeException();
        }
    }

    public static byte[] toBytes3(Person person) {
        PersonProto3.Person3 p = PersonProto3.Person3.newBuilder()
                .setId(person.getId())
                .setName(person.getName())
                .setAge(person.getAge())
                .setHeight(person.getHeight())
                .setCreateTime(person.getCreateTime().getTime())
                .build();
        return p.toByteArray();
    }

    public static Person parseBytes3(byte[] bytes) throws InvalidProtocolBufferException {
        PersonProto3.Person3 p = PersonProto3.Person3.parseFrom(bytes);
        Person person = new Person();
        person.setId(p.getId());
        person.setName(p.getName());
        person.setAge(p.getAge());
        person.setHeight(p.getHeight());
        person.setCreateTime(new Date(p.getCreateTime()));
        return person;
    }

    public static byte[] toDelimitedBytes3(List<Person> people) {
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            for (Person person : people) {
                PersonProto3.Person3 p = PersonProto3.Person3.newBuilder()
                        .setId(person.getId())
                        .setName(person.getName())
                        .setAge(person.getAge())
                        .setHeight(person.getHeight())
                        .setCreateTime(person.getCreateTime().getTime())
                        .build();
                p.writeDelimitedTo(output);
            }
            return output.toByteArray();
        } catch (IOException ex) {
            throw new RuntimeException();
        }
    }

    public static List<Person> parseDelimitedBytes3(byte[] bytes) {
        try (ByteArrayInputStream input = new ByteArrayInputStream(bytes)) {
            List<Person> people = new ArrayList<>();
            while (input.available() > 0) {
                PersonProto3.Person3 p = PersonProto3.Person3.parseDelimitedFrom(input);
                Person person = new Person();
                person.setId(p.getId());
                person.setName(p.getName());
                person.setAge(p.getAge());
                person.setHeight(p.getHeight());
                person.setCreateTime(new Date(p.getCreateTime()));
                people.add(person);
            }
            return people;
        } catch (IOException ex) {
            throw new RuntimeException();
        }
    }
}
