package com.ibessonov.typesafe.nat;

import com.ibessonov.typesafe.base.Not;
import com.ibessonov.typesafe.dec.Dec;
import com.ibessonov.typesafe.dec.DecMatcher;
import com.ibessonov.typesafe.sametype.SameType;

import static com.ibessonov.typesafe.dec.Dec.no;
import static com.ibessonov.typesafe.dec.Dec.yes;
import static com.ibessonov.typesafe.nat.NatSameType.natSameType;
import static com.ibessonov.typesafe.nat.NatSameType.zeroIsNotPositive;

/**
 * @author ibessonov
 */
public interface NatUtil {

    static <P extends Nat<P>, Result> Result natError(SameType<S<P>, Z> prf) {
        return zeroIsNotPositive(prf).bottom();
    }

    static <N extends Nat<N>, M extends Nat<M>> Dec<SameType<N, M>> equal(N n, M m) {
        return n.natMatch(new NatMatcher<N, Dec<SameType<N, M>>>() {
            @Override
            public Dec<SameType<N, M>> caseZ(SameType<N, Z> prf) {
                return m.natMatch(new NatMatcher<M, Dec<SameType<N, M>>>() {
                    @Override
                    public Dec<SameType<N, M>> caseZ(SameType<M, Z> prf2) {
                        return yes(prf.transitive(prf2.swap()));
                    }

                    @Override
                    public <PN extends Nat<PN>> Dec<SameType<N, M>> caseS(PN prev, SameType<S<PN>, M> prf2) {
                        return no(prf3 -> zeroIsNotPositive(prf2.transitive(prf3.swap()).transitive(prf)));
                    }
                });
            }

            @Override
            public <PN extends Nat<PN>> Dec<SameType<N, M>> caseS(PN prevN, SameType<S<PN>, N> prf) {
                return m.natMatch(new NatMatcher<M, Dec<SameType<N, M>>>() {
                    @Override
                    public Dec<SameType<N, M>> caseZ(SameType<M, Z> prf2) {
                        return no(prf3 -> zeroIsNotPositive(prf.transitive(prf3).transitive(prf2)));
                    }

                    @Override
                    public <PM extends Nat<PM>> Dec<SameType<N, M>> caseS(PM prevM, SameType<S<PM>, M> prf2) {
                        return equal(prevN, prevM).match(new DecMatcher<SameType<PN, PM>, Dec<SameType<N, M>>>() {
                            @Override
                            public Dec<SameType<N, M>> caseYes(SameType<PN, PM> prf3) {
                                SameType<S<PN>, S<PM>> reverse = prf3.reverse(NatSameType::natSameType);
                                return yes(prf.swap().transitive(reverse).transitive(prf2));
                            }

                            @Override
                            public Dec<SameType<N, M>> caseNo(Not<SameType<PN, PM>> prf3) {
                                return no(prf4 -> prf3.apply(natSameType(prf.transitive(prf4).transitive(prf2.swap()))));
                            }
                        });
                    }
                });
            }
        });
    }
}
