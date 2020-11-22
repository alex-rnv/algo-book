package com.alexrnv.algobook.math.algebra.numbers.poly;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/**
 * H6n=n(2n−1)	 	1, 6, 15, 28, 45, ...
 */
public class Hexagonal {
    public static final Set<Long> HEXAGONAL_NUM_TABLE = new HashSet<>();
    private static final int TABLE_SIZE = 10000;

    static {
        for (int n=1; n<TABLE_SIZE; n++) {
            HEXAGONAL_NUM_TABLE.add(hexagonal(n));
        }
    }

    public static long hexagonal(long n) {
        return n * ((n<<1)-1);
    }

    public static BigInteger hexagonal(BigInteger n) {
        return n.multiply(BigInteger.TWO).subtract(BigInteger.ONE).multiply(n);
    }

    public static boolean isHexagonalNumber(long x) {
        return x < TABLE_SIZE ? isInHexagonalTable(x) : isHexagonalByFormula(BigInteger.valueOf(x));
    }

    protected static boolean isInHexagonalTable(long x) {
        return HEXAGONAL_NUM_TABLE.contains(x);
    }

    protected static boolean isHexagonalByFormula(BigInteger x) {
        BigInteger tst = x.multiply(BigInteger.valueOf(8).add(BigInteger.ONE)).sqrt().add(BigInteger.ONE).divide(BigInteger.valueOf(4));
        return hexagonal(tst).equals(x);
    }
}
