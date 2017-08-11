package com.ibessonov.typesafe.either;

/**
 * @author ibessonov
 */
class LeftEither<Left, Right> extends Either<Left, Right> {

    private final Left value;

    LeftEither(Left value) {
        this.value = value;
    }

    @Override
    public <Result> Result match(EitherMatcher<Left, Right, Result> matcher) {
        return matcher.caseLeft(value);
    }

    @Override
    void inheritanceProtection() {}
}
