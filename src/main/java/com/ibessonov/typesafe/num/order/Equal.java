package com.ibessonov.typesafe.num.order;

import com.ibessonov.typesafe.num.Num;

import static com.ibessonov.typesafe.sametype.SameType.sameType;

/**
 * @author ibessonov
 */
public class Equal<N extends Num> implements LessOrEqual<N, N> {
    Equal() {}

    @Override
    public <Result> Result match(LeqMatcher<N, N, Result> matcher) {
        return matcher.caseEqual(this, sameType());
    }
}
