package com.alexrnv.algobook.math.algebra.specialfunctions.phi;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

public class PhiFunctionTest {

    @Test
    public void testCalcSimple() {
        Assert.assertEquals(PhiFunction.calculate(1), 1);
        Assert.assertEquals(PhiFunction.calculate(2), 1);
        Assert.assertEquals(PhiFunction.calculate(3), 2);
        Assert.assertEquals(PhiFunction.calculate(4), 2);
        Assert.assertEquals(PhiFunction.calculate(5), 4);
        Assert.assertEquals(PhiFunction.calculate(6), 2);
        Assert.assertEquals(PhiFunction.calculate(7), 6);
        Assert.assertEquals(PhiFunction.calculate(8), 4);
        Assert.assertEquals(PhiFunction.calculate(9), 6);
        Assert.assertEquals(PhiFunction.calculate(10),4);
        Assert.assertEquals(PhiFunction.calculate(11),10);
        Assert.assertEquals(PhiFunction.calculate(12),4);
        Assert.assertEquals(PhiFunction.calculate(13),12);
        Assert.assertEquals(PhiFunction.calculate(14),6);
        Assert.assertEquals(PhiFunction.calculate(15),8);
        Assert.assertEquals(PhiFunction.calculate(16),8);
        Assert.assertEquals(PhiFunction.calculate(17),16);
        Assert.assertEquals(PhiFunction.calculate(18),6);
        Assert.assertEquals(PhiFunction.calculate(19),18);
        Assert.assertEquals(PhiFunction.calculate(20),8);
        Assert.assertEquals(PhiFunction.calculate(21),12);
    }

    @Test
    public void testTable() {
        for (int n = 0; n < PhiFunction.PHI_TABLE_SIZE; n++) {
            Assert.assertEquals(PhiFunction.PHI_TABLE[n], PhiFunction.calculate(n));
        }
    }

    @Test
    public void testCompareCalcIterator() {
        int N = 100;
        Iterator<Long> iterator = PhiFunction.iterator(N);
        for (int n = 0; n <= N; n++) {
            Assert.assertEquals(PhiFunction.calculate(n), (long) iterator.next());
        }
    }

    @Test
    public void testAssertIteratorLargeN() {
        int N = 10_000_000;
        Iterator<Long> iterator = PhiFunction.iterator(N);
        long n = 0, t = System.currentTimeMillis();
        while (iterator.hasNext()) {
            n = iterator.next();
        }
        System.out.printf("phi(%d)=%d, time to calculate: %d ms", N, n, System.currentTimeMillis()-t);
        Assert.assertEquals(4000000, n);
    }
}