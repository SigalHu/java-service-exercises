package com.sigalhu.jse.serialize.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.BeanSerializer;
import com.sigalhu.jse.serialize.beans.Person;

import java.util.Date;

/**
 * @author huxujun
 * @date 2019/9/29
 */
public class KryoUtils {
    private static final Kryo kryo = new Kryo();
    private static final Output output = new Output(256, 10 * 1024);
    private static final Input input = new Input();

    static {
        kryo.register(Person.class, new BeanSerializer(kryo, Person.class));
        kryo.register(Date.class, new BeanSerializer(kryo, Date.class));
    }

    public static byte[] toBytes(Person person) {
        try {
            kryo.writeObject(output, person);
            return output.toBytes();
        } finally {
            output.reset();
        }
    }

    public static Person parseBytes(byte[] bytes) {
        input.setBuffer(bytes);
        return kryo.readObject(input, Person.class);
    }
}
