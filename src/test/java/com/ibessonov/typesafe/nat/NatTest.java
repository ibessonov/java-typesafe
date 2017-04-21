package com.ibessonov.typesafe.nat;

import com.ibessonov.typesafe.base.Impossible;
import com.ibessonov.typesafe.sametype.SameType;
import org.junit.Test;

import static com.ibessonov.typesafe.dec.DecUtil.isNo;
import static com.ibessonov.typesafe.dec.DecUtil.isYes;
import static com.ibessonov.typesafe.nat.Nat.succ;
import static com.ibessonov.typesafe.nat.Nat.zero;
import static com.ibessonov.typesafe.nat.NatUtil.equal;
import static com.ibessonov.typesafe.nat.NatUtil.natError;
import static com.ibessonov.typesafe.sametype.SameType.sameType;
import static org.junit.Assert.assertTrue;

/**
 * @author ibessonov
 */
@SuppressWarnings({"UnusedAssignment", "unused"})
public class NatTest {

    @Test
    public void test() {
        Z zero = zero();
        S<Z> one = succ(zero);

        S<S<Z>> two = succ(succ(zero));
        two = succ(one);

        S<S<S<Z>>> three = succ(succ(succ(zero)));
        three = succ(succ(one));
        three = succ(two);
    }

    @Test(expected = Impossible.class)
    public void testImpossible() {
        @SuppressWarnings("unchecked")
        SameType<S<Z>, Z> impossible = (SameType) sameType();
        natError(impossible);
    }

    @Test
    public void testEqual() {
        Z zero = zero();
        S<Z> one = succ(zero);
        S<S<Z>> two = succ(one);

        assertTrue(isYes(equal(zero, zero)));
        assertTrue(isYes(equal(one, one)));
        assertTrue(isYes(equal(two, two)));

        assertTrue(isNo(equal(zero, one)));
        assertTrue(isNo(equal(zero, two)));

        assertTrue(isNo(equal(one, zero)));
        assertTrue(isNo(equal(two, zero)));

        assertTrue(isNo(equal(one, two)));
        assertTrue(isNo(equal(two, one)));
    }
}
