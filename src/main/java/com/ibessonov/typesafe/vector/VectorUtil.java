package com.ibessonov.typesafe.vector;

import com.ibessonov.typesafe.nat.Nat;
import com.ibessonov.typesafe.nat.S;
import com.ibessonov.typesafe.nat.Z;
import com.ibessonov.typesafe.nat.fin.Fin;
import com.ibessonov.typesafe.nat.fin.FinMatcher;
import com.ibessonov.typesafe.sametype.SameType;

import java.util.function.BiFunction;
import java.util.function.Function;

import static com.ibessonov.typesafe.nat.Nat.succ;
import static com.ibessonov.typesafe.nat.Nat.zero;
import static com.ibessonov.typesafe.nat.NatSameType.natSameType;
import static com.ibessonov.typesafe.nat.fin.FinSameType.finSameType;
import static com.ibessonov.typesafe.nat.fin.FinUtil.finError;
import static com.ibessonov.typesafe.sametype.SameType.sameType;
import static com.ibessonov.typesafe.vector.Vector.*;

/**
 * @author ibessonov
 */
public interface VectorUtil {

    static <N extends Nat<N>, T, Result>
    Result match(Vector<N, T> vector, SameType<N, Z> prf, VectorNilMatcher<T, Result> matcher) {
        SameType<Vector<N, T>, Vector<Z, T>> prf2 = vecSameType(prf);
        return prf2.castFirst(vector).match(matcher);
    }

    static <N extends Nat<N>, P extends Nat<P>, T, Result>
    Result match(Vector<N, T> vector, SameType<S<P>, N> prf, VectorConsMatcher<P, T, Result> matcher) {
        SameType<Vector<S<P>, T>, Vector<N, T>> prf2 = vecSameType(prf);
        return prf2.castSecond(vector).match(matcher);
    }

    static <N extends Nat<N>, T, P extends Nat<P>> T head(Vector<N, T> vector, SameType<S<P>, N> prf) {
        return match(vector, prf, (head, tail) -> head);
    }

    static <N extends Nat<N>, T, P extends Nat<P>> Vector<P, T> tail(Vector<N, T> vector, SameType<S<P>, N> prf) {
        return match(vector, prf, (head, tail) -> tail);
    }

    static <N extends Nat<N>, T> N size(Vector<N, T> vector) {
        return vector.match(new VectorMatcher<N, T, N>() {
            @Override
            public N caseNil(SameType<N, Z> prf) {
                return prf.castSecond(zero());
            }

            @Override
            public <P extends Nat<P>> N caseCons(T head, Vector<P, T> tail, SameType<S<P>, N> prf) {
                return prf.castFirst(succ(size(tail)));
            }
        });
    }

    static <N extends Nat<N>, T> T index(Vector<N, T> vector, Fin<N> index) {
        return vector.match(new VectorMatcher<N, T, T>() {
            @Override
            public T caseNil(SameType<N, Z> prf) {
                return finError(finSameType(prf).castFirst(index));
            }

            @Override
            public <P extends Nat<P>> T caseCons(T head, Vector<P, T> tail, SameType<S<P>, N> prf) {
                return index.match(new FinMatcher<N, T>() {
                    @Override
                    public <K extends Nat<K>> T caseFz(SameType<S<K>, N> prf2) {
                        return head;
                    }

                    @Override
                    public <K extends Nat<K>> T caseFs(Fin<K> fin, SameType<S<K>, N> prf2) {
                        SameType<P, K> prf3 = natSameType(prf.transitive(prf2.swap()));
                        SameType<Vector<P, T>, Vector<K, T>> prf4 = vecSameType(prf3);
                        return index(prf4.castFirst(tail), fin);
                    }
                });
            }
        });
    }

    static <N extends Nat<N>, T, P extends Nat<P>> T last(Vector<N, T> vector, SameType<S<P>, N> prf) {
        return match(vector, prf, (head, tail) -> {
            class LastElemVM<N2 extends Nat<N2>> implements VectorMatcher<N2, T, T> {
                private final T prev;

                private LastElemVM(T prev) {
                    this.prev = prev;
                }

                @Override
                public T caseNil(SameType<N2, Z> prf2) {
                    return prev;
                }

                @Override
                public <P2 extends Nat<P2>> T caseCons(T head, Vector<P2, T> tail, SameType<S<P2>, N2> prf2) {
                    return tail.match(new LastElemVM<>(head));
                }
            }
            return tail.match(new LastElemVM<>(head));
        });
    }

