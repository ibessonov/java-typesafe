package com.ibessonov.typesafe.num.order;

import com.ibessonov.typesafe.num.Num;
import com.ibessonov.typesafe.num.S;

import static com.ibessonov.typesafe.sametype.SameType.sameType;

/**
 * Indicates that {@code Left < S<Right>}
 * @author ibessonov
 */
public class Less<Left extends Num, Right extends Num> implements LessOrEqual<Left, S<Right>> {
    private final LessOrEqual<Left, Right> prf;

    Less(LessOrEqual<Left, Right> prf) {
        this.prf = prf;
    }

    public LessOrEqual<Left, Right> asLessOrEqual() {
        return prf;
    }

    @Override
    public <Result> Result match(LeqMatcher<Left, S<Right>, Result> matcher) {
        return matcher.caseLess(this, sameType());
    }
}
