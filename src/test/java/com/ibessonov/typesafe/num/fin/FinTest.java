package com.ibessonov.typesafe.num.fin;

import com.ibessonov.typesafe.num.S;
import com.ibessonov.typesafe.num.Z;
import org.junit.Test;

import static com.ibessonov.typesafe.num.fin.Fin.fs;
import static com.ibessonov.typesafe.num.fin.Fin.fz;

/**
 * @author ibessonov
 */
@SuppressWarnings("unused")
public class FinTest {

    @Test
    public void test() {
        Fin<S<S<S<Z>>>> zero = fz();
        Fin<S<S<S<Z>>>> one = fs(fz());
        Fin<S<S<S<Z>>>> fs = fs(fs(fz()));
    }
}