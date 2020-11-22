package com.alexrnv.algobook.math.algebra.numbers.fractions;

import com.alexrnv.algobook.math.algebra.fundamentals.primes.Primes;
import org.junit.Test;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class SimpleFractionTest {

    @Test
    public void TestSimpleUnitFractions() {
        IntStream.range(1, 100).forEach(n -> {
            SimpleFraction fraction = SimpleFraction.create(1L, n);
            assertEquals(BigInteger.ONE, fraction.getNumerator());
            assertEquals(BigInteger.valueOf(n), fraction.getDenominator());
        });
    }

    @Test
    public void TestPrimeFractionsNotSimplified() {
        List<Long> primes = Primes.primes(100);
        for (int i=0; i<primes.size()-1; i++) {
            long n = primes.get(i);
            for (int j=i+1; j< primes.size(); j++) {
                long d = primes.get(j);
                SimpleFraction fraction = SimpleFraction.create(n, d);
                assertEquals(BigInteger.valueOf(n), fraction.getNumerator());
                assertEquals(BigInteger.valueOf(d), fraction.getDenominator());
            }
        }
    }

    @Test
    public void TestFactorsSimplified() {
        for (int n=2; n<18; n++) {
            for (int factor=1; factor<12; factor++) {
                long d = n*factor;
                SimpleFraction fraction = SimpleFraction.create(n, d);
                assertEquals(BigInteger.valueOf(1), fraction.getNumerator());
                assertEquals(BigInteger.valueOf(factor), fraction.getDenominator());
            }
        }
    }

    @Test
    public void TestNumbersWithGCDAreSimplified() {
        long m = 19;
        long k = 37;
        for (long n=2; n<18; n++) {
            for (int factor=1; factor<12; factor++) {
                long d = n*factor;
                SimpleFraction fraction = SimpleFraction.create(n*m, d*k);
                assertEquals(BigInteger.valueOf(m), fraction.getNumerator());
                assertEquals(BigInteger.valueOf(factor*k), fraction.getDenominator());
            }
        }
    }

    @Test
    public void testZero() {
        SimpleFraction a = SimpleFraction.create(0, 1);
        assertEquals(BigInteger.ZERO, a.getNumerator());
        assertEquals(BigInteger.ONE, a.getDenominator());

        a = SimpleFraction.create(0, 0);
        assertEquals(BigInteger.ZERO, a.getNumerator());
        assertEquals(BigInteger.ZERO, a.getDenominator());
    }

    @Test
    public void testAdd() {
        SimpleFraction a = SimpleFraction.create(1, 2);
        SimpleFraction b = SimpleFraction.create(1, 2);
        SimpleFraction c = a.add(b);
        assertEquals(BigInteger.ONE, c.getNumerator());
        assertEquals(BigInteger.ONE, c.getDenominator());

        a = SimpleFraction.create(1, 2);
        b = SimpleFraction.create(1, 3);
        c = a.add(b);
        assertEquals(BigInteger.valueOf(5), c.getNumerator());
        assertEquals(BigInteger.valueOf(6), c.getDenominator());

        a = SimpleFraction.create(1, 2);
        b = SimpleFraction.create(2, 3);
        c = a.add(b);
        assertEquals(BigInteger.valueOf(7), c.getNumerator());
        assertEquals(BigInteger.valueOf(6), c.getDenominator());

        a = SimpleFraction.create(1, 4);
        b = SimpleFraction.create(1, 12);
        c = a.add(b);
        assertEquals(BigInteger.valueOf(1), c.getNumerator());
        assertEquals(BigInteger.valueOf(3), c.getDenominator());

        a = SimpleFraction.create(0, 1);
        b = SimpleFraction.create(1, 12);
        c = a.add(b);
        assertEquals(BigInteger.valueOf(1), c.getNumerator());
        assertEquals(BigInteger.valueOf(12), c.getDenominator());

        a = SimpleFraction.create(0, 0);
        b = SimpleFraction.create(1, 12);
        c = a.add(b);
        assertEquals(BigInteger.valueOf(0), c.getNumerator());
        assertEquals(BigInteger.valueOf(0), c.getDenominator());
    }

    @Test
    public void testSubtract() {
        SimpleFraction a = SimpleFraction.create(1, 2);
        SimpleFraction b = SimpleFraction.create(1, 2);
        SimpleFraction c = a.subtract(b);
        assertEquals(BigInteger.ZERO, c.getNumerator());
        assertEquals(BigInteger.valueOf(1), c.getDenominator());

        a = SimpleFraction.create(1, 2);
        b = SimpleFraction.create(1, 3);
        c = a.subtract(b);
        assertEquals(BigInteger.valueOf(1), c.getNumerator());
        assertEquals(BigInteger.valueOf(6), c.getDenominator());

        a = SimpleFraction.create(1, 2);
        b = SimpleFraction.create(2, 3);
        c = a.subtract(b);
        assertEquals(BigInteger.valueOf(-1), c.getNumerator());
        assertEquals(BigInteger.valueOf(6), c.getDenominator());

        a = SimpleFraction.create(1, 4);
        b = SimpleFraction.create(1, 12);
        c = a.subtract(b);
        assertEquals(BigInteger.valueOf(1), c.getNumerator());
        assertEquals(BigInteger.valueOf(6), c.getDenominator());

        a = SimpleFraction.create(0, 1);
        b = SimpleFraction.create(1, 12);
        c = a.subtract(b);
        assertEquals(BigInteger.valueOf(-1), c.getNumerator());
        assertEquals(BigInteger.valueOf(12), c.getDenominator());

        a = SimpleFraction.create(0, 0);
        b = SimpleFraction.create(1, 12);
        c = a.subtract(b);
        assertEquals(BigInteger.valueOf(0), c.getNumerator());
        assertEquals(BigInteger.valueOf(0), c.getDenominator());
    }

    @Test
    public void testMultiply() {
        SimpleFraction a = SimpleFraction.create(1, 2);
        SimpleFraction b = SimpleFraction.create(1, 2);
        SimpleFraction c = a.multiply(b);
        assertEquals(BigInteger.ONE, c.getNumerator());
        assertEquals(BigInteger.valueOf(4), c.getDenominator());

        a = SimpleFraction.create(1, 2);
        b = SimpleFraction.create(1, 3);
        c = a.multiply(b);
        assertEquals(BigInteger.valueOf(1), c.getNumerator());
        assertEquals(BigInteger.valueOf(6), c.getDenominator());

        a = SimpleFraction.create(1, 2);
        b = SimpleFraction.create(2, 3);
        c = a.multiply(b);
        assertEquals(BigInteger.valueOf(1), c.getNumerator());
        assertEquals(BigInteger.valueOf(3), c.getDenominator());

        a = SimpleFraction.create(3, 4);
        b = SimpleFraction.create(1, 3);
        c = a.multiply(b);
        assertEquals(BigInteger.valueOf(1), c.getNumerator());
        assertEquals(BigInteger.valueOf(4), c.getDenominator());

        a = SimpleFraction.create(0, 1);
        b = SimpleFraction.create(1, 12);
        c = a.multiply(b);
        assertEquals(BigInteger.valueOf(0), c.getNumerator());
        assertEquals(BigInteger.valueOf(1), c.getDenominator());

        a = SimpleFraction.create(0, 0);
        b = SimpleFraction.create(1, 12);
        c = a.multiply(b);
        assertEquals(BigInteger.valueOf(0), c.getNumerator());
        assertEquals(BigInteger.valueOf(0), c.getDenominator());

        a = SimpleFraction.create(1, 2);
        c = a.multiply(3);
        assertEquals(BigInteger.valueOf(3), c.getNumerator());
        assertEquals(BigInteger.valueOf(2), c.getDenominator());

        a = SimpleFraction.create(1, 2);
        c = a.multiply(4);
        assertEquals(BigInteger.valueOf(2), c.getNumerator());
        assertEquals(BigInteger.valueOf(1), c.getDenominator());
    }

    @Test
    public void testDivide() {
        SimpleFraction a = SimpleFraction.create(1, 2);
        SimpleFraction b = SimpleFraction.create(1, 2);
        SimpleFraction c = a.divide(b);
        assertEquals(BigInteger.ONE, c.getNumerator());
        assertEquals(BigInteger.valueOf(1), c.getDenominator());

        a = SimpleFraction.create(1, 2);
        b = SimpleFraction.create(1, 3);
        c = a.divide(b);
        assertEquals(BigInteger.valueOf(3), c.getNumerator());
        assertEquals(BigInteger.valueOf(2), c.getDenominator());

        a = SimpleFraction.create(1, 2);
        b = SimpleFraction.create(2, 3);
        c = a.divide(b);
        assertEquals(BigInteger.valueOf(3), c.getNumerator());
        assertEquals(BigInteger.valueOf(4), c.getDenominator());

        a = SimpleFraction.create(3, 4);
        b = SimpleFraction.create(1, 3);
        c = a.divide(b);
        assertEquals(BigInteger.valueOf(9), c.getNumerator());
        assertEquals(BigInteger.valueOf(4), c.getDenominator());

        a = SimpleFraction.create(0, 1);
        b = SimpleFraction.create(1, 12);
        c = a.divide(b);
        assertEquals(BigInteger.valueOf(0), c.getNumerator());
        assertEquals(BigInteger.valueOf(1), c.getDenominator());

        a = SimpleFraction.create(0, 0);
        b = SimpleFraction.create(1, 12);
        c = a.divide(b);
        assertEquals(BigInteger.valueOf(0), c.getNumerator());
        assertEquals(BigInteger.valueOf(0), c.getDenominator());

        a = SimpleFraction.create(1, 3);
        b = SimpleFraction.create(0, 0);
        c = a.divide(b);
        assertEquals(BigInteger.valueOf(0), c.getNumerator());
        assertEquals(BigInteger.valueOf(0), c.getDenominator());

        a = SimpleFraction.create(1, 3);
        b = SimpleFraction.create(0, 0);
        c = a.divide(b);
        assertEquals(BigInteger.valueOf(0), c.getNumerator());
        assertEquals(BigInteger.valueOf(0), c.getDenominator());

        a = SimpleFraction.create(1, 3);
        c = a.divide(2);
        assertEquals(BigInteger.valueOf(1), c.getNumerator());
        assertEquals(BigInteger.valueOf(6), c.getDenominator());

        a = SimpleFraction.create(2, 3);
        c = a.divide(2);
        assertEquals(BigInteger.valueOf(1), c.getNumerator());
        assertEquals(BigInteger.valueOf(3), c.getDenominator());
    }

    @Test
    public void testReciprocal() {
        SimpleFraction a = SimpleFraction.create(2, 3);
        SimpleFraction c = a.reciprocal();
        assertEquals(BigInteger.valueOf(3), c.getNumerator());
        assertEquals(BigInteger.valueOf(2), c.getDenominator());
    }


}