package com.ibessonov.typesafe.num.fin;

import com.ibessonov.typesafe.num.Num;
import com.ibessonov.typesafe.num.S;
import com.ibessonov.typesafe.num.Z;
import com.ibessonov.typesafe.sametype.SameType;

/**
 * @author ibessonov
 */
public interface Fin<N extends Num> {

    static <K extends Num> Fin<S<K>> fz() {
        return new FZ<>();
    }

    static <K extends Num> Fin<S<K>> fs(Fin<K> fin) {
        return new FS<>(fin);
    }

    <Result> Result match(FinMatcher<N, Result> matcher);

    interface FinMatcher<N extends Num, Result> {
        <K extends Num> Result caseFz(FZ<K> fz, SameType<S<K>, N> prf);
        <K extends Num> Result caseFs(FS<K> fz, SameType<S<K>, N> prf);
    }

    @SuppressWarnings("unchecked")
    static <P extends Num, Q extends Num>
    SameType<Fin<P>, Fin<Q>> finSameType(SameType<P, Q> prf) {
        return (SameType) prf;
    }

    static <Result> Result finError(Fin<Z> fin) {
        throw new AssertionError(fin);
    }
}
