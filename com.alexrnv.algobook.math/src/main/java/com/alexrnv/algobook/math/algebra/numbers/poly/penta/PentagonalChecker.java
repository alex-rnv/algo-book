package com.alexrnv.algobook.math.algebra.numbers.poly.penta;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PentagonalChecker {

    protected final Set<Long> pentagonalNumTable;
    protected final long precalculateFrom;
    protected final long precalculateTo;

    protected PentagonalChecker(long precalculateFrom, long precalculateTo) {
        long d = precalculateTo - precalculateFrom;
        assert d >= 0 && d < Integer.MAX_VALUE;
        this.precalculateFrom = precalculateFrom;
        this.precalculateTo = precalculateTo;
        this.pentagonalNumTable = precalculate(precalculateFrom, precalculateTo);
    }

    protected PentagonalChecker(long[] precalculated, int from, int to) {
        long d = to - from;
        assert d >= 0 && d < Integer.MAX_VALUE;
        this.precalculateFrom = from;
        this.precalculateTo = to;
        this.pentagonalNumTable = Arrays.stream(precalculated).boxed().collect(Collectors.toSet());
    }

    private Set<Long> precalculate(long precalculateFrom, long precalculateTo) {
        Set<Long> table = new HashSet<>((int) (precalculateTo - precalculateFrom));
        for (long x = precalculateFrom; x < precalculateFrom + precalculateTo; x++) {
            table.add(Pentagonal.pentagonal(x));
        }
        return table;
    }

    public boolean isPentagonalNumber(long x) {
        return shouldCheckTable(x) ? isInPentagonalTable(x) : isPentagonalByFormula(BigInteger.valueOf(x));
    }

    protected boolean isInPentagonalTable(long x) {
        return pentagonalNumTable.contains(x);
    }

    protected boolean isPentagonalByFormula(BigInteger x) {
        BigInteger tst = x.multiply(BigInteger.valueOf(24).add(BigInteger.ONE)).sqrt().add(BigInteger.ONE).divide(BigInteger.valueOf(6));
        return Pentagonal.pentagonal(tst).equals(x);
    }

    private boolean shouldCheckTable(long x) {
        return x >= precalculateFrom && x < precalculateTo;
    }
}
