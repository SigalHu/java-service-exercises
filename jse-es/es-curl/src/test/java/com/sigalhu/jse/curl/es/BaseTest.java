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
    public void test() throws IOException, InterruptedException {
        Process p = Runtime.getRuntime().exec("curl localhost:9200");
        p.waitFor();
        print(p.getInputStream());
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
