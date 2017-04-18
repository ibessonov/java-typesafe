package com.ibessonov.typesafe.dec;

import com.ibessonov.typesafe.base.Not;

import java.util.function.Function;

/**
 * @author ibessonov
 */
public interface DecUtil {

    static <T, Result> Result match(Dec<T> dec, Function<T, Result> yes, Function<Not<T>, Result> no) {
        return dec.match(new DecMatcher<T, Result>() {
            @Override
            public Result caseYes(T prf) {
                return yes.apply(prf);
            }

            @Override
            public Result caseNo(Not<T> prf) {
                return no.apply(prf);
            }
        });
    }

    static <T> boolean isYes(Dec<T> dec) {
        return match(dec, yes -> true, no -> false);
    }

    static <T> boolean isNo(Dec<T> dec) {
        return match(dec, yes -> false, no -> true);
    }
}
