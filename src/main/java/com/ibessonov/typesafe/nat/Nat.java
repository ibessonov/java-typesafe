package com.ibessonov.typesafe.nat;

/**
 * @author ibessonov
 */
public abstract class Nat<N extends Nat<N>> {

    public static Z zero() {
        return new Z();
    }

    public static <P extends Nat<P>> S<P> succ(P p) {
        return new S<>(p);
    }

    public abstract <Result> Result natMatch(NatMatcher<N, Result> matcher);

    /**
     * Package-protected method in abstract class guarantees that one cannot inherit this class outside the package
     */
    abstract void inheritanceProtection();
}
