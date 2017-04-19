package com.ibessonov.typesafe.nat;

import static com.ibessonov.typesafe.sametype.SameType.sameType;

/**
 * @author ibessonov
 */
public final class S<P extends Nat<P>> extends Nat<S<P>> {

    private final P prev;

    S(P p) {
        prev = p;
    }

    @Override
    public <Result> Result natMatch(NatMatcher<S<P>, Result> matcher) {
        return matcher.caseS(prev, sameType());
    }

    @Override
    void inheritanceProtection() {}
}
