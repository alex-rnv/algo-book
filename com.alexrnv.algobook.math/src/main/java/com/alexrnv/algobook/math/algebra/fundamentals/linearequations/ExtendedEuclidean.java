package com.alexrnv.algobook.math.algebra.fundamentals.linearequations;

import java.math.BigInteger;

/**
 * For the equations of the type a⋅x+b⋅y=gcd(a,b),
 * calculates both gcd(a,b) and the space of possible values (x,y), satisfying the equation.
 */
public class ExtendedEuclidean {

    public static EuclideanSolutionSpace solve(long a, long b) {
        return solve(BigInteger.valueOf(a), BigInteger.valueOf(b));
    }

    public static EuclideanSolutionSpace solve(BigInteger a, BigInteger b) {
        if (a.equals(BigInteger.ZERO)) return new EuclideanSolutionSpace(BigInteger.ZERO, b, BigInteger.ZERO, BigInteger.ZERO, b);
        if (b.equals(BigInteger.ZERO)) return new EuclideanSolutionSpace(a, BigInteger.ZERO, BigInteger.ZERO, BigInteger.ZERO, a);

        BigInteger x = BigInteger.ONE, y = BigInteger.ZERO, x0 = BigInteger.ZERO, y0 = BigInteger.ONE, t, a0 = a, b0 = b;
        while (!b.equals(BigInteger.ZERO)) {
            BigInteger q = a.divide(b);
            t = x;
            x = x0;
            x0 = t.subtract(q.multiply(x0));
            t = y;
            y = y0;
            y0 = t.subtract(q.multiply(y0));
            t = a;
            a = b;
            b = t.subtract(q.multiply(b));
        }
        return new EuclideanSolutionSpace(x, y,
                a.equals(BigInteger.ZERO) ? BigInteger.ZERO : b0.divide(a),
                a.equals(BigInteger.ZERO) ? BigInteger.ZERO : a0.negate().divide(a),
                a);
    }
}
