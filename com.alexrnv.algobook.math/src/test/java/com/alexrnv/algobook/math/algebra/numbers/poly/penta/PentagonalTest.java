package com.alexrnv.algobook.math.algebra.numbers.poly.penta;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class PentagonalTest {

    @Test
    public void testSanityCheck() {
        assertEquals(1L, Pentagonal.pentagonal(1L));
        assertEquals(5L, Pentagonal.pentagonal(2L));
        assertEquals(12L, Pentagonal.pentagonal(3L));
        assertEquals(22L, Pentagonal.pentagonal(4L));
        assertEquals(35L, Pentagonal.pentagonal(5L));
        assertEquals(51L, Pentagonal.pentagonal(6L));

        assertEquals(2L, Pentagonal.pentagonal(-1L));
        assertEquals(7L, Pentagonal.pentagonal(-2L));
        assertEquals(15L, Pentagonal.pentagonal(-3L));
        assertEquals(26L, Pentagonal.pentagonal(-4L));
        assertEquals(40L, Pentagonal.pentagonal(-5L));
        assertEquals(57L, Pentagonal.pentagonal(-6L));

        assertEquals(1500000500000L, Pentagonal.pentagonal(-1000_000L));
        assertEquals(1499999500000L, Pentagonal.pentagonal(1000_000L));

        assertEquals(new BigInteger("127605887595351923803377163805340467200"), Pentagonal.pentagonal(BigInteger.valueOf(Long.MIN_VALUE)));
        assertEquals(new BigInteger("127605887595351923766483675657921363970"), Pentagonal.pentagonal(BigInteger.valueOf(Long.MAX_VALUE)));

    }

    @Test
    public void testStaticChecker() {
        assertEquals(0, Pentagonal.CHECKER.precalculateFrom);
        assertEquals(Pentagonal.TABLE_SIZE, Pentagonal.CHECKER.precalculateTo);
        assertEquals(Pentagonal.TABLE_SIZE, Pentagonal.CHECKER.pentagonalNumTable.size());
    }

    @Test
    public void testStaticCalculator() {
        assertEquals(0, Pentagonal.CALCULATOR.precalculateFrom);
        assertEquals(Pentagonal.TABLE_SIZE, Pentagonal.CALCULATOR.precalculateTo);
        assertEquals(Pentagonal.TABLE_SIZE, Pentagonal.CALCULATOR.precalculated.length);
    }

}