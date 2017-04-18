package com.ibessonov.typesafe.vector;

import com.ibessonov.typesafe.nat.Nat;
import com.ibessonov.typesafe.nat.S;

import static com.ibessonov.typesafe.sametype.SameType.sameType;

/**
 * @author ibessonov
 */
class Cons<N extends Nat<N>, T> implements Vector<S<N>, T> {

    private final T head;
    private final Vector<N, T> tail;

    Cons(T head, Vector<N, T> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public <Result> Result match(VectorMatcher<S<N>, T, Result> matcher) {
        return matcher.caseCons(head, tail, sameType());
    }
}
