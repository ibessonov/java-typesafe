package com.ibessonov.typesafe.num.order;

import com.ibessonov.typesafe.num.Num;
import com.ibessonov.typesafe.num.S;
import com.ibessonov.typesafe.sametype.SameType;

/**
 * Indicates that {@code Left <= Right}
 * @author ibessonov
 */
public interface LessOrEqual<Left extends Num, Right extends Num> {

    static <N extends Num> Equal<N> equal() {
        return new Equal<>();
    }

    static <Left extends Num, Right extends Num> Less<Left, Right> less(LessOrEqual<Left, Right> prf) {
        return new Less<>(prf);
    }

    <Result> Result match(LeqMatcher<Left, Right, Result> matcher);

    interface LeqMatcher<Left extends Num, Right extends Num, Result> {
        Result caseEqual(Equal<Left> e, SameType<Left, Right> prf);
        <P extends Num> Result caseLess(Less<Left, P> l, SameType<S<P>, Right> prf);
    }
}
