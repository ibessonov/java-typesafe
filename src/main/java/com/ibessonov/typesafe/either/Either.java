package com.ibessonov.typesafe.either;

/**
 * @author ibessonov
 */
public abstract class Either<Left, Right> {

    public static <Left, Right> Either<Left, Right> left(Left value) {
        return new LeftEither<>(value);
    }

    public static <Left, Right> Either<Left, Right> right(Right value) {
        return new RightEither<>(value);
    }

    public abstract <Result> Result match(EitherMatcher<Left, Right, Result> matcher);

    /**
     * Package-protected method in abstract class guarantees that one cannot inherit this class outside the package
     */
    abstract void inheritanceProtection();
}
