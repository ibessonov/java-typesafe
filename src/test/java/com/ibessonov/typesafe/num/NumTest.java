package com.ibessonov.typesafe.num;

import org.junit.Test;

import static com.ibessonov.typesafe.num.Num.succ;
import static com.ibessonov.typesafe.num.Num.zero;

/**
 * @author ibessonov
 */
@SuppressWarnings({"UnusedAssignment", "unused"})
public class NumTest {

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
}