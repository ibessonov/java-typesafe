package com.ibessonov.typesafe.num.fin;

import com.ibessonov.typesafe.num.Num;
import com.ibessonov.typesafe.num.S;

import static com.ibessonov.typesafe.sametype.SameType.sameType;

/**
 * @author ibessonov
 */
public class FS<K extends Num> implements Fin<S<K>> {
    private final Fin<K> fin;

    FS(Fin<K> fin) {
        this.fin = fin;
    }

    public Fin<K> fin() {
        return fin;
    }

    @Override
    public <Result> Result match(FinMatcher<S<K>, Result> matcher) {
        return matcher.caseFs(this, sameType());
    }
}
