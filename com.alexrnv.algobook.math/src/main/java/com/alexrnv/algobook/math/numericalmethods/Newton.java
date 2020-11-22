package com.alexrnv.algobook.math.numericalmethods;

import java.math.BigInteger;

public class Newton {

    public static BigInteger nearestIntegerRootBelow(BigInteger n) {
        //best practical initial approximation is 2^(num_bits/2)
        BigInteger a = BigInteger.ONE.shiftLeft(n.bitLength() / 2);
        boolean p_dec = false;
        for (;;) {
            BigInteger b = n.divide(a).add(a).shiftRight(1);
            if (a.compareTo(b) == 0 || a.compareTo(b) < 0 && p_dec)
                break;
            p_dec = a.compareTo(b) > 0;
            a = b;
        }
        return a;
    }

    public static long nearestIntegerRootBelow(long n) {
        long x = 1;
        boolean decreased = false;
        for (;;) {
            long nx = (x + n / x) >> 1;
            if (x == nx || nx > x && decreased)
                break;
            decreased = nx < x;
            x = nx;
        }
        return x;
    }
}
