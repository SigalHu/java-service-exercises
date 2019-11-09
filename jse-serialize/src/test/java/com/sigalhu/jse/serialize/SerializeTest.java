package com.sigalhu.jse.serialize;

import com.sigalhu.jse.serialize.beans.Person;
import org.junit.Before;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author huxujun
 * @date 2019/9/28
 */
public class SerializeTest {

    protected Person person;
    protected List<Person> people;

    private Random random = new Random();

    @Before
    public void setUp() throws Exception {
        person = newPerson();
        people = Arrays.asList(newPerson(), newPerson());
    }

    private Person newPerson() {
        Person person = new Person();
        person.setId(random.nextLong());
        person.setName("name" + random.nextLong());
        person.setAge(random.nextInt());
        person.setHeight(random.nextDouble());
        person.setCreateTime(new Date());
        person.setUpdateTime(new Date());
        return person;
    }
}
