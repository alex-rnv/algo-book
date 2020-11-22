package com.alexrnv.algobook.math.algebra.numbers.poly;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.stream.IntStream;

public class HeptagonalTest {

    @Test
    public void testTableSimple() {
        IntStream.of(1, 7, 18, 34, 55, 81, 112, 148, 189, 235)
                .forEach(n -> Assert.assertTrue(Heptagonal.isInHeptagonalTable(n)));
    }

    @Test
    public void testFormulaSimple() {
        IntStream.of(1, 7, 18, 34, 55, 81, 112, 148, 189, 235)
                .forEach(n -> Assert.assertTrue(Heptagonal.isHeptagonalByFormula(BigInteger.valueOf(n))));
    }

}