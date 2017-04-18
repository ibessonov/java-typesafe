package com.ibessonov.typesafe.nat.fin;

import com.ibessonov.typesafe.nat.Nat;
import com.ibessonov.typesafe.nat.S;
import com.ibessonov.typesafe.nat.Z;
import com.ibessonov.typesafe.sametype.SameType;

import static com.ibessonov.typesafe.nat.Nat.succ;
import static com.ibessonov.typesafe.nat.Nat.zero;
import static com.ibessonov.typesafe.nat.NatUtil.natError;
import static com.ibessonov.typesafe.nat.fin.Fin.fs;
import static com.ibessonov.typesafe.nat.fin.Fin.fz;
import static com.ibessonov.typesafe.nat.fin.FinSameType.finSameType;

/**
 * @author ibessonov
 */
public interface FinUtil {

    // returns unchecked nat :(
    static <N extends Nat<N>> Nat finToNum(Fin<N> fin) {
        return fin.match(new FinMatcher<N, Nat>() {
            @Override
            public <K extends Nat<K>> Nat caseFz(SameType<S<K>, N> prf) {
                return zero();
            }

            @SuppressWarnings("unchecked")
            @Override
            public <K extends Nat<K>> Nat caseFs(Fin<K> fin, SameType<S<K>, N> prf) {
                return succ(finToNum(fin));
            }
        });
    }

    static <N extends Nat<N>> Fin<S<N>> finCast(Fin<N> fin) {
        return fin.match(new FinMatcher<N, Fin<S<N>>>() {
            @Override
            public <K extends Nat<K>> Fin<S<N>> caseFz(SameType<S<K>, N> prf) {
                return fz();
            }

            @Override
            public <K extends Nat<K>> Fin<S<N>> caseFs(Fin<K> fin, SameType<S<K>, N> prf) {
                return fs(finSameType(prf).castFirst(finCast(fin)));
            }
        });
    }

    static <Result> Result finError(Fin<Z> fin) {
        return fin.match(new FinMatcher<Z, Result>() {
            @Override
            public <K extends Nat<K>> Result caseFz(SameType<S<K>, Z> prf) {
                return natError(prf);
            }

            @Override
            public <K extends Nat<K>> Result caseFs(Fin<K> fin, SameType<S<K>, Z> prf) {
                return natError(prf);
            }
        });
    }
}
