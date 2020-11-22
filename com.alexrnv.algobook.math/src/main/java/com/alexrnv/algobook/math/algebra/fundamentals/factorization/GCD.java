package com.alexrnv.algobook.math.algebra.fundamentals.factorization;

import java.math.BigInteger;
import java.util.Arrays;

public class GCD {

    /**
     * Greatest common divisor (highest common factor) of two long integers.
     * Current implementation returns GCD of absolute values of a and b, so the result always exists and positive.
     * @return greatest common divisor of the modules of a and b
     */
    public static long gcd(long a, long b) {
        if (a<0) a=-a;
        if (b<0) b=-b;

        long t;
        while(b > 0) {
            t=b;
            b = a % b;
            a=t;
        }
        return a;
    }

    /**
     * Knuth "The Art Of Programming vol.1" Chapter 1.1, ex.3.
     * Current implementation returns GCD of absolute values of a and b, so the result always exists and positive.
     * @return greatest common divisor of the modules of a and b
     */
    public static long gcd2(long a, long b) {
        if (a<0) a=-a;
        if (b<0) b=-b;

        if (a > b) {
            while(true) {
                a = a % b;
                if (a == 0) return b;
                b = b % a;
                if (b == 0) return a;
            }
        } else {
            while(true) {
                b = b % a;
                if (b == 0) return a;
                a = a % b;
                if (a == 0) return b;
            }
        }
    }

    /**
     * GCD of variable-size array of long integers.
     * Uses divide-and-conquer approach and uses {@link GCD#gcd(long, long)} operation pairwise,
     * iteratively reduces the size of array, until one common GCD is found.
     * input arrays.
     * Current implementation ignores the sign and converts every negative number into positive before the calculation.
     * @return greatest common divisor of the modules of a and b
     */
    public static long gcd(long a, long... bb) {
        //copy once and reuse iteratively
        long[] aux = new long[bb.length + 1];
        aux[0] = a;
        System.arraycopy(bb, 0, aux, 1, bb.length);
        return gcd(aux, aux.length);
    }

    /**
     * GCD of fixed-size array of long integers. Implementation is similar to  {@link GCD#gcd(long, long...)}.
     * @see GCD#gcd(long, long...)
     * @return greatest common divisor of the modules of a and b
     */
    public static long gcd(long[] a) {
        //copy once and reuse iteratively
        long[] aux = Arrays.copyOf(a, a.length);
        return gcd(aux, aux.length);
    }

    private static long gcd(long[] a, int len) {
        if (len == 1) return a[0];
        if (len == 2) return gcd(a[0], a[1]);

        int lenOfDivided = len / 2;

        for (int i=0; i<lenOfDivided; i++) {
            int j = 2*i;
            a[i] = gcd(a[j], a[j+1]);
        }
        if (len % 2 != 0) {
            a[lenOfDivided] = a[len-1];
            lenOfDivided++;
        }
        return gcd(a, lenOfDivided);
    }

    /**
     * Calculates GCD using Java's BigInteger arithmetic.
     */
    protected static long gcdJVM(long a, long b) {
        return BigInteger.valueOf(a).gcd(BigInteger.valueOf(b)).longValueExact();
    }
}
