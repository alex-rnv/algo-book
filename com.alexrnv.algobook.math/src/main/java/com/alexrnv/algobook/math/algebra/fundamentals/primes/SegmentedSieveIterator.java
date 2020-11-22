package com.alexrnv.algobook.math.algebra.fundamentals.primes;

import java.util.ArrayList;

class SegmentedSieveIterator extends SimpleSieveIterator {
    private final ArrayList<Long> basePrimes;
    private final int blockSize;
    private int blockIndex;
    private BlockIterator blockIterator;

    SegmentedSieveIterator(long upperBound, int blockSize) {
        super(upperBound);
        this.basePrimes = new ArrayList<>((int) Math.log(sieveSize) + 1);
        this.blockSize = blockSize;
        this.blockIndex = (int)(sieveSize / blockSize);
        basePrimes.add(currentPrime);
    }

    @Override
    protected long calculateSieveSize() {
        return (long) Math.sqrt(upperBound);
    }

    @Override
    protected void calculateNextPrime() {
        if (currentPrime == PRIME_0) {
            currentPrime = PRIME_1;
            basePrimes.add(currentPrime);
            baseSieve.mark(currentPrime);
        } else if (currentPrime < sieveSize) {
            prepareNextPrimeInSieve();
        } else {
            prepareNextPrimeInSegment();
        }
    }

    @Override
    protected boolean prepareNextPrimeInSieve() {
        if (super.prepareNextPrimeInSieve()) {
            basePrimes.add(currentPrime);
            return true;
        } else {
            prepareNextPrimeInSegment();
            return false;
        }
    }

    @Override
    protected void sieveOverflow() {}

    private void prepareNextPrimeInSegment() {
        if (blockIterator == null) {
            blockIterator = new BlockIterator(blockSize, upperBound, blockIndex, basePrimes);
        }
        long prime = blockIterator.nextPrime();
        if (prime == 0) {
            hasNext = false;
            return;
        }
        currentPrime = prime;
    }

}
