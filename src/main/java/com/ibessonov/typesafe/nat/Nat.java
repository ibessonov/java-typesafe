package com.ibessonov.typesafe.nat;

/**
 * @author ibessonov
 */
public interface Nat<N extends Nat<N>> {

    static Z zero() {
        return new Z();
    }

    static <P extends Nat<P>> S<P> succ(P p) {
        return new S<>(p);
    }

    <Result> Result natMatch(NatMatcher<N, Result> matcher);
}
