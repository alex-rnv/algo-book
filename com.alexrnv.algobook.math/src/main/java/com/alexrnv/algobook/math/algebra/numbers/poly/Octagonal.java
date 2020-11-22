package com.alexrnv.algobook.math.algebra.numbers.poly;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/**
 * On=n(3n−2)	 	1, 8, 21, 40, 65, ...
 */
public class Octagonal {

    public static final Set<Long> OCTAGONAL_NUM_TABLE = new HashSet<>();
    private static final int TABLE_SIZE = 10000;

    static {
        for (int n=1; n<TABLE_SIZE; n++) {
            OCTAGONAL_NUM_TABLE.add(octagonal(n));
        }
    }

    public static long octagonal(long n) {
        return n * (3*n-2);
    }

    public static BigInteger octagonal(BigInteger n) {
        return n.multiply(BigInteger.valueOf(3)).subtract(BigInteger.valueOf(2)).multiply(n);
    }

    public static boolean isOctagonalNumber(long x) {
        return x < TABLE_SIZE ? isInOctagonalTable(x) : isOctagonalByFormula(BigInteger.valueOf(x));
    }

    protected static boolean isInOctagonalTable(long x) {
        return OCTAGONAL_NUM_TABLE.contains(x);
    }

    protected static boolean isOctagonalByFormula(BigInteger x) {
        BigInteger tst = x.multiply(BigInteger.valueOf(12)).add(BigInteger.valueOf(4)).sqrt().add(BigInteger.valueOf(2)).divide(BigInteger.valueOf(6));
        return octagonal(tst).equals(x);
    }
}
