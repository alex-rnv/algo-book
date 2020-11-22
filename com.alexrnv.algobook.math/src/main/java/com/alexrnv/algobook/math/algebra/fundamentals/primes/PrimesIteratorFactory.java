package com.alexrnv.algobook.math.algebra.fundamentals.primes;

class PrimesIteratorFactory {

    protected static final long MAX_VALUE = 2L * (long)Integer.MAX_VALUE * (long)Integer.MAX_VALUE;
    protected static final int BLOCK_SIZE = 64_000;

    public static PrimesIterator createIterator(long upperBound) {
        checkOverflow(upperBound);
        if (upperBound > BLOCK_SIZE) {
            return new SegmentedSieveIterator(upperBound, BLOCK_SIZE);
        } else {
            return new SimpleSieveIterator(upperBound);
        }
    }

    private static void checkOverflow(long upperBound) {
        if (MAX_VALUE <= upperBound)
            throw new ArithmeticException("Overflow: current segmented sieve implementation can only" +
                    "keep sqrt(upperBound) bits in memory, upper bound must be less than or equal to " + MAX_VALUE);
    }
}
