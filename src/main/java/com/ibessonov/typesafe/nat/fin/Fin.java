package com.ibessonov.typesafe.nat.fin;

import com.ibessonov.typesafe.nat.Nat;
import com.ibessonov.typesafe.nat.S;

/**
 * @author ibessonov
 */
public interface Fin<N extends Nat<N>> {

    static <K extends Nat<K>> Fin<S<K>> fz() {
        return new FZ<>();
    }

    static <K extends Nat<K>> Fin<S<K>> fs(Fin<K> fin) {
        return new FS<>(fin);
    }

    <Result> Result match(FinMatcher<N, Result> matcher);
}
