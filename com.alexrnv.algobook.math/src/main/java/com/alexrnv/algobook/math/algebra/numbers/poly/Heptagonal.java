package com.alexrnv.algobook.math.algebra.numbers.poly;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/**
 * H7n=n(5n−3)/2	 	1, 7, 18, 34, 55, ...
 */
public class Heptagonal {
    public static final Set<Long> HEPTAGONAL_NUM_TABLE = new HashSet<>();
    private static final int TABLE_SIZE = 10000;

    static {
        for (int n=1; n<TABLE_SIZE; n++) {
            HEPTAGONAL_NUM_TABLE.add(heptagonal(n));
        }
    }

    public static long heptagonal(long n) {
        return (n * (5*n-3)) >> 1;
    }

    public static BigInteger heptagonal(BigInteger n) {
        return n.multiply(BigInteger.valueOf(5)).subtract(BigInteger.valueOf(3)).multiply(n).divide(BigInteger.TWO);
    }

    public static boolean isHeptagonalNumber(long x) {
        return x < TABLE_SIZE ? isInHeptagonalTable(x) : isHeptagonalByFormula(BigInteger.valueOf(x));
    }

    protected static boolean isInHeptagonalTable(long x) {
        return HEPTAGONAL_NUM_TABLE.contains(x);
    }

    protected static boolean isHeptagonalByFormula(BigInteger x) {
        BigInteger tst = x.multiply(BigInteger.valueOf(40)).add(BigInteger.valueOf(9)).sqrt().add(BigInteger.valueOf(3)).divide(BigInteger.valueOf(10));
        return heptagonal(tst).equals(x);
    }
}
