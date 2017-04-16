package com.ibessonov.typesafe.sametype;

import java.util.function.Function;

/**
 * @author ibessonov
 */
public interface SameType<Left, Right> {

    Left toLeft(Right r);

    Right toRight(Left l);

    SameType<Right, Left> swap();

    <Extra> SameType<Left, Extra> transitive(SameType<Right, Extra> prf);

    <Left2, Right2> SameType<Left2, Right2> reverse(Function<SameType<Left2, Right2>, SameType<Left, Right>> f);

    static <T> SameType<T, T> sameType() {
        return new SameTypeImpl<>();
    }
}