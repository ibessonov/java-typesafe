package com.ibessonov.typesafe.num;

import com.ibessonov.typesafe.sametype.SameType;

import static com.ibessonov.typesafe.sametype.SameType.sameType;

/**
 * @author ibessonov
 */
public interface Num {

    static Z zero() {
        return new Z();
    }

    static <P extends Num> S<P> succ(P p) {
        return new S<>(p);
    }

    @SuppressWarnings("unchecked")
    static <N extends Num, Result> Result numMatch(N n, NumMatcher<N, Result> matcher) {
        if (n instanceof Z) {
            return (Result) matcher.caseZ((Z) n, (SameType) sameType());
        } else {
            return (Result) matcher.caseS((S) n, (SameType) sameType());
        }
    }

    interface NumMatcher<N extends Num, Result> {
        Result caseZ(Z n, SameType<N, Z> prf);
        <P extends Num> Result caseS(S<P> n, SameType<S<P>, N> prf);
    }

    @SuppressWarnings("unchecked")
    static <P extends Num, Q extends Num>
    SameType<P, Q> numSameType(SameType<S<P>, S<Q>> prf) {
        return (SameType) prf;
    }

    static <P extends Num, Result> Result numError(SameType<S<P>, Z> prf) {
        throw new AssertionError(prf);
    }
}
