package com.ibessonov.typesafe.vector;

import com.ibessonov.typesafe.nat.Nat;
import com.ibessonov.typesafe.nat.S;
import com.ibessonov.typesafe.nat.Z;
import com.ibessonov.typesafe.sametype.SameType;

import static com.ibessonov.typesafe.nat.NatSameType.natSameType;
import static com.ibessonov.typesafe.nat.NatUtil.natError;
import static com.ibessonov.typesafe.vector.Vector.vecSameType;

/**
 * @author ibessonov
 */
@FunctionalInterface
public interface VectorConsMatcher<P extends Nat<P>, T, Result> extends VectorMatcher<S<P>, T, Result> {

    @Override
    default Result caseNil(SameType<S<P>, Z> prf) {
        return natError(prf);
    }

    @Override
    default <P2 extends Nat<P2>> Result caseCons(T head, Vector<P2, T> tail, SameType<S<P2>, S<P>> prf) {
        SameType<Vector<P2, T>, Vector<P, T>> prf2 = vecSameType(natSameType(prf));
        return caseCons(head, prf2.castFirst(tail));
    }

    Result caseCons(T head, Vector<P, T> tail);
}
