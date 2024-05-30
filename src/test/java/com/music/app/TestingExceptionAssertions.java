package com.music.app;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class TestingExceptionAssertions {

    public static final String THE_EXPECTED_EXCEPTION_WAS = "The expected exception was ";
    public static final String BUT_THIS_WAS_THROWN = " but this was thrown ";

    public static <T> void assertThrows(Supplier<T> supplier, Class<? extends Exception> exception, String message) {
        try {
            supplier.get();
            fail();
        } catch (Exception e) {
            assertTrue(exception.isInstance(e), THE_EXPECTED_EXCEPTION_WAS + exception.getCanonicalName() + BUT_THIS_WAS_THROWN
                    + e.getClass().getCanonicalName());
            assertTrue(e.getMessage().contains(message));
        }
    }

    public static void assertThrows(Thunk thunk, Class<? extends Exception> exception, String message) {
        try {
            thunk.execute();
            fail();
        } catch (Exception e) {
            assertTrue(exception.isInstance(e), THE_EXPECTED_EXCEPTION_WAS + exception.getCanonicalName() + BUT_THIS_WAS_THROWN
                    + e.getClass().getCanonicalName());
            assertTrue(e.getMessage().contains(message));
        }
    }

    @FunctionalInterface
    public interface Thunk {
        void execute();
    }
}
