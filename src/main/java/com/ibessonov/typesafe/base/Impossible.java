package com.ibessonov.typesafe.base;

/**
 * Error that has to be thrown in code that could never be executed under normal circumstances
 * @author ibessonov
 */
public class Impossible extends Error {

    /**
     * Method that should be impossible to call since Void cannot be instantiated. Assumed that parameter is not null
     * @param v impossible parameter
     * @param <T> type of return value, might be anything
     * @return nothing, {@code }Impossible} error is thrown
     */
    public static <T> T void_(Void v) {
        throw new Impossible();
    }
}
