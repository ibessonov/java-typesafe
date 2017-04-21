package com.ibessonov.typesafe.nat;

import com.ibessonov.typesafe.base.Impossible;
import com.ibessonov.typesafe.sametype.SameType;

/**
 * Methods to manipulate with {@link SameType} objects with {@link Nat} type parameters
 * @author ibessonov
 */
public interface NatSameType {

    /**
     * Proof that if {@code p+1 = q+1}, then {@code p = q}
     * @param prf proof that {@code p+1 = q+1}
     * @param <P> type of number {@code p}
     * @param <Q> type of number {@code q}
     * @return proof that {@code p = q}
     */
    @SuppressWarnings("unchecked")
    static <P extends Nat<P>, Q extends Nat<Q>>
    SameType<P, Q> natSameType(SameType<S<P>, S<Q>> prf) {
        return (SameType) prf;
    }

    /**
     * Proof that {@code false → false} for one specific case
     * @param lie proof that zero is positive
     * @param <P> type of number that has to be less than zero
     * @return nothing, {@link Impossible} is thrown instead
     */
    static <P extends Nat<P>> Void zeroIsNotPositive(SameType<S<P>, Z> lie) {
        throw new Impossible();
    }
}
