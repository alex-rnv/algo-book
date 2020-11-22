package com.alexrnv.algobook.math.algebra.numbers.poly.penta;

import java.math.BigInteger;

/**
 * Pn=n(3n−1)/2	 	1, 5, 12, 22, 35, ...
 */
public class Pentagonal {

    protected static final int TABLE_SIZE = 10000;
    protected static final PentagonalCalculator CALCULATOR = new PentagonalCalculator(0, TABLE_SIZE);
    protected static final PentagonalChecker CHECKER = new PentagonalChecker(CALCULATOR.precalculated ,0, TABLE_SIZE);

    public static long pentagonal(long n) {
        return CALCULATOR.calculate(n);
    }

    public static BigInteger pentagonal(BigInteger n) {
        return CALCULATOR.calculate(n);
    }

    public static boolean isPentagonalNumber(long x) {
        return CHECKER.isPentagonalNumber(x);
    }

    public static PentagonalChecker newChecker(long precalculateFrom, long precalculateTo) {
        return new PentagonalChecker(precalculateFrom, precalculateTo);
    }

    public static PentagonalCalculator newCalculator(int precalculateFrom, int precalculateTo) {
        return new PentagonalCalculator(precalculateFrom, precalculateTo);
    }
}
