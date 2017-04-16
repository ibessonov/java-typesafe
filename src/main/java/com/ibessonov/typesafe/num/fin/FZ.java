package com.ibessonov.typesafe.num.fin;

import com.ibessonov.typesafe.num.Num;
import com.ibessonov.typesafe.num.S;

import static com.ibessonov.typesafe.sametype.SameType.sameType;

/**
 * @author ibessonov
 */
public class FZ<K extends Num> implements Fin<S<K>> {
    FZ() {}

    @Override
    public <Result> Result match(FinMatcher<S<K>, Result> matcher) {
        return matcher.caseFz(this, sameType());
    }
}
