package com.ibessonov.typesafe.dec;

/**
 * @author ibessonov
 */
class Yes<T> implements Dec<T> {

    private final T prf;

    Yes(T prf) {
        this.prf = prf;
    }

    @Override
    public <Result> Result match(DecMatcher<T, Result> matcher) {
        return matcher.caseYes(prf);
    }
}
