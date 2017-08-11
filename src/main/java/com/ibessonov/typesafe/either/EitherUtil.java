package com.ibessonov.typesafe.either;

import com.ibessonov.typesafe.sametype.SameType;

import java.util.function.Consumer;
import java.util.function.Function;

import static com.ibessonov.typesafe.either.Either.left;
import static com.ibessonov.typesafe.either.Either.right;
import static com.ibessonov.typesafe.util.Functions.constant;
import static java.util.function.Function.identity;

/**
 * @author ibessonov
 */
public interface EitherUtil {

    static <Left, Right, Result> Result match(Either<Left, Right> either, Function<Left, Result> left, Function<Right, Result> right) {
        return either.match(new EitherMatcher<Left, Right, Result>() {
            @Override
            public Result caseLeft(Left value) {
                return left.apply(value);
            }

            @Override
            public Result caseRight(Right value) {
                return right.apply(value);
            }
        });
    }

    static <Left, Right> void consume(Either<Left, Right> either, Consumer<Left> left, Consumer<Right> right) {
        either.match(new EitherMatcher<Left, Right, Void>() {
            @Override
            public Void caseLeft(Left value) {
                left.accept(value);
                return null;
            }

            @Override
            public Void caseRight(Right value) {
                right.accept(value);
                return null;
            }
        });
    }

    static <Left, Right> Left sameEither(Either<Left, Right> either, SameType<Left, Right> prf) {
        return match(either, identity(), prf::castSecond);
    }

    static <Left, Right> Either<Right, Left> swap(Either<Left, Right> either) {
        return match(either, Either::right, Either::left);
    }

    static <Left, Right> Left leftOrDefault(Either<Left, Right> either, Left def) {
        return match(either, identity(), constant(def));
    }

    static <Left, Right> Right rightOrDefault(Either<Left, Right> either, Right def) {
        return match(either, constant(def), identity());
    }

    static <Left1, Left2, Right> Either<Left2, Right> mapLeft(Either<Left1, Right> either, Function<Left1, Left2> f) {
        return match(either, v -> left(f.apply(v)), Either::right);
    }

    static <Left, Right1, Right2> Either<Left, Right2> mapRight(Either<Left, Right1> either, Function<Right1, Right2> f) {
        return match(either, Either::left, v -> right(f.apply(v)));
    }
}
