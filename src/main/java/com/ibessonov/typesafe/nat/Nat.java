package com.ibessonov.typesafe.nat;

/**
 * Type that represents natural numbers with zero. Each number is either zero or another nat plus one
 * @param <N> should be the same type as current instance, e.g. {@code Nat<Z>} is actually  {@code Z}
 *           and {@code Nat<S<S<Z>>>} is {@code S<S<Z>>}
 * @author ibessonov
 */
public abstract class Nat<N extends Nat<N>> {

    /**
     * @return {@code Nat<Z>} object that represents number 0
     */
    public static Z zero() {
        return new Z();
    }

    /**
     * Increment operation for type Nat
     * @param p number to increment
     * @param <P> type of the number
     * @return number of type {@code S<P>} which means that is is the next number after {@code p}
     */
    public static <P extends Nat<P>> S<P> succ(P p) {
        return new S<>(p);
    }

    /**
     * @return the same object but casted to its generic type parameter
     */
    public abstract N cast();

    /**
     * Pattern matching
     * @param matcher matcher of appropriate type
     * @param <Result> type of returning value deduced from passed matcher
     * @return matching result
     */
    public abstract <Result> Result natMatch(NatMatcher<N, Result> matcher);

    /**
     * Package-protected method in abstract class guarantees that one cannot inherit this class outside the package
     */
    abstract void inheritanceProtection();
}
