package com.ibessonov.typesafe.nat;

import static com.ibessonov.typesafe.sametype.SameType.sameType;

/**
 * @author ibessonov
 */
public class Z implements Nat<Z> {

    Z() {}

    @Override
    public <Result> Result natMatch(NatMatcher<Z, Result> matcher) {
        return matcher.caseZ(sameType());
    }
}
