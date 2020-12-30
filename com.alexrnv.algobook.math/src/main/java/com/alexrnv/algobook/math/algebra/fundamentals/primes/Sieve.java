package com.alexrnv.algobook.math.algebra.fundamentals.primes;

import java.util.BitSet;

/**
 * Optimized version of the sieve that tracks only odd numbers.
 * First prime number 2 is not present in the sieve.
 */
class Sieve {
    private final long size;
    private final BitSet sieve;

    Sieve(long size) {
        this.size = size;
        this.sieve = new BitSet((int)(size >> 1));
    }

    void mark(long prime) {
        for (long j = prime * prime; j <= size; j += 2 * prime) {
            sieve.set(bitIndex(j));
        }
    }

    boolean get(long i) {
        return sieve.get(bitIndex(i));
    }

    private int bitIndex(long i) {
        return (int) ((i >> 1) - 1);
    }

}
