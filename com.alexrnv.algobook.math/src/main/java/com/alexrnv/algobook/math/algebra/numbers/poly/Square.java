package com.alexrnv.algobook.math.algebra.numbers.poly;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public class Square {

    public static final Set<Long> SQUARE_NUM_TABLE = new HashSet<>();
    private static final int TABLE_SIZE = 10000;
    private static final BigInteger TABLE_SIZE_BI = BigInteger.valueOf(TABLE_SIZE);

    static {
        for (int n=1; n<TABLE_SIZE; n++) {
            SQUARE_NUM_TABLE.add(square(n));
        }
    }

    public static long square(long n) {
        return n*n;
    }

    public static BigInteger square(BigInteger n) {
        return n.multiply(n);
    }

    public static boolean isSquareNumber(long x) {
        return x < TABLE_SIZE ? isInSquareTable(x) : isSquareByFormula(BigInteger.valueOf(x));
    }

    public static boolean isSquareNumber(BigInteger x) {
        return x.compareTo(TABLE_SIZE_BI) < 0 ? isInSquareTable(x.intValue()) : isSquareByFormula(x);
    }

    protected static boolean isInSquareTable(long x) {
        return SQUARE_NUM_TABLE.contains(x);
    }

    protected static boolean isSquareByFormula(BigInteger x) {
        BigInteger tst = x.sqrt();
        return square(tst).equals(x);
    }
}
