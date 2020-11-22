package com.alexrnv.algobook.math.algebra.fundamentals.primes;

import org.junit.Assert;
import org.junit.Test;

public class PrimesTest {
    @Test
    public void testPrimes() {
        Assert.assertArrayEquals(
                new long[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97},
                Primes.primes(100).stream().mapToLong(i->i).toArray()
        );
    }

    @Test
    public void testIsPrime() {
        Assert.assertTrue(Primes.isPrime(2));
        Assert.assertTrue(Primes.isPrime(3));
        Assert.assertTrue(Primes.isPrime(5));
        Assert.assertTrue(Primes.isPrime(7));
        Assert.assertTrue(Primes.isPrime(11));
        Assert.assertTrue(Primes.isPrime(472_882_027));

        Assert.assertFalse(Primes.isPrime(4));
        Assert.assertFalse(Primes.isPrime(6));
        Assert.assertFalse(Primes.isPrime(8));
        Assert.assertFalse(Primes.isPrime(9));
        Assert.assertFalse(Primes.isPrime(10));
        Assert.assertFalse(Primes.isPrime(15));
        Assert.assertFalse(Primes.isPrime(99));
    }

}