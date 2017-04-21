package com.ibessonov.typesafe.nat.fin;

import com.ibessonov.typesafe.base.Impossible;
import com.ibessonov.typesafe.nat.S;
import com.ibessonov.typesafe.nat.Z;
import org.junit.Test;

import static com.ibessonov.typesafe.nat.fin.Fin.fs;
import static com.ibessonov.typesafe.nat.fin.Fin.fz;
import static com.ibessonov.typesafe.nat.fin.FinUtil.finError;

/**
 * @author ibessonov
 */
@SuppressWarnings("unused")
public class FinTest {

    @Test
    public void test() {
        Fin<S<S<S<Z>>>> zero = fz();
        Fin<S<S<S<Z>>>> one = fs(fz());
        Fin<S<S<S<Z>>>> two = fs(fs(fz()));
    }

    @Test(expected = Impossible.class)
    public void testImpossible() {
        @SuppressWarnings("unchecked")
        Fin<Z> impossible = (Fin) fz();
        finError(impossible);
    }
}
