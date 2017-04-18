package com.ibessonov.typesafe.vector;

import com.ibessonov.typesafe.nat.Z;

import static com.ibessonov.typesafe.sametype.SameType.sameType;

/**
 * @author ibessonov
 */
class Nil<T> implements Vector<Z, T> {

    Nil() {}

    @Override
    public <Result> Result match(VectorMatcher<Z, T, Result> matcher) {
        return matcher.caseNil(sameType());
    }
}
