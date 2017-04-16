package com.ibessonov.typesafe.vector;

import com.ibessonov.typesafe.num.Num;
import com.ibessonov.typesafe.num.S;

import static com.ibessonov.typesafe.sametype.SameType.sameType;

/**
 * @author ibessonov
 */
public class Cons<N extends Num, T> implements Vector<S<N>, T> {
    private final T head;
    private final Vector<N, T> tail;

    Cons(T head, Vector<N, T> tail) {
        this.head = head;
        this.tail = tail;
    }

    public T head() {
        return head;
    }

    public Vector<N, T> tail() {
        return tail;
    }

    @Override
    public <Result> Result match(VectorMatcher<S<N>, T, Result> matcher) {
        return matcher.caseCons(this, sameType());
    }
}
