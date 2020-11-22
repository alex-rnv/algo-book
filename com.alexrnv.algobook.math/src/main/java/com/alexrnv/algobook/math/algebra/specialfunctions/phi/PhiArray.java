package com.alexrnv.algobook.math.algebra.specialfunctions.phi;

/**
 * Convenience class to calculate, store and retrieve phi function values in realtime.
 * Since the most efficient phi function calculation for n involves fiddling with previous n-1
 * results, for efficiency we keep all the previously calculated values accessible.
 * One can think of this as an extension of {@link PhiIterator}, which allows jumping back and forth
 * between already iterated values.
 */
public class PhiArray extends PhiIterator {

    PhiArray(int limit) {
        super(limit);
    }

    public long get(int n) {
        if (n > sieve.length) return -1;
        while (n > current) {
            next();
        }
        return sieve[n];
    }

    protected void fillTheTable(long[] table) {
        get(table.length - 1);
        System.arraycopy(sieve, 0, table, 0, table.length);
    }
}
