package com.alexrnv.algobook.math.algebra.fundamentals.primes;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PrimesIteratorTest {

    @Test
    public void testCompareWithSimpleSieve() {
        int limit = 100_000;
        List<Long> primesEratosthenes = Primes.primes(limit);
        List<Long> primesIterator = readAll(PrimesIteratorFactory.createIterator(limit));

        assertListsEqual(primesEratosthenes, primesIterator);
    }

    @Test
    public void testCompareWithSimpleSieveSmallNumber() {
        int limit = 1000;
        List<Long> primesEratosthenes = Primes.primes(limit);
        List<Long> primesIterator = readAll(PrimesIteratorFactory.createIterator(limit));

        assertListsEqual(primesEratosthenes, primesIterator);
    }

    @Test
    public void testCompareWithSimpleSieveBlockSize() {
        int limit = PrimesIteratorFactory.BLOCK_SIZE;
        List<Long> primesEratosthenes = Primes.primes(limit);
        List<Long> primesIterator = readAll(PrimesIteratorFactory.createIterator(limit));

        assertListsEqual(primesEratosthenes, primesIterator);
    }

    @Test
    public void testCompareWithSimpleSieveBlockSizePlusOne() {
        int limit = PrimesIteratorFactory.BLOCK_SIZE + 1;
        List<Long> primesEratosthenes = Primes.primes(limit);
        List<Long> primesIterator = readAll(PrimesIteratorFactory.createIterator(limit));

        assertListsEqual(primesEratosthenes, primesIterator);
    }

    @Test
    public void testCompareWithSimpleSieveDoubleBlockSize() {
        int limit = 2 * PrimesIteratorFactory.BLOCK_SIZE;
        List<Long> primesEratosthenes = Primes.primes(limit);
        List<Long> primesIterator = readAll(PrimesIteratorFactory.createIterator(limit));

        assertListsEqual(primesEratosthenes, primesIterator);
    }

    @Test
    public void testCompareWithSimpleSieveDoubleBlockSizePlusOne() {
        int limit = 2 * PrimesIteratorFactory.BLOCK_SIZE + 1;
        List<Long> primesEratosthenes = Primes.primes(limit);
        List<Long> primesIterator = readAll(PrimesIteratorFactory.createIterator(limit));

        assertListsEqual(primesEratosthenes, primesIterator);
    }

    @Test
    @Ignore
    public void testCompareWithSimpleSieveBigNumberOdd() {
        int limit = Integer.MAX_VALUE;
        long t0 = System.currentTimeMillis();
        List<Long> primesEratosthenes = Primes.primes(limit);
        System.out.printf("Sieve of Eratosthenes: %dms\n", System.currentTimeMillis() - t0);
        t0 = System.currentTimeMillis();
        List<Long> primesIterator = readAll(PrimesIteratorFactory.createIterator(limit));
        System.out.printf("Iterator with segmented sieve: %dms\n", System.currentTimeMillis() - t0);

        assertListsEqual(primesEratosthenes, primesIterator);
    }

    @Test
    @Ignore
    public void testCompareWithSimpleSieveBigNumberEven() {
        int limit = Integer.MAX_VALUE - 1;
        long t0 = System.currentTimeMillis();
        List<Long> primesEratosthenes = Primes.primes(limit);
        System.out.printf("Sieve of Eratosthenes: %dms\n", System.currentTimeMillis() - t0);
        t0 = System.currentTimeMillis();
        List<Long> primesIterator = readAll(PrimesIteratorFactory.createIterator(limit));
        System.out.printf("Iterator with segmented sieve: %dms\n", System.currentTimeMillis() - t0);

        assertListsEqual(primesEratosthenes, primesIterator);
    }

    static List<Long> readAll(Iterator<Long> iter) {
        List<Long> result = new ArrayList<>();
        while(iter.hasNext()) {
            result.add(iter.next());
        }
        return result;
    }

    static void assertListsEqual(List<Long> list1, List<Long> list2) {
        assertEquals(list1.size(), list2.size());
        for (int i=0; i< list1.size(); i++) {
            assertEquals(list1.get(i), list2.get(i));
        }
    }

}