package com.ibessonov.typesafe.util;

import java.util.function.Function;

public class Functions {

    public static <T, R> Function<T, R> constant(R r) {
        return x -> r;
    }
}
