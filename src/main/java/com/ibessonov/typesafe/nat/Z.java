package com.ibessonov.typesafe.nat;

import static com.ibessonov.typesafe.sametype.SameType.sameType;

/**
 * Type that represents number 0
 * @author ibessonov
 */
public final class Z extends Nat<Z> {

    Z() {}

    @Override
    public Z cast() {
        return this;
    }

    @Override
    public <Result> Result natMatch(NatMatcher<Z, Result> matcher) {
        return matcher.caseZ(sameType());
    }

    @Override
    void inheritanceProtection() {}
}
