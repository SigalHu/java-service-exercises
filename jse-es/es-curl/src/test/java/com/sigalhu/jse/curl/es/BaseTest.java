package com.sigalhu.jse.curl.es;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author huxujun
 * @date 2020/2/2
 */
public class BaseTest {

    @Test
    public void test() {
        exec("curl localhost:9200");
    }

    public static void exec(String cmd) {
        try {
            Process p = Runtime.getRuntime().exec(new String[]{"sh", "-c", cmd});
            p.waitFor();
            System.out.println(cmd);
            System.out.println("\nThe response is ...");
            print(p.getInputStream());
        } catch (InterruptedException|IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void print(InputStream inputStream) {
        try (
                InputStreamReader streamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(streamReader);
        ) {
            reader.lines().forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
