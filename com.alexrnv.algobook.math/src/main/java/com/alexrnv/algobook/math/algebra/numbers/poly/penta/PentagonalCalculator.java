package com.alexrnv.algobook.math.algebra.numbers.poly.penta;

import java.math.BigInteger;

public class PentagonalCalculator {

    protected final long[] precalculated;
    protected final int precalculateFrom;
    protected final int precalculateTo;

    protected PentagonalCalculator(int precalculateFrom, int precalculateTo) {
        long d = precalculateTo - precalculateFrom;
        assert d >= 0 && d < Integer.MAX_VALUE;
        this.precalculateFrom = precalculateFrom;
        this.precalculateTo = precalculateTo;
        this.precalculated = precalculate(precalculateFrom, precalculateTo);
    }

    private long[] precalculate(int precalculateFrom, int precalculateTo) {
        long[] array = new long[(int)(precalculateTo-precalculateFrom)];

        for (int x = precalculateFrom; x < precalculateTo; x++) {
            array[x - precalculateFrom] = calculateByFormula(x);
        }
        return array;
    }

    public long calculate(long n) {
        return isInTable(n) ? getPrecalculatedValue(n) : calculateByFormula(n);
    }

    protected long calculateByFormula(long n) {
        return (n * (3 * n - 1)) >> 1;
    }

    protected long getPrecalculatedValue(long n) {
        assert isInTable(n);
        return precalculated[(int) (n - precalculateFrom)];
    }

    public BigInteger calculate(BigInteger n) {
        return n.multiply(BigInteger.valueOf(3)).subtract(BigInteger.ONE).multiply(n).divide(BigInteger.TWO);
    }

    private boolean isInTable(long x) {
        return x >= precalculateFrom && x < precalculateTo;
    }
}