    static <N extends Nat<N>, T, P extends Nat<P>> Vector<P, T> init(Vector<N, T> vector, SameType<S<P>, N> prf) {
        return match(vector, prf, (head, tail) -> {
            class LastElemVM<N2 extends Nat<N2>> implements VectorMatcher<N2, T, Vector<N2, T>> {
                private final T prev;

                private LastElemVM(T prev) {
                    this.prev = prev;
                }

                @Override
                public Vector<N2, T> caseNil(SameType<N2, Z> prf2) {
                    SameType<Vector<N2, T>, Vector<Z, T>> prf3 = vecSameType(prf2);
                    return prf3.castSecond(nil());
                }

                @Override
                public <P2 extends Nat<P2>> Vector<N2, T> caseCons(T head, Vector<P2, T> tail, SameType<S<P2>, N2> prf2) {
                    SameType<Vector<S<P2>, T>, Vector<N2, T>> prf3 = vecSameType(prf2);
                    return prf3.castFirst(cons(prev, tail.match(new LastElemVM<>(head))));
                }
            }
            return tail.match(new LastElemVM<>(head));
        });
    }

    static <N extends Nat<N>, T> Vector<N, T> reverse(Vector<N, T> vector) {
        return vector.match(new VectorMatcher<N, T, Vector<N, T>>() {
            @Override
            public Vector<N, T> caseNil(SameType<N, Z> prf) {
                SameType<Vector<N, T>, Vector<Z, T>> prf2 = vecSameType(prf);
                return prf2.castSecond(nil());
            }

            @Override
            public <P extends Nat<P>> Vector<N, T> caseCons(T head, Vector<P, T> tail, SameType<S<P>, N> prf) {
                SameType<Vector<S<P>, T>, Vector<N, T>> prf2 = vecSameType(prf);

                Vector<S<P>, T> v = cons(head, tail);
                return prf2.castFirst(cons(last(v, sameType()), reverse(init(v, sameType()))));
            }
        });
    }

    static <N extends Nat<N>, T, U> Vector<N, U> map(Vector<N, T> vector, Function<T, U> mapping) {
        return vector.match(new VectorMatcher<N, T, Vector<N, U>>() {
            @Override
            public Vector<N, U> caseNil(SameType<N, Z> prf) {
                SameType<Vector<N, U>, Vector<Z, U>> prf2 = vecSameType(prf);
                return prf2.castSecond(nil());
            }

            @Override
            public <P extends Nat<P>> Vector<N, U> caseCons(T head, Vector<P, T> tail, SameType<S<P>, N> prf) {
                SameType<Vector<S<P>, U>, Vector<N, U>> prf2 = vecSameType(prf);
                return prf2.castFirst(cons(mapping.apply(head), map(tail, mapping)));
            }
        });
    }

    static <N extends Nat<N>, T, U, Result>
    Vector<N, Result> zip(Vector<N, T> vector1, Vector<N, U> vector2, BiFunction<T, U, Result> function) {
        return vector1.match(new VectorMatcher<N, T, Vector<N, Result>>() {
            @Override
            public Vector<N, Result> caseNil(SameType<N, Z> prf) {
                SameType<Vector<N, Result>, Vector<Z, Result>> prf2 = vecSameType(prf);
                return prf2.castSecond(nil());
            }

            @Override
            public <P extends Nat<P>> Vector<N, Result> caseCons(T head1, Vector<P, T> tail1, SameType<S<P>, N> prf) {
                return VectorUtil.match(vector2, prf, (head2, tail2) -> {
                    SameType<Vector<S<P>, Result>, Vector<N, Result>> prf2 = vecSameType(prf);
                    return prf2.castFirst(cons(function.apply(head1, head2), zip(tail1, tail2, function)));
                });
            }
        });
    }
}
