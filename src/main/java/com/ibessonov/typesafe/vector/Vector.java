package com.ibessonov.typesafe.vector;

import com.ibessonov.typesafe.num.Num;
import com.ibessonov.typesafe.num.S;
import com.ibessonov.typesafe.num.Z;
import com.ibessonov.typesafe.sametype.SameType;

import static com.ibessonov.typesafe.num.Num.numError;
import static com.ibessonov.typesafe.num.Num.numSameType;
import static com.ibessonov.typesafe.sametype.SameType.sameType;

/**
 * @author ibessonov
 */
public interface Vector<N extends Num, T> {

    static <T> Vector<Z, T> nil() {
        return new Nil<>();
    }

    static <T, N extends Num> Vector<S<N>, T> cons(T head, Vector<N, T> tail) {
        return new Cons<>(head, tail);
    }

    @SuppressWarnings("unchecked")
    static <N extends Num, M extends Num, T>
    SameType<Vector<N, T>, Vector<M, T>> vecSameType(SameType<N, M> eq) {
        return (SameType) eq;
    }

    <P extends Num> T head(SameType<S<P>, N> prf);
    <P extends Num> Vector<P, T> tail(SameType<S<P>, N> prf);

    <Result> Result match(VectorMatcher<N, T, Result> matcher);

    interface VectorMatcher<N extends Num, T, Result> {
        Result caseNil(Nil<T> v, SameType<N, Z> prf);
        <P extends Num> Result caseCons(Cons<P, T> v, SameType<S<P>, N> prf);
    }

    class Nil<T> implements Vector<Z, T> {
        private Nil() {}

        @Override
        public <P extends Num> T head(SameType<S<P>, Z> prf) {
            return numError(prf);
        }

        @Override
        public <P extends Num> Vector<P, T> tail(SameType<S<P>, Z> prf) {
            return numError(prf);
        }

        @Override
        public <R> R match(VectorMatcher<Z, T, R> matcher) {
            return matcher.caseNil(this, sameType());
        }
    }

    class Cons<N extends Num, T> implements Vector<S<N>, T> {
        private final T head;
        private final Vector<N, T> tail;
        private Cons(T head, Vector<N, T> tail) {
            this.head = head;
            this.tail = tail;
        }

        @Override
        public <P extends Num> T head(SameType<S<P>, S<N>> prf) {
            return head;
        }

        @Override
        public <P extends Num> Vector<P, T> tail(SameType<S<P>, S<N>> prf) {
            SameType<P, N> prf2 = numSameType(prf);
            SameType<Vector<P, T>, Vector<N, T>> prf3 = vecSameType(prf2);
            return prf3.toLeft(tail);
        }

        public T head() {
            return head(sameType());
        }

        public Vector<N, T> tail() {
            return tail(sameType());
        }

        @Override
        public <R> R match(VectorMatcher<S<N>, T, R> matcher) {
            return matcher.caseCons(this, sameType());
        }
    }
}
