package com.alexrnv.algobook.math.algebra.numbers.poly;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.stream.IntStream;

public class HexagonalTest {

    @Test
    public void testTableSimple() {
        IntStream.of(1, 6, 15, 28, 45, 66, 91, 120, 153, 190)
                .forEach(n -> Assert.assertTrue(Hexagonal.isInHexagonalTable(n)));
    }

    @Test
    public void testFormulaSimple() {
        IntStream.of(1, 6, 15, 28, 45, 66, 91, 120, 153, 190)
                .forEach(n -> Assert.assertTrue(Hexagonal.isHexagonalByFormula(BigInteger.valueOf(n))));
    }

    @Test
    public void test1() {
        Assert.assertTrue(Hexagonal.isHexagonalNumber(8128));
    }

}