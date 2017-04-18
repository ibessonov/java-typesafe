package com.ibessonov.typesafe.nat.fin;

import com.ibessonov.typesafe.nat.Nat;
import com.ibessonov.typesafe.sametype.SameType;

/**
 * @author ibessonov
 */
public interface FinSameType {

    @SuppressWarnings("unchecked")
    static <P extends Nat<P>, Q extends Nat<Q>>
    SameType<Fin<P>, Fin<Q>> finSameType(SameType<P, Q> prf) {
        return (SameType) prf;
    }
}
