package com.ibessonov.typesafe.vector;

import com.ibessonov.typesafe.nat.Nat;
import com.ibessonov.typesafe.nat.S;
import com.ibessonov.typesafe.nat.Z;
import com.ibessonov.typesafe.sametype.SameType;

/**
 * @author ibessonov
 */
public interface Vector<N extends Nat<N>, T> {

    static <T> Vector<Z, T> nil() {
        return new Nil<>();
    }

    static <T, N extends Nat<N>> Vector<S<N>, T> cons(T head, Vector<N, T> tail) {
        return new Cons<>(head, tail);
    }

    @SuppressWarnings("unchecked")
    static <N extends Nat<N>, M extends Nat<M>, T>
    SameType<Vector<N, T>, Vector<M, T>> vecSameType(SameType<N, M> eq) {
        return (SameType) eq;
    }

    <Result> Result match(VectorMatcher<N, T, Result> matcher);
}
