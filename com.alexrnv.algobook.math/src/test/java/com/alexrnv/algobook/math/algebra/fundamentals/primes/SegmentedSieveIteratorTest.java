package com.alexrnv.algobook.math.algebra.fundamentals.primes;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SegmentedSieveIteratorTest {

    @Test
    public void testSmallBlockSize() {
        assertEquals(997, getLastPrime(1000, 8));
        assertEquals(997, getLastPrime(1000, 16));
        assertEquals(997, getLastPrime(1000, 15));
        assertEquals(997, getLastPrime(1000, 17));
        assertEquals(997, getLastPrime(1000, 30));
        assertEquals(997, getLastPrime(1000, 31));
        assertEquals(997, getLastPrime(1000, 32));
        assertEquals(997, getLastPrime(1000, 33));
        assertEquals(997, getLastPrime(1000, 35));
    }

    private long getLastPrime(int upperBound, int blockSize) {
        SegmentedSieveIterator iterator = new SegmentedSieveIterator(upperBound, blockSize);
        long prime = 0;
        while (iterator.hasNext()) {
            prime = iterator.next();
        }
        return prime;
    }
}