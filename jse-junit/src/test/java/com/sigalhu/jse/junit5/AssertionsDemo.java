package com.sigalhu.jse.junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

/**
 * @author huxujun
 * @date 2018/9/16
 */
public class AssertionsDemo {

    private Person person = new Person("John", "Doe");

    @Test
    void standardAssertions() {
        Assertions.assertEquals(2, 2);
        Assertions.assertEquals(4, 4, "The optional assertion message is now the last parameter.");
        Assertions.assertTrue('a' < 'b', () -> "Assertion messages can be lazily evaluated -- "
                + "to avoid constructing complex messages unnecessarily.");
    }

    @Test
    void groupedAssertions() {
        // In a grouped assertion all assertions are executed, and any
        // failures will be reported together.
        Assertions.assertAll("person",
                () -> Assertions.assertEquals("John", person.getFirstName()),
                () -> Assertions.assertEquals("Doe", person.getLastName())
        );
    }

    @Test
    void dependentAssertions() {
        // Within a code block, if an assertion fails the
        // subsequent code in the same block will be skipped.
        Assertions.assertAll("properties",
                () -> {
                    String firstName = person.getFirstName();
                    Assertions.assertNotNull(firstName);

                    // Executed only if the previous assertion is valid.
                    Assertions.assertAll("first name",
                            () -> Assertions.assertTrue(firstName.startsWith("J")),
                            () -> Assertions.assertTrue(firstName.endsWith("n"))
                    );
                },
                () -> {
                    // Grouped assertion, so processed independently
                    // of results of first name assertions.
                    String lastName = person.getLastName();
                    Assertions.assertNotNull(lastName);

                    // Executed only if the previous assertion is valid.
                    Assertions.assertAll("last name",
                            () -> Assertions.assertTrue(lastName.startsWith("D")),
                            () -> Assertions.assertTrue(lastName.endsWith("e"))
                    );
                }
        );
    }

    @Test
    void exceptionTesting() {
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("a message");
        });
        Assertions.assertEquals("a message", exception.getMessage());
    }

    @Test
    void timeoutNotExceeded() {
        // The following assertion succeeds.
        Assertions.assertTimeout(Duration.ofSeconds(2), () -> {
            Thread.sleep(1000);
            // Perform task that takes less than 2 minutes.
        });
    }

    @Test
    void timeoutNotExceededWithResult() {
        // The following assertion succeeds, and returns the supplied object.
        String actualResult = Assertions.assertTimeout(Duration.ofSeconds(2), () -> {
            Thread.sleep(1000);
            return "a result";
        });
        Assertions.assertEquals("a result", actualResult);
    }

    @Test
    void timeoutNotExceededWithMethod() {
        // The following assertion invokes a method reference and returns an object.
        String actualGreeting = Assertions.assertTimeout(Duration.ofMinutes(2), AssertionsDemo::greeting);
        Assertions.assertEquals("Hello, World!", actualGreeting);
    }

    @Test
    void timeoutExceeded() {
        // The following assertion fails with an error message similar to:
        // execution exceeded timeout of 10 ms by 91 ms
        Assertions.assertTimeout(Duration.ofMillis(10), () -> {
            // Simulate task that takes more than 10 ms.
            Thread.sleep(100);
        });
    }

    @Test
    void timeoutExceededWithPreemptiveTermination() {
        // The following assertion fails with an error message similar to:
        // execution timed out after 10 ms
        Assertions.assertTimeoutPreemptively(Duration.ofMillis(10), () -> {
            // Simulate task that takes more than 10 ms.
            Thread.sleep(100);
        });
    }

    private static String greeting() {
        return "Hello, World!";
    }
}
