package com.ibessonov.typesafe.dec;

import com.ibessonov.typesafe.base.Not;

/**
 * @author ibessonov
 */
class No<T> implements Dec<T> {

    private final Not<T> prf;

    No(Not<T> prf) {
        this.prf = prf;
    }

    @Override
    public <Result> Result match(DecMatcher<T, Result> matcher) {
        return matcher.caseNo(prf);
    }
}
