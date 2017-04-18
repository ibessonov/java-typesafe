package com.ibessonov.typesafe.vector;

import com.ibessonov.typesafe.nat.Nat;
import com.ibessonov.typesafe.nat.S;
import com.ibessonov.typesafe.nat.Z;
import com.ibessonov.typesafe.sametype.SameType;

/**
 * @author ibessonov
 */
public interface VectorMatcher<N extends Nat<N>, T, Result> {

    Result caseNil(SameType<N, Z> prf);

    <P extends Nat<P>> Result caseCons(T head, Vector<P, T> tail, SameType<S<P>, N> prf);
}
