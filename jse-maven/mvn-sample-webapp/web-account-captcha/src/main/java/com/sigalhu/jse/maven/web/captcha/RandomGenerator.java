package com.sigalhu.jse.maven.web.captcha;

import java.util.Random;

public class RandomGenerator {
    private static String range = "0123456789abcdefghijklmnopqrstuvwxyz";

    public static synchronized String getRandomString() {
        Random random = new Random();

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            result.append(range.charAt(random.nextInt(range.length())));
        }
        return result.toString();
    }
}