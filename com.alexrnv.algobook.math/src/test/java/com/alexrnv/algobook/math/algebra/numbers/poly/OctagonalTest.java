package com.alexrnv.algobook.math.algebra.numbers.poly;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.stream.IntStream;

public class OctagonalTest {

    @Test
    public void testTableSimple() {
        IntStream.of(1, 8, 21, 40, 65, 96, 133, 176, 225, 280)
                .forEach(n -> Assert.assertTrue(Octagonal.isInOctagonalTable(n)));
    }

    @Test
    public void testFormulaSimple() {
        IntStream.of(1, 8, 21, 40, 65, 96, 133, 176, 225, 280)
                .forEach(n -> Assert.assertTrue(Octagonal.isOctagonalByFormula(BigInteger.valueOf(n))));
    }

}