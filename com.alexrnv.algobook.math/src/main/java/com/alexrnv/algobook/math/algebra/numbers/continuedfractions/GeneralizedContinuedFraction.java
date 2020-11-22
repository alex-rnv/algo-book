package com.alexrnv.algobook.math.algebra.numbers.continuedfractions;

import java.math.BigInteger;
import java.util.function.Function;

/**
 * https://en.wikipedia.org/wiki/Generalized_continued_fraction#cite_note-num_theory-16
 * This particular representation defines two series of arguments - a and b, which are consecutive parameters in
 * the recursive formula.
 * Functions a and b must be defined for all natural numbers n.
 * a(n) and b(n) are corresponding n-th elements in the recursive formula.
 */
public class GeneralizedContinuedFraction {

    protected final Function<BigInteger, BigInteger> aFunction;
    protected final Function<BigInteger, BigInteger> bFunction;

    public GeneralizedContinuedFraction(Function<BigInteger, BigInteger> aFunction, Function<BigInteger, BigInteger> bFunction) {
        this.aFunction = aFunction;
        this.bFunction = bFunction;
    }
}
