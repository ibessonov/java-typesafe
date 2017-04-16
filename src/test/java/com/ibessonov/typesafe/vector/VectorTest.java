package com.ibessonov.typesafe.vector;

import com.ibessonov.typesafe.num.S;
import com.ibessonov.typesafe.num.Z;
import com.ibessonov.typesafe.num.fin.Fin;
import com.ibessonov.typesafe.vector.Vector.Nil;
import org.junit.Test;

import static com.ibessonov.typesafe.num.fin.Fin.fs;
import static com.ibessonov.typesafe.num.fin.Fin.fz;
import static com.ibessonov.typesafe.sametype.SameType.sameType;
import static com.ibessonov.typesafe.vector.Vector.cons;
import static com.ibessonov.typesafe.vector.Vector.nil;
import static com.ibessonov.typesafe.vector.VectorUtil.*;
import static org.junit.Assert.assertEquals;

/**
 * @author ibessonov
 */
@SuppressWarnings({"UnusedAssignment", "unused"})
public class VectorTest {

    @Test
    public void test0() {
        Nil<String> vector = nil();
        Z size = size(vector);
    }

    @Test
    public void test1() {
        Vector<S<Z>, String> vector = cons("1", nil());
        S<Z> size = size(vector);

        assertEquals("1", vector.head(sameType()));
        assertEquals("1", last(vector, sameType()));

        Vector<Z, String> tail = vector.tail(sameType());
        Vector<Z, String> init = init(vector, sameType());

        Fin<S<Z>> zero = fz();
        assertEquals("1", index(vector, zero));
    }

    @Test
    public void test2() {
        Vector<S<S<Z>>, String> vector = cons("1", cons("2", nil()));
        S<S<Z>> size = size(vector);

        assertEquals("1", vector.head(sameType()));
        assertEquals("2", last(vector, sameType()));

        Vector<S<Z>, String> tail = vector.tail(sameType());
        Vector<S<Z>, String> init = init(vector, sameType());

        assertEquals("2", tail.head(sameType()));
        assertEquals("1", init.head(sameType()));

        Fin<S<S<Z>>> zero = fz();
        Fin<S<S<Z>>> one = fs(fz());

        assertEquals("1", index(vector, zero));
        assertEquals("2", index(vector, one));

        Vector<S<S<Z>>, String> reverse = reverse(vector);
        assertEquals("2", index(reverse, zero));
        assertEquals("1", index(reverse, one));
    }
}