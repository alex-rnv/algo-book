package com.alexrnv.algobook.math.algebra.numbers.poly;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/**
 * Tn=n(n+1)/2	 	1, 3, 6, 10, 15, ...
 */
public class Triangular {

    private static final Set<Long> TRIANGULAR_NUM_TABLE = new HashSet<>();
    protected static final int TABLE_SIZE = 10000;

    /**
     * Upper threshold for input arguments in triangular() function. Higher values will cause long overflow.
     * BigInteger operations should be used instead.
     */
    protected static final long LONG_ARGUMENT_OVERFLOW = quadraticRootWithOverflow(Long.MAX_VALUE).longValueExact() + 1;

    /**
     * Upper threshold for internal long arithmetic. Calculations involve multiplication by 8.
     * For higher values BigInteger is used internally.
     */
    protected static final long LONG_CALCULATION_OVERFLOW = Long.MAX_VALUE >> 3;

    static {
        for (int n=1; n<TABLE_SIZE; n++) {
            TRIANGULAR_NUM_TABLE.add(triangular(n));
        }
    }

    public static boolean causesOverflow(long x) {
        return x >= LONG_ARGUMENT_OVERFLOW;
    }

    /**
     * Faster version for long values, but implies the client checks the input argument first. Argument must be
     * less than {@link Triangular#LONG_ARGUMENT_OVERFLOW} = 4294967296.
     * Method {@link Triangular#causesOverflow(long)} performs this check.
     */
    public static long triangular(long x) {
        assert x > 0 && x < LONG_ARGUMENT_OVERFLOW;
        return triangularUnsafe(x);
    }

    protected static long triangularUnsafe(long x) {
        return (x&1) == 0 ? (x>>1) * (x+1) : x * ((x+1)>>1);
    }

    public static BigInteger triangular(BigInteger x) {
        return x.multiply(x.add(BigInteger.ONE)).divide(BigInteger.TWO);
    }

    public static boolean isTriangularNumber(long x) {
        assert x > 0 && x < LONG_ARGUMENT_OVERFLOW;
        return x < TABLE_SIZE ? isInTriangularTable(x) : isTriangularByFormula(x);
    }

    protected static boolean isInTriangularTable(long x) {
        return TRIANGULAR_NUM_TABLE.contains(x);
    }

    /**
     * https://en.wikipedia.org/wiki/Triangular_number#Triangular_roots_and_tests_for_triangular_numbers
     * TBD optimize
     */
    protected static boolean isTriangularByFormula(long x) {
        return x < LONG_CALCULATION_OVERFLOW ? isTriangularByFormulaUnsafe(x) : isTriangularByFormulaWithOverflow(BigInteger.valueOf(x));
    }

    private static boolean isTriangularByFormulaUnsafe(long x) {
        long x2 = x<<1;
        long tst = ((long)(Math.sqrt((x2<<2)+1) + 0.5) - 1) >> 1;
        return tst*tst + tst == x2;
    }

    public static boolean isTriangularNumber(BigInteger x) {
        return isTriangularByFormulaWithOverflow(x);
    }

    private static boolean isTriangularByFormulaWithOverflow(BigInteger x) {
        BigInteger tst = quadraticRootWithOverflow(x);
        return triangular(tst).equals(x);
    }

    private static BigInteger quadraticRootWithOverflow(BigInteger x) {
        return x.multiply(BigInteger.valueOf(8L))
                .add(BigInteger.ONE)
                .sqrt()
                .subtract(BigInteger.ONE)
                .divide(BigInteger.TWO);
    }

    private static BigInteger quadraticRootWithOverflow(long x) {
        return quadraticRootWithOverflow(BigInteger.valueOf(x));
    }


}
