package com.alexrnv.algobook.math.algebra.fundamentals.linearequations;

import java.math.BigInteger;

/**
 * Extended Euclidean algorithm produces GCD of x and y, so it is kept as part of the solution.
 * @see SolutionSpace
 */
public class EuclideanSolutionSpace extends SolutionSpace {

    private final BigInteger gcd;

    public EuclideanSolutionSpace(long x0, long y0, long dx, long dy, long gcd) {
        this(BigInteger.valueOf(x0), BigInteger.valueOf(y0), BigInteger.valueOf(dx), BigInteger.valueOf(dy), BigInteger.valueOf(gcd));
    }

    public EuclideanSolutionSpace(BigInteger x0, BigInteger y0, BigInteger dx, BigInteger dy, BigInteger gcd) {
        super(x0, y0, dx, dy);
        this.gcd = gcd;
    }

    public BigInteger getGcd() {
        return gcd;
    }
}
