package com.ibessonov.typesafe.base;

import java.util.function.Function;

/**
 * @author ibessonov
 */
@FunctionalInterface
public interface Not<T> extends Function<T, Bottom> {
}
