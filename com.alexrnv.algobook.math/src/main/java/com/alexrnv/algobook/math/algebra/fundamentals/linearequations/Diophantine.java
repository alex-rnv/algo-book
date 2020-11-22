package com.alexrnv.algobook.math.algebra.fundamentals.linearequations;

import java.math.BigInteger;

/**
 * Solves Diophantine equations of the type ax+by=c.
 */
public class Diophantine {

    /**
     * Given coefficients of the equation ax+by=c,
     * returns the solution space (all possible pairs of (x,y)), or null if the solution can't be found.
     * @return - SolutionSpace or null if can't be found.
     */
    public static SolutionSpace solve(long a, long b, long c) {
        return solve(BigInteger.valueOf(a), BigInteger.valueOf(b), BigInteger.valueOf(c));
    }

    /**
     * Given coefficients of the equation ax+by=c,
     * returns the solution space (all possible pairs of (x,y)), or null if the solution can't be found.
     * @return - SolutionSpace or null if can't be found.
     */
    public static SolutionSpace solve(BigInteger a, BigInteger b, BigInteger c) {
        EuclideanSolutionSpace euclideanSolution = ExtendedEuclidean.solve(a, b);
        BigInteger gcd = euclideanSolution.getGcd();

        if (c.equals(BigInteger.ZERO) && gcd.equals(BigInteger.ZERO))
            return new SolutionSpace(0,0,0,0);

        if (!c.equals(BigInteger.ZERO) && (gcd.equals(BigInteger.ZERO) || !c.abs().mod(gcd.abs()).equals(BigInteger.ZERO)))
            return null;

        BigInteger x = euclideanSolution.x0.multiply(c).divide(gcd);
        BigInteger y = euclideanSolution.y0.multiply(c).divide(gcd);
        BigInteger dx = b.divide(gcd);
        BigInteger dy = a.negate().divide(gcd);
        return new SolutionSpace(x, y, dx, dy);
    }
}
