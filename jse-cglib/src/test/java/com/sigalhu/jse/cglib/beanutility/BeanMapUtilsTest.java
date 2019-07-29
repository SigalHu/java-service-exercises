package com.sigalhu.jse.cglib.beanutility;

import com.sigalhu.jse.cglib.targetobject.DogImpl;
import com.sigalhu.jse.cglib.targetobject.Person;
import net.sf.cglib.beans.BeanMap;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

/**
 * @author huxujun
 * @date 2018/11/18
 */
public class BeanMapUtilsTest {

    @Test
    public void convertToMap() {
        DogImpl dog = new DogImpl("BeanMapUtilsTest");
        Map<String, Object> map = BeanMapUtils.convertToMap(dog);
        Assert.assertEquals("BeanMapUtilsTest", map.get("name"));

        map.put("name", "BeanMapUtilsTest2");
        Assert.assertEquals("BeanMapUtilsTest2", dog.getName());

        map.put("dogName", "BeanMapUtilsTest3");
        Assert.assertEquals("BeanMapUtilsTest3", dog.getName());
    }

    private static long count = 1000000L;
    private static Person person = new Person();

    @BeforeClass
    public static void resetPerson() {
        person.setId(1L);
        person.setName("person");
        person.setAge(2);
        person.setHeight(3D);
        person.setWeight(4D);
        person.setPhone("12345679800");
    }

    @Test
    public void testCost() {
        long total = 0L;
        for (int i = 0; i < 10; i++) {
            total += createFunction();
        }
        System.out.println("====== total cost ======");
        System.out.println("平均耗时：" + total / 10D + "ms");
    }

    private static long keyCount = 100;
    private static Map<String, Long> hashMap = new HashMap<>();
    static {
        Random random = new Random();
        for (int i = 0; i < keyCount; i++) {
            hashMap.put("id-" + i, random.nextLong());
        }
    }

