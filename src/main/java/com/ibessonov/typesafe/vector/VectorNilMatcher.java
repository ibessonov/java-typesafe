package com.ibessonov.typesafe.vector;

import com.ibessonov.typesafe.nat.Nat;
import com.ibessonov.typesafe.nat.S;
import com.ibessonov.typesafe.nat.Z;
import com.ibessonov.typesafe.sametype.SameType;

import static com.ibessonov.typesafe.nat.NatUtil.natError;

/**
 * @author ibessonov
 */
@FunctionalInterface
public interface VectorNilMatcher<T, Result> extends VectorMatcher<Z, T, Result> {

    @Override
    default Result caseNil(SameType<Z, Z> prf) {
        return caseNil();
    }

    @Override
    default <P extends Nat<P>> Result caseCons(T head, Vector<P, T> tail, SameType<S<P>, Z> prf) {
        return natError(prf);
    }

    Result caseNil();
}
