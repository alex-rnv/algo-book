package com.alexrnv.algobook.math.algebra.fundamentals.primes;

class SimpleSieveIterator implements PrimesIterator {
    protected static final long PRIME_0 = 2L;
    protected static final long PRIME_1 = 3L;

    protected final long upperBound;
    protected final long sieveSize;
    protected final Sieve baseSieve;
    protected long currentPrime;
    protected boolean hasNext = true;

    protected SimpleSieveIterator(long upperBound) {
        this.currentPrime = PRIME_0;
        this.upperBound = upperBound;
        this.sieveSize = calculateSieveSize();
        this.baseSieve = new Sieve(sieveSize);
    }

    protected long calculateSieveSize() {
        return upperBound;
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public Long next() {
        long toReturn = currentPrime;
        calculateNextPrime();
        return toReturn;
    }

    protected void calculateNextPrime() {
        if (currentPrime == PRIME_0) {
            currentPrime = PRIME_1;
            this.baseSieve.mark(currentPrime);
        } else {
            prepareNextPrimeInSieve();
        }
    }

    protected boolean prepareNextPrimeInSieve() {
        long number = currentPrime + 2;
        while (true) {
            if (number >= sieveSize) {
                sieveOverflow();
                return false;
            }

            if (!this.baseSieve.get(number)) break;

            number += 2;
        }
        currentPrime = number;
        baseSieve.mark(currentPrime);
        return true;

    }

    protected void sieveOverflow() {
        hasNext = false;
    }
}
