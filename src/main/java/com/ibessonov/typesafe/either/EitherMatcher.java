package com.ibessonov.typesafe.either;

/**
 * @author ibessonov
 */
public interface EitherMatcher<Left, Right, Result> {

    Result caseLeft(Left value);

    Result caseRight(Right value);
}
