package com.ibessonov.typesafe.nat;

import com.ibessonov.typesafe.sametype.SameType;

/**
 * @author ibessonov
 */
public interface NatMatcher<N extends Nat<N>, Result> {

    Result caseZ(SameType<N, Z> prf);

    <P extends Nat<P>> Result caseS(P prev, SameType<S<P>, N> prf);
}
