package com.alexrnv.algobook.math.algebra.fundamentals.factorization;

import org.junit.Assert;
import org.junit.Test;

public class DivisorsTest {

    @Test
    public void testFactorsAll() {
        assertFactorsAll(1, new long[]{1});
        assertFactorsAll(2, new long[]{2});
        assertFactorsAll(3, new long[]{3});
        assertFactorsAll(4, new long[]{2,2});
        assertFactorsAll(5, new long[]{5});
        assertFactorsAll(6, new long[]{2,3});
        assertFactorsAll(7, new long[]{7});
        assertFactorsAll(8, new long[]{2,2,2});
        assertFactorsAll(9, new long[]{3,3});
        assertFactorsAll(10, new long[]{2,5});
        assertFactorsAll(21, new long[]{3,7});
        assertFactorsAll(28, new long[]{2,2,7});
        assertFactorsAll(60, new long[]{2,2,3,5});
    }

    @Test
    public void testFactorsUnique() {
        assertFactorsUnique(1, new long[]{1});
        assertFactorsUnique(2, new long[]{2});
        assertFactorsUnique(3, new long[]{3});
        assertFactorsUnique(4, new long[]{2});
        assertFactorsUnique(5, new long[]{5});
        assertFactorsUnique(6, new long[]{2,3});
        assertFactorsUnique(7, new long[]{7});
        assertFactorsUnique(8, new long[]{2});
        assertFactorsUnique(9, new long[]{3});
        assertFactorsUnique(10, new long[]{2,5});
        assertFactorsUnique(21, new long[]{3,7});
        assertFactorsUnique(28, new long[]{2,7});
        assertFactorsUnique(60, new long[]{2,3,5});
    }

    @Test
    public void testNumberOfDivisors() {
        assertNumberOfDivisors(1, 1);
        assertNumberOfDivisors(2, 2);
        assertNumberOfDivisors(3, 2);
        assertNumberOfDivisors(4, 3);
        assertNumberOfDivisors(5, 2);
        assertNumberOfDivisors(6, 4);
        assertNumberOfDivisors(7, 2);
        assertNumberOfDivisors(8, 4);
        assertNumberOfDivisors(9, 3);
        assertNumberOfDivisors(10, 4);
        assertNumberOfDivisors(21, 4);
        assertNumberOfDivisors(24, 8);
        assertNumberOfDivisors(27, 4);
        assertNumberOfDivisors(28, 6);
        assertNumberOfDivisors(29, 2);
        assertNumberOfDivisors(31, 2);
        assertNumberOfDivisors(32, 6);
        assertNumberOfDivisors(33, 4);
        assertNumberOfDivisors(60, 12);
        assertNumberOfDivisors(63, 6);
        assertNumberOfDivisors(64, 7);
        assertNumberOfDivisors(1000, 16);
        assertNumberOfDivisors(1024, 11);
        assertNumberOfDivisors(100_000, 36);
        assertNumberOfDivisors(100_000_000, 81);
        assertNumberOfDivisors(1_000_000_000, 100);
        assertNumberOfDivisors(2_000_000_000, 110);
        assertNumberOfDivisors(76576500, 576);
    }

    @Test
    public void testSumOfDivisors() {
        assertSumOfDivisors(1, 1);
        assertSumOfDivisors(2, 3);
        assertSumOfDivisors(3, 4);
        assertSumOfDivisors(4, 7);
        assertSumOfDivisors(5, 6);
        assertSumOfDivisors(6, 12);
        assertSumOfDivisors(7, 8);
        assertSumOfDivisors(8, 15);
        assertSumOfDivisors(9, 13);
        assertSumOfDivisors(10, 18);
        assertSumOfDivisors(21, 32);
        assertSumOfDivisors(24, 60);
        assertSumOfDivisors(27, 40);
        assertSumOfDivisors(28, 56);
        assertSumOfDivisors(29, 30);
        assertSumOfDivisors(31, 32);
        assertSumOfDivisors(32, 63);
        assertSumOfDivisors(33, 48);
        assertSumOfDivisors(60, 168);
        assertSumOfDivisors(63, 104);
        assertSumOfDivisors(64, 127);
        assertSumOfDivisors(1000, 2340);
        assertSumOfDivisors(1024, 2047);
        assertSumOfDivisors(100_000, 246078);
    }

    private void assertFactorsAll(long n, long[] expectedFactors) {
        Assert.assertArrayEquals(
                expectedFactors,
                Divisors.factorsAll(n).stream().mapToLong(i->i).toArray()
        );
    }

    private void assertFactorsUnique(long n, long[] expectedFactors) {
        Assert.assertArrayEquals(
                expectedFactors,
                Divisors.factorsUnique(n).stream().mapToLong(i->i).toArray()
        );
    }

    private void assertNumberOfDivisors(int n, int expectedNumDivisors) {
        Assert.assertEquals(expectedNumDivisors, Divisors.numberOfDivisors(n));
    }

    private void assertSumOfDivisors(int n, int expectedSumOfDivisors) {
        Assert.assertEquals(expectedSumOfDivisors, Divisors.sumOfDivisors(n));
    }

}