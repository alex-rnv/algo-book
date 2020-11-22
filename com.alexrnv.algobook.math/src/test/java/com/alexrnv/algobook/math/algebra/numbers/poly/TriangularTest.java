package com.alexrnv.algobook.math.algebra.numbers.poly;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Random;
import java.util.stream.IntStream;

public class TriangularTest {

    @Test(expected = AssertionError.class)
    public void testZero() {
        Triangular.isTriangularNumber(0);
    }

    @Test(expected = AssertionError.class)
    public void testNegative() {
        Triangular.isTriangularNumber(-1);
    }

    @Test
    public void testLongOverflow() {
        Assert.assertTrue(Triangular.triangularUnsafe(Triangular.LONG_ARGUMENT_OVERFLOW) < 0); //overflow
        Assert.assertTrue(Triangular.triangularUnsafe(Triangular.LONG_ARGUMENT_OVERFLOW - 1) > 0);
    }

    @Test
    public void testCausesOverflow() {
        Assert.assertTrue(Triangular.causesOverflow(Triangular.LONG_ARGUMENT_OVERFLOW));
        Assert.assertTrue(Triangular.causesOverflow(Long.MAX_VALUE));
        Assert.assertFalse(Triangular.causesOverflow(Triangular.LONG_ARGUMENT_OVERFLOW-1));
    }

    @Test(expected = AssertionError.class)
    public void testLongOverflowAssert() {
        Triangular.isTriangularNumber(Triangular.LONG_ARGUMENT_OVERFLOW);
    }

    @Test
    public void testTriangularTable() {
        for (int n = 1; n< Triangular.TABLE_SIZE; n++) {
            long x = Triangular.triangular(n);
            Assert.assertTrue(Triangular.isInTriangularTable(x));
        }
    }

    @Test
    public void testTriangularFormulaSimple() {
        IntStream.of(1,3,6,10,15,21,28,36,45,55)
                .forEach( x -> Assert.assertTrue(Triangular.isTriangularByFormula(x)));
    }

    @Test
    public void testTriangularFormulaRandomValues() {
        Random random = new Random();
        for (int i=0; i<100_000; i++)  {
            long x = random.nextLong() % Triangular.LONG_ARGUMENT_OVERFLOW;
            if (x < 0) x = -x;
            long t = Triangular.triangular(x);
            Assert.assertTrue(String.format("x=%d, t=%d", x, t), Triangular.isTriangularByFormula(t));
        }
    }

    @Test
    public void testTriangularFormulaBigInteger() {
        Random random = new Random();
        for (int i=0; i<10_000; i++)  {
            long x = random.nextLong();
            if (x < 0) x = -x;
            BigInteger t = Triangular.triangular(BigInteger.valueOf(x));
            Assert.assertTrue(String.format("x=%d, t=%d", x, t), Triangular.isTriangularNumber(t));
        }
    }

    @Test
    public void testTriangularFormulaBigBigInteger() {
        BigInteger x = BigInteger.valueOf(Long.MAX_VALUE).pow(16);
        BigInteger t = Triangular.triangular(x);
        Assert.assertTrue(String.format("x=%d, t=%d", x, t), Triangular.isTriangularNumber(t));
    }

//    @Test
//    public void test1() {
//        Assert.assertTrue(Triangular.isTriangularByFormula(2625972890803285078l));
//    }


}