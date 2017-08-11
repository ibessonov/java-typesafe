package com.ibessonov.typesafe.either;

/**
 * @author ibessonov
 */
class RightEither<Left, Right> extends Either<Left, Right> {

    private final Right value;

    RightEither(Right value) {
        this.value = value;
    }

    @Override
    public <Result> Result match(EitherMatcher<Left, Right, Result> matcher) {
        return matcher.caseRight(value);
    }

    @Override
    void inheritanceProtection() {}
}
