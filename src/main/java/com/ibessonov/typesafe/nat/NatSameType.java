package com.ibessonov.typesafe.nat;

import com.ibessonov.typesafe.base.Impossible;
import com.ibessonov.typesafe.sametype.SameType;

/**
 * @author ibessonov
 */
public interface NatSameType {

    @SuppressWarnings("unchecked")
    static <P extends Nat<P>, Q extends Nat<Q>>
    SameType<P, Q> natSameType(SameType<S<P>, S<Q>> prf) {
        return (SameType) prf;
    }

    static <P extends Nat<P>> Void zeroIsNotPositive(SameType<S<P>, Z> lie) {
        throw new Impossible();
    }
}
