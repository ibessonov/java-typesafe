package com.ibessonov.typesafe.nat.fin;

import com.ibessonov.typesafe.nat.Nat;
import com.ibessonov.typesafe.nat.S;

import static com.ibessonov.typesafe.sametype.SameType.sameType;

/**
 * @author ibessonov
 */
public class FS<K extends Nat<K>> implements Fin<S<K>> {

    private final Fin<K> fin;

    FS(Fin<K> fin) {
        this.fin = fin;
    }

    @Override
    public <Result> Result match(FinMatcher<S<K>, Result> matcher) {
        return matcher.caseFs(fin, sameType());
    }
}