    private static long getHashMap() {
        long result = ThreadLocalRandom.current().nextLong();
        System.out.println("====== get hash map ======");
        long cost = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < keyCount; j++) {
                result += hashMap.get("id-" + j);
            }
        }
        cost = System.currentTimeMillis() - cost;
        System.out.println("耗时：" + cost + "ms");
        System.out.println("result=" + result);
        return cost;
    }

    private static long putHashMap() {
        long result = ThreadLocalRandom.current().nextLong();
        System.out.println("====== put hash map ======");
        long cost = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < keyCount; j++) {
                hashMap.put("id-" + j, result + j);
            }
        }
        cost = System.currentTimeMillis() - cost;
        System.out.println("耗时：" + cost + "ms");
        System.out.println("result=" + result);
        return cost;
    }

    private static Map<String, Long> concurrentHashMap = new ConcurrentHashMap<>();
    static {
        Random random = new Random();
        for (int i = 0; i < keyCount; i++) {
            concurrentHashMap.put("id-" + i, random.nextLong());
        }
    }

    private static long getConcurrentHashMap() {
        long result = ThreadLocalRandom.current().nextLong();
        System.out.println("====== get concurrent hash map ======");
        long cost = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < keyCount; j++) {
                result += concurrentHashMap.get("id-" + j);
            }
        }
        cost = System.currentTimeMillis() - cost;
        System.out.println("耗时：" + cost + "ms");
        System.out.println("result=" + result);
        return cost;
    }

    private static long putConcurrentHashMap() {
        long result = ThreadLocalRandom.current().nextLong();
        System.out.println("====== put concurrent hash map ======");
        long cost = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < keyCount; j++) {
                concurrentHashMap.put("id-" + j, result + j);
            }
        }
        cost = System.currentTimeMillis() - cost;
        System.out.println("耗时：" + cost + "ms");
        System.out.println("result=" + result);
        return cost;
    }

    private static Map<String, Object> useCglibMap = BeanMap.create(person);

    private static long useCglib() {
        long result = ThreadLocalRandom.current().nextLong();
        System.out.println("====== use cglib ======");
        long cost = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            result += (Long) useCglibMap.get("id");
            result += useCglibMap.get("name").hashCode();
            result += (Integer) useCglibMap.get("age");
            result += ((Double) useCglibMap.get("height")).longValue();
            result += ((Double) useCglibMap.get("weight")).longValue();
            result += useCglibMap.get("phone").hashCode();
        }
        cost = System.currentTimeMillis() - cost;
        System.out.println("耗时：" + cost + "ms");
        System.out.println("result=" + result);
        return cost;
    }

    private static long invokeGetter() {
        long result = ThreadLocalRandom.current().nextLong();
        System.out.println("====== invoke getter ======");
        long cost = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            result += person.getId();
            result += person.getName().hashCode();
            result += person.getAge();
            result += person.getHeight().longValue();
            result += person.getWeight().longValue();
            result += person.getPhone().hashCode();
        }
        cost = System.currentTimeMillis() - cost;
        System.out.println("耗时：" + cost + "ms");
        System.out.println("result=" + result);
        return cost;
    }

    private static Map<String, Method> reflectMethodMap = new HashMap<String, Method>() {
        {
            try {
                put("id", Person.class.getDeclaredMethod("getId"));
                put("name", Person.class.getDeclaredMethod("getName"));
                put("age", Person.class.getDeclaredMethod("getAge"));
                put("height", Person.class.getDeclaredMethod("getHeight"));
                put("weight", Person.class.getDeclaredMethod("getWeight"));
                put("phone", Person.class.getDeclaredMethod("getPhone"));
            } catch (Throwable ex) {
            }
        }
    };

    private static long reflectMethod() throws Exception {
        long result = ThreadLocalRandom.current().nextLong();
        System.out.println("====== reflect method ======");
        Method idMethod = reflectMethodMap.get("id");
        Method nameMethod = reflectMethodMap.get("name");
        Method ageMethod = reflectMethodMap.get("age");
        Method heightMethod = reflectMethodMap.get("height");
        Method weightMethod = reflectMethodMap.get("weight");
        Method phoneMethod = reflectMethodMap.get("phone");
        long cost = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            result += (Long) idMethod.invoke(person);
            result += nameMethod.invoke(person).hashCode();
            result += (Integer) ageMethod.invoke(person);
            result += ((Double) heightMethod.invoke(person)).longValue();
            result += ((Double) weightMethod.invoke(person)).longValue();
            result += phoneMethod.invoke(person).hashCode();
        }
        cost = System.currentTimeMillis() - cost;
        System.out.println("耗时：" + cost + "ms");
        System.out.println("result=" + result);
        return cost;
    }

    private static long invokeFunction() {
        long result = ThreadLocalRandom.current().nextLong();
        System.out.println("====== invoke function ======");
        Function<Person, Long> idFunction = Person::getId;
        Function<Person, String> nameFunction = Person::getName;
        Function<Person, Integer> ageFunction = Person::getAge;
        Function<Person, Double> heightFunction = Person::getHeight;
        Function<Person, Double> weightFunction = Person::getWeight;
        Function<Person, String> phoneFunction = Person::getPhone;
        long cost = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            result += idFunction.apply(person);
            result += nameFunction.apply(person).hashCode();
            result += ageFunction.apply(person);
            result += heightFunction.apply(person).longValue();
            result += weightFunction.apply(person).longValue();
            result += phoneFunction.apply(person).hashCode();
        }
        cost = System.currentTimeMillis() - cost;
        System.out.println("耗时：" + cost + "ms");
        System.out.println("result=" + result);
        return cost;
    }

    private static Map<String, Field> reflectFieldMap = new HashMap<String, Field>(){
        {
            try {
                Field idField = Person.class.getDeclaredField("id");
                idField.setAccessible(true);
                put("id", idField);
                Field nameField = Person.class.getDeclaredField("name");
                nameField.setAccessible(true);
                put("name", nameField);
                Field ageField = Person.class.getDeclaredField("age");
                ageField.setAccessible(true);
                put("age", ageField);
                Field heightField = Person.class.getDeclaredField("height");
                heightField.setAccessible(true);
                put("height", heightField);
                Field weightField = Person.class.getDeclaredField("weight");
                weightField.setAccessible(true);
                put("weight", weightField);
                Field phoneField = Person.class.getDeclaredField("phone");
                phoneField.setAccessible(true);
                put("phone", phoneField);
            } catch (Throwable ex) {
            }
        }
    };

    private static long reflectField() throws Exception {
        long result = ThreadLocalRandom.current().nextLong();
        System.out.println("====== reflect field ======");
        Field idField = reflectFieldMap.get("id");
        Field nameField = reflectFieldMap.get("name");
        Field ageField = reflectFieldMap.get("age");
        Field heightField = reflectFieldMap.get("height");
        Field weightField = reflectFieldMap.get("weight");
        Field phoneField = reflectFieldMap.get("phone");
        long cost = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            result += (Long) idField.get(person);
            result += nameField.get(person).hashCode();
            result += (Integer) ageField.get(person);
            result += ((Double) heightField.get(person)).longValue();
            result += ((Double) weightField.get(person)).longValue();
            result += phoneField.get(person).hashCode();
        }
        cost = System.currentTimeMillis() - cost;
        System.out.println("耗时：" + cost + "ms");
        System.out.println("result=" + result);
        return cost;
    }

    private static Map<String, Method> reflectPropertyMap = new HashMap<String, Method>() {
        {
            try {
                put("id", new PropertyDescriptor("id", Person.class).getReadMethod());
                put("name", new PropertyDescriptor("name", Person.class).getReadMethod());
                put("age", new PropertyDescriptor("age", Person.class).getReadMethod());
                put("height", new PropertyDescriptor("height", Person.class).getReadMethod());
                put("weight", new PropertyDescriptor("weight", Person.class).getReadMethod());
                put("phone", new PropertyDescriptor("phone", Person.class).getReadMethod());
            } catch (Throwable ex) {
            }
        }
    };

    private static long reflectProperty() throws Exception {
        long result = ThreadLocalRandom.current().nextLong();
        System.out.println("====== reflect property ======");
        Method idMethod = reflectPropertyMap.get("id");
        Method nameMethod = reflectPropertyMap.get("name");
        Method ageMethod = reflectPropertyMap.get("age");
        Method heightMethod = reflectPropertyMap.get("height");
        Method weightMethod = reflectPropertyMap.get("weight");
        Method phoneMethod = reflectPropertyMap.get("phone");
        long cost = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            result += (Long) idMethod.invoke(person);
            result += nameMethod.invoke(person).hashCode();
            result += (Integer) ageMethod.invoke(person);
            result += ((Double) heightMethod.invoke(person)).longValue();
            result += ((Double) weightMethod.invoke(person)).longValue();
            result += phoneMethod.invoke(person).hashCode();
        }
        cost = System.currentTimeMillis() - cost;
        System.out.println("耗时：" + cost + "ms");
        System.out.println("result=" + result);
        return cost;
    }

    private static Map<String, MethodHandle> lookupMethodMap = new HashMap<String, MethodHandle>() {
        {
            try {
                MethodHandles.Lookup lookup = MethodHandles.lookup();
                put("id", lookup.findVirtual(Person.class, "getId", MethodType.methodType(Long.class)));
                put("name", lookup.findVirtual(Person.class, "getName", MethodType.methodType(String.class)));
                put("age", lookup.findVirtual(Person.class, "getAge", MethodType.methodType(Integer.class)));
                put("height", lookup.findVirtual(Person.class, "getHeight", MethodType.methodType(Double.class)));
                put("weight", lookup.findVirtual(Person.class, "getWeight", MethodType.methodType(Double.class)));
                put("phone", lookup.findVirtual(Person.class, "getPhone", MethodType.methodType(String.class)));
            } catch (Throwable ex) {
            }
        }
    };

    private static long lookupMethod() throws Throwable {
        long result = ThreadLocalRandom.current().nextLong();
        System.out.println("====== lookup method ======");
        MethodHandle idMethod = lookupMethodMap.get("id");
        MethodHandle nameMethod = lookupMethodMap.get("name");
        MethodHandle ageMethod = lookupMethodMap.get("age");
        MethodHandle heightMethod = lookupMethodMap.get("height");
        MethodHandle weightMethod = lookupMethodMap.get("weight");
        MethodHandle phoneMethod = lookupMethodMap.get("phone");
        long cost = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            result += (Long) idMethod.invoke(person);
            result += nameMethod.invoke(person).hashCode();
            result += (Integer) ageMethod.invoke(person);
            result += ((Double) heightMethod.invoke(person)).longValue();
            result += ((Double) weightMethod.invoke(person)).longValue();
            result += phoneMethod.invoke(person).hashCode();
        }
        cost = System.currentTimeMillis() - cost;
        System.out.println("耗时：" + cost + "ms");
        System.out.println("result=" + result);
        return cost;
    }

    private static Map<String, Function<Person, Object>> getterMap = getters(Person.class);

    @SuppressWarnings("unchecked")
    private static <T> Map<String, Function<T, Object>> getters(Class<T> clazz) {
        try {
            Map<String, Function<T, Object>> getterMap = new HashMap<>();
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            MethodType getter = MethodType.methodType(Function.class);
            MethodType getterType = MethodType.methodType(Object.class, Object.class);
            for (PropertyDescriptor descriptor : Introspector.getBeanInfo(clazz).getPropertyDescriptors()) {
                Method method = descriptor.getReadMethod();
                if ("getClass".equals(method.getName())) {
                    continue;
                }
                MethodHandle handle = lookup.findVirtual(clazz, method.getName(), MethodType.methodType(method.getReturnType()));
                getterMap.put(descriptor.getName(),
                        (Function<T, Object>) LambdaMetafactory.metafactory(
                                lookup, "apply", getter, getterType, handle, handle.type()
                        ).getTarget().invokeExact());
            }
            return getterMap;
        } catch (Throwable ex) {
            throw new IllegalStateException(ex);
        }
    }

    private static long createFunction() {
        long result = ThreadLocalRandom.current().nextLong();
        System.out.println("====== create function ======");
        Function<Person, Object> idFunction = getterMap.get("id");
        Function<Person, Object> nameFunction = getterMap.get("name");
        Function<Person, Object> ageFunction = getterMap.get("age");
        Function<Person, Object> heightFunction = getterMap.get("height");
        Function<Person, Object> weightFunction = getterMap.get("weight");
        Function<Person, Object> phoneFunction = getterMap.get("phone");
        long cost = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            result += (Long) idFunction.apply(person);
            result += nameFunction.apply(person).hashCode();
            result += (Integer) ageFunction.apply(person);
            result += ((Double) heightFunction.apply(person)).longValue();
            result += ((Double) weightFunction.apply(person)).longValue();
            result += phoneFunction.apply(person).hashCode();
        }
        cost = System.currentTimeMillis() - cost;
        System.out.println("耗时：" + cost + "ms");
        System.out.println("result=" + result);
        return cost;
    }
}