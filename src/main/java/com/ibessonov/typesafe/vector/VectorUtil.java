package com.ibessonov.typesafe.vector;

import com.ibessonov.typesafe.num.Num;
import com.ibessonov.typesafe.num.S;
import com.ibessonov.typesafe.num.Z;
import com.ibessonov.typesafe.num.fin.FS;
import com.ibessonov.typesafe.num.fin.FZ;
import com.ibessonov.typesafe.num.fin.Fin;
import com.ibessonov.typesafe.num.fin.Fin.FinMatcher;
import com.ibessonov.typesafe.sametype.SameType;
import com.ibessonov.typesafe.vector.Vector.*;

import java.util.function.Function;

import static com.ibessonov.typesafe.num.Num.*;
import static com.ibessonov.typesafe.num.fin.Fin.finError;
import static com.ibessonov.typesafe.num.fin.Fin.finSameType;
import static com.ibessonov.typesafe.sametype.SameType.sameType;
import static com.ibessonov.typesafe.vector.Vector.*;

/**
 * @author ibessonov
 */
public class VectorUtil {

    public static <N extends Num, T, P extends Num> T head(Vector<N, T> vector, SameType<S<P>, N> prf) {
        return vector.match(new VectorMatcher<N, T, T>() {
            @Override
            public T caseNil(Nil<T> v, SameType<N, Z> prf2) {
                return numError(prf.transitive(prf2));
            }

            @Override
            public <P2 extends Num> T caseCons(Cons<P2, T> v, SameType<S<P2>, N> prf) {
                return v.head();
            }
        });
    }

    public static <N extends Num, T, P extends Num> Vector<P, T> tail(Vector<N, T> vector, SameType<S<P>, N> prf) {
        return vector.match(new VectorMatcher<N, T, Vector<P, T>>() {
            @Override
            public Vector<P, T> caseNil(Nil<T> v, SameType<N, Z> prf2) {
                return numError(prf.transitive(prf2));
            }

            @Override
            public <P2 extends Num> Vector<P, T> caseCons(Cons<P2, T> v, SameType<S<P2>, N> prf2) {
                SameType<P, P2> prf3 = numSameType(prf.transitive(prf2.swap()));
                SameType<Vector<P, T>, Vector<P2, T>> prf4 = vecSameType(prf3);
                return prf4.toLeft(v.tail());
            }
        });
    }

    public static <N extends Num, T> N size(Vector<N, T> vector) {
        return vector.match(new VectorMatcher<N, T, N>() {
            @Override
            public N caseNil(Nil<T> v, SameType<N, Z> prf) {
                return prf.toLeft(zero());
            }

            @Override
            public <P extends Num> N caseCons(Cons<P, T> v, SameType<S<P>, N> prf) {
                return prf.toRight(succ(size(v.tail())));
            }
        });
    }

    public static <N extends Num, T> T index(Vector<N, T> vector, Fin<N> index) {
        return vector.match(new VectorMatcher<N, T, T>() {
            @Override
            public T caseNil(Nil<T> v, SameType<N, Z> prf) {
                return finError(finSameType(prf).toRight(index));
            }

            @Override
            public <P extends Num> T caseCons(Cons<P, T> v, SameType<S<P>, N> prf) {
                return index.match(new FinMatcher<N, T>() {
                    @Override
                    public <K extends Num> T caseFz(FZ<K> fz, SameType<S<K>, N> prf2) {
                        return v.head();
                    }

                    @Override
                    public <K extends Num> T caseFs(FS<K> fz, SameType<S<K>, N> prf2) {
                        SameType<P, K> prf3 = numSameType(prf.transitive(prf2.swap()));
                        SameType<Vector<P, T>, Vector<K, T>> prf4 = vecSameType(prf3);
                        return index(prf4.toRight(v.tail()), fz.fin());
                    }
                });
            }
        });
    }

    public static <N extends Num, T, P extends Num> T last(Vector<N, T> vector, SameType<S<P>, N> prf) {
        return vector.match(new VectorMatcher<N, T, T>() {
            @Override
            public T caseNil(Nil<T> v, SameType<N, Z> prf2) {
                return numError(prf.transitive(prf2));
            }

            @Override
            public <P2 extends Num> T caseCons(Cons<P2, T> v, SameType<S<P2>, N> prf2) {
                class LastElemVM<N2 extends Num> implements VectorMatcher<N2, T, T> {
                    private final T prev;

                    private LastElemVM(T prev) {
                        this.prev = prev;
                    }

                    @Override
                    public T caseNil(Nil<T> v, SameType<N2, Z> prf2) {
                        return prev;
                    }

                    @Override
                    public <P3 extends Num> T caseCons(Cons<P3, T> v, SameType<S<P3>, N2> prf2) {
                        return v.tail().match(new LastElemVM<>(v.head()));
                    }
                }
                return v.match(new LastElemVM<>(v.head()));
            }
        });
    }

    public static <N extends Num, T, P extends Num> Vector<P, T> init(Vector<N, T> v, SameType<S<P>, N> prf) {
        return v.match(new VectorMatcher<N, T, Vector<P, T>>() {

            @Override
            public Vector<P, T> caseNil(Nil<T> v, SameType<N, Z> prf2) {
                return numError(prf.transitive(prf2));
            }

            @Override
            public <P2 extends Num> Vector<P, T> caseCons(Cons<P2, T> v, SameType<S<P2>, N> prf2) {
                class LastElemVM<N2 extends Num> implements VectorMatcher<N2, T, Vector<N2, T>> {
                    private final T prev;

                    private LastElemVM(T prev) {
                        this.prev = prev;
                    }

                    @Override
                    public Vector<N2, T> caseNil(Nil<T> v, SameType<N2, Z> prf2) {
                        SameType<Vector<N2, T>, Vector<Z, T>> prf3 = vecSameType(prf2);
                        return prf3.toLeft(v);
                    }

                    @Override
                    public <P3 extends Num> Vector<N2, T> caseCons(Cons<P3, T> v, SameType<S<P3>, N2> prf3) {
                        SameType<Vector<S<P3>, T>, Vector<N2, T>> prf4 = vecSameType(prf3);
                        return prf4.toRight(cons(prev, v.tail().match(new LastElemVM<>(v.head()))));
                    }
                }
                SameType<P, P2> prf3 = numSameType(prf.transitive(prf2.swap()));
                SameType<Vector<P, T>, Vector<P2, T>> prf4 = vecSameType(prf3);
                return prf4.toLeft(v.tail().match(new LastElemVM<>(v.head())));
            }
        });
    }

    public static <N extends Num, T> Vector<N, T> reverse(Vector<N, T> vector) {
        return vector.match(new VectorMatcher<N, T, Vector<N, T>>() {
            @Override
            public Vector<N, T> caseNil(Nil<T> v, SameType<N, Z> prf) {
                SameType<Vector<N, T>, Vector<Z, T>> prf2 = vecSameType(prf);
                return prf2.toLeft(v);
            }

            @Override
            public <P extends Num> Vector<N, T> caseCons(Cons<P, T> v, SameType<S<P>, N> prf) {
                SameType<Vector<S<P>, T>, Vector<N, T>> prf2 = vecSameType(prf);
                return prf2.toRight(cons(last(v, sameType()), reverse(init(v, sameType()))));
            }
        });
    }

    public static <N extends Num, T, U> Vector<N, U> map(Vector<N, T> vector, Function<T, U> mapping) {
        return vector.match(new VectorMatcher<N, T, Vector<N, U>>() {
            @Override
            public Vector<N, U> caseNil(Nil<T> v, SameType<N, Z> prf) {
                SameType<Vector<N, U>, Vector<Z, U>> prf2 = vecSameType(prf);
                return prf2.toLeft(nil());
            }

            @Override
            public <P extends Num> Vector<N, U> caseCons(Cons<P, T> v, SameType<S<P>, N> prf) {
                SameType<Vector<S<P>, U>, Vector<N, U>> prf2 = vecSameType(prf);
                return prf2.toRight(cons(mapping.apply(v.head()), map(v.tail(), mapping)));
            }
        });
    }
}