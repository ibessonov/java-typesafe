package com.ibessonov.typesafe.vector;

import com.ibessonov.typesafe.base.Pair;
import com.ibessonov.typesafe.nat.S;
import com.ibessonov.typesafe.nat.Z;
import com.ibessonov.typesafe.nat.fin.Fin;
import org.junit.Test;

import static com.ibessonov.typesafe.nat.fin.Fin.fs;
import static com.ibessonov.typesafe.nat.fin.Fin.fz;
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
        Vector<Z, String> vector = nil();
        Z size = size(vector);
    }

    @Test
    public void test1() {
        Vector<S<Z>, String> vector = cons("1", nil());
        S<Z> size = size(vector);

        assertEquals("1", head(vector, sameType()));
        assertEquals("1", last(vector, sameType()));

        Vector<Z, String> tail = tail(vector, sameType());
        Vector<Z, String> init = init(vector, sameType());

        Fin<S<Z>> zero = fz();
        assertEquals("1", index(vector, zero));
    }

    @Test
    public void test2() {
        Vector<S<S<Z>>, String> vector = cons("1", cons("2", nil()));
        S<S<Z>> size = size(vector);

        assertEquals("1", head(vector, sameType()));
        assertEquals("2", last(vector, sameType()));

        Vector<S<Z>, String> tail = tail(vector, sameType());
        Vector<S<Z>, String> init = init(vector, sameType());

        assertEquals("2", head(tail, sameType()));
        assertEquals("1", head(init, sameType()));

        Fin<S<S<Z>>> zero = fz();
        Fin<S<S<Z>>> one = fs(fz());

        assertEquals("1", index(vector, zero));
        assertEquals("2", index(vector, one));

        Vector<S<S<Z>>, String> reverse = reverse(vector);
        assertEquals("2", index(reverse, zero));
        assertEquals("1", index(reverse, one));
    }

    @Test
    public void testMap() {
        Vector<S<S<Z>>, String> vector = cons("1", cons("2", nil()));

        Vector<S<S<Z>>, Integer> result = map(vector, Integer::valueOf);
        assertEquals(Integer.valueOf(1), index(result, fz()));
        assertEquals(Integer.valueOf(2), index(result, fs(fz())));
    }

    @Test
    public void testZip() {
        Vector<S<S<Z>>, Integer> v1 = cons(1, cons(2, nil()));
        Vector<S<S<Z>>, String>  v2 = cons("1", cons("2", nil()));

        Vector<S<S<Z>>, Pair<Integer, String>> zip = zip(v1, v2, Pair::pair);

        Pair<Integer, String> fst = index(zip, fz());
        Pair<Integer, String> snd = index(zip, fs(fz()));

        assertEquals(Integer.valueOf(1), fst.left);
        assertEquals("1", fst.right);

        assertEquals(Integer.valueOf(2), snd.left);
        assertEquals("2", snd.right);
    }
}
