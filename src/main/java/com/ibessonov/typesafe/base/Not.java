package com.ibessonov.typesafe.base;

import java.util.function.Function;

/**
 * Function {@code T -> Void} is the equivalent of logical negation {@code ¬T ≡ T → false}
 * @author ibessonov
 */
@FunctionalInterface
public interface Not<T> extends Function<T, Void> {
}
