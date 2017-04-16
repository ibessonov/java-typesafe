package com.ibessonov.typesafe.vector;

import com.ibessonov.typesafe.num.Num;
import com.ibessonov.typesafe.num.S;
import com.ibessonov.typesafe.num.Z;
import com.ibessonov.typesafe.sametype.SameType;

/**
 * @author ibessonov
 */
public interface Vector<N extends Num, T> {

    static <T> Vector<Z, T> nil() {
        return new Nil<>();
    }

    static <T, N extends Num> Vector<S<N>, T> cons(T head, Vector<N, T> tail) {
        return new Cons<>(head, tail);
    }

    @SuppressWarnings("unchecked")
    static <N extends Num, M extends Num, T>
    SameType<Vector<N, T>, Vector<M, T>> vecSameType(SameType<N, M> eq) {
        return (SameType) eq;
    }

    <Result> Result match(VectorMatcher<N, T, Result> matcher);

    interface VectorMatcher<N extends Num, T, Result> {
        Result caseNil(Nil<T> v, SameType<N, Z> prf);
        <P extends Num> Result caseCons(Cons<P, T> v, SameType<S<P>, N> prf);
    }
}
