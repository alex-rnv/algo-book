package com.alexrnv.algobook.math.algebra.fundamentals;

import com.alexrnv.algobook.util.Triple;
import org.junit.Assert;
import org.junit.Test;

public class PythagoreanTripleTest {

    @Test
    public void testSimple() {
        Assert.assertEquals(new Triple<>(3L,4L,5L), PythagoreanTriple.generateFor(1L, 2L));
        Assert.assertEquals(new Triple<>(3L,4L,5L), PythagoreanTriple.generateFor(2L, 1L));
        Assert.assertEquals(new Triple<>(5L,12L,13L), PythagoreanTriple.generateFor(2L, 3L));
        Assert.assertEquals(new Triple<>(5L,12L,13L), PythagoreanTriple.generateFor(3L, 2L));
    }

}