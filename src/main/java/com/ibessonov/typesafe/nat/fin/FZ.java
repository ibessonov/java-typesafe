package com.ibessonov.typesafe.nat.fin;

import com.ibessonov.typesafe.nat.Nat;
import com.ibessonov.typesafe.nat.S;

import static com.ibessonov.typesafe.sametype.SameType.sameType;

/**
 * @author ibessonov
 */
public class FZ<K extends Nat<K>> implements Fin<S<K>> {
    FZ() {}

    @Override
    public <Result> Result match(FinMatcher<S<K>, Result> matcher) {
        return matcher.caseFz(sameType());
    }
}
