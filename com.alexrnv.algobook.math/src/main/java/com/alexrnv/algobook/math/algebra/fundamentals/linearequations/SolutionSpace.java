package com.alexrnv.algobook.math.algebra.fundamentals.linearequations;

import com.alexrnv.algobook.util.Pair;

import java.math.BigInteger;

/**
 * Describes the set of solutions of a linear Diophantine equation of two variables x and y in a form <br/>
 * xk = x0 + k*dx  <br/>
 * yk = y0 + k*dy  <br/>
 *
 * Base solution (x0,y0) is obtained by calling {@link SolutionSpace#getKthSolution} with parameter 0.
 */
public class SolutionSpace {

    protected final BigInteger x0;
    protected final BigInteger y0;
    protected final BigInteger dx;
    protected final BigInteger dy;

    public SolutionSpace(long x0, long y0, long dx, long dy) {
        this(BigInteger.valueOf(x0), BigInteger.valueOf(y0), BigInteger.valueOf(dx), BigInteger.valueOf(dy));
    }

    public SolutionSpace(BigInteger x0, BigInteger y0, BigInteger dx, BigInteger dy) {
        this.x0 = x0;
        this.y0 = y0;
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * @param k - any natural number, 0 will produce base solution
     * @return solution for a given k
     */
    public Pair<BigInteger> getKthSolution(long k) {
        BigInteger bk = BigInteger.valueOf(k);
        return new Pair<>(x0.add(dx.multiply(bk)), y0.add(dy.multiply(bk)));
    }
}
