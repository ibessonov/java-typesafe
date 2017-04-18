package com.ibessonov.typesafe.nat.fin;

import com.ibessonov.typesafe.nat.Nat;
import com.ibessonov.typesafe.nat.S;
import com.ibessonov.typesafe.sametype.SameType;

/**
 * @author ibessonov
 */
public interface FinMatcher<N extends Nat<N>, Result> {

    <K extends Nat<K>> Result caseFz(SameType<S<K>, N> prf);

    <K extends Nat<K>> Result caseFs(Fin<K> fin, SameType<S<K>, N> prf);
}
