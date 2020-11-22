package com.alexrnv.algobook.math.algebra.fundamentals;

import com.alexrnv.algobook.util.Triple;

/**
 * Pythagorean triple consists of three positive integers, such that a^2 + b^2 = c^2, they form the right triangle with
 * sides a,b,c.
 * https://en.wikipedia.org/wiki/Pythagorean_triple
 * */
public class PythagoreanTriple {

    /**
     * Generates triple (m^2-n^2, 2mn, m^2+n^2) as per Euclid's formula. If n>m, parameters are swapped.
     * Resulting triple is always sorted a<b<c.
     */
    public static Triple<Long> generateFor(long n, long m) {
        assert n > 0 && m > 0;
        long t;
        if (m < n) {
            t = m; m = n; n = t;
        }
        long a = m*m - n*n;
        long b = 2*n*m;
        long c = m*m + n*n;
        if (b < a) {
            t = a; a = b; b = t;
        }
        if (b > c) {
            t = b; b = c; c = t;
        }
        return new Triple<>(a, b, c);
    }

}
