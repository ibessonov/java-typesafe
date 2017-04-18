package com.ibessonov.typesafe.dec;

import com.ibessonov.typesafe.base.Not;

/**
 * @author ibessonov
 */
public interface DecMatcher<T, Result> {

    Result caseYes(T yes);

    Result caseNo(Not<T> no);
}
