package com.ibessonov.typesafe.dec;

import com.ibessonov.typesafe.base.Not;

/**
 * @author ibessonov
 */
public interface Dec<T> {

    static <T> Dec<T> yes(T prf) {
        return new Yes<>(prf);
    }

    static <T> Dec<T> no(Not<T> prf) {
        return new No<>(prf);
    }

    <Result> Result match(DecMatcher<T, Result> matcher);
}
