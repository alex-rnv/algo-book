package com.alexrnv.algobook.math.algebra.specialfunctions.phi;

import java.util.Iterator;

class PhiIterator implements Iterator<Long> {

    protected int current;
    protected final long[] sieve;

    PhiIterator(int limit) {
        this(0, limit);
    }

    private PhiIterator(int from, int to) {
        assert from >= 0 && to > 1 && to < Integer.MAX_VALUE && from < to;
        this.sieve = new long[to + 1];
        for (int i = 0; i <= to; i++) this.sieve[i] = i;
        shift(from);
    }

    @Override
    public boolean hasNext() {
        return current < sieve.length;
    }

    @Override
    public Long next() {
        sift();
        return sieve[current++];
    }

    private void shift(int until) {
        while (current < until) next();
    }

    private void sift() {
        if (current < 2) return;

        if (sieve[current] == current) {
            for (int i = current; i < sieve.length; i += current) {
                sieve[i] -= sieve[i] / current;
            }
        }
    }
}
