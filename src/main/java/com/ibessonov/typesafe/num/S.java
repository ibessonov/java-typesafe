package com.ibessonov.typesafe.num;

/**
 * @author ibessonov
 */
public class S<P extends Num> implements Num {
    private final P prev;
    S(P p) {
        prev = p;
    }

    public P prev() {
        return prev;
    }
}
