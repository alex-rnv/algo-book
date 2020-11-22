package com.alexrnv.algobook.math.algebra.fundamentals;

import java.math.BigInteger;
import java.util.Arrays;

public class Arithmetic {

    public static long binpow(long a, long b) {
        assert b >= 0;
        long res = 1;
        while (b > 0) {
            if ((b & 1) != 0)
                res = res * a;
            a = a * a;
            b >>= 1;
        }
        return res;
    }

    private static final BigInteger[] FACTORIAL_TABLE = {
            BigInteger.ONE,
            BigInteger.ONE,
            BigInteger.valueOf(2L),
            BigInteger.valueOf(6L),
            BigInteger.valueOf(24L),
            BigInteger.valueOf(120L),
            BigInteger.valueOf(720L),
            BigInteger.valueOf(5040L),
            BigInteger.valueOf(40320L),
            BigInteger.valueOf(362880L),
            BigInteger.valueOf(3628800L),
            BigInteger.valueOf(39916800L),
            BigInteger.valueOf(479001600L),
            BigInteger.valueOf(6227020800L),
            BigInteger.valueOf(87178291200L),
            BigInteger.valueOf(1307674368000L),
            BigInteger.valueOf(20922789888000L),
            BigInteger.valueOf(355687428096000L),
            BigInteger.valueOf(6402373705728000L),
            BigInteger.valueOf(121645100408832000L),
            BigInteger.valueOf(2432902008176640000L)
    };

    public static BigInteger factorial(int n) {
        if (n < FACTORIAL_TABLE.length) return FACTORIAL_TABLE[n];
        BigInteger factorial = FACTORIAL_TABLE[FACTORIAL_TABLE.length-1];
        for (int k=FACTORIAL_TABLE.length; k<=n; k++) {
            factorial = factorial.multiply(BigInteger.valueOf(k));
        }
        return factorial;
    }

    /**
     * https://en.wikipedia.org/wiki/Repeating_decimal
     */
    public static String repeatingDecimalBase10(int p, int q) {
        int b = 10;
        int r = p / q;
        String digits = "0123456789";
        String s = "";   // the string of digits
        int pos = 0; // all places are right to the radix point
        int[] occurs = new int[q];
        Arrays.fill(occurs, -1);
        while (occurs[p] == -1) {
            occurs[p] = pos;  // the position of the place with remainder p
            int z = (int)Math.floor(b*p/q); // index z of digit within: 0 ≤ z ≤ b-1
            p = p*b - z*q;    // 0 ≤ p < q

            s += digits.substring(z, z+1);// append the character of the digit
            if (p == 0) {
                return "" + r + "." + s;
            }
            pos += 1;
        }

        return "" + r + "." + s.substring(0, occurs[p]) + "(" + s.substring(occurs[p]) + ")";
    }

}
