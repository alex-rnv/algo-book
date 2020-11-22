package com.alexrnv.algobook.math.algebra.numbers.continuedfractions;

import com.alexrnv.algobook.math.algebra.numbers.fractions.SimpleFraction;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Generates convergent series using fundamental recurrence formulas:
 * https://en.wikipedia.org/wiki/Generalized_continued_fraction#cite_note-num_theory-16
 */
public class ConvergentSeries {

    /**
     * @param fraction - generalized fraction to calculate series for.
     * @param limit - maximum number of infinite series to calculate.
     * @return an ordered list of approximations
     */
    public static List<SimpleFraction> generateConvergentSeries(GeneralizedContinuedFraction fraction, int limit) {
        assert limit > 0;

        List<SimpleFraction> series = new ArrayList<>(limit);
        ConvergentSeriesIterator iterator = new ConvergentSeriesIterator(fraction);
        int n = 0;
        while (iterator.hasNext() && n++ <= limit) {
            SimpleFraction simpleFraction = iterator.next();
            if (!simpleFraction.getDenominator().equals(BigInteger.ZERO)) {
                series.add(simpleFraction);
            }
        }

        return series;
    }

    /**
     * Infinite convergent simple fractions iterator.
     * @param fraction - generalized continued fraction
     */
    public static Iterator<SimpleFraction> convergentSeriesIterator(GeneralizedContinuedFraction fraction) {
        return new ConvergentSeriesIterator(fraction);
    }

    private static class ConvergentSeriesIterator implements Iterator<SimpleFraction> {

        private final GeneralizedContinuedFraction fraction;

        private long n;
        private BigInteger A_prev_prev;
        private BigInteger B_prev_prev;
        private BigInteger A_prev;
        private BigInteger B_prev;

        private ConvergentSeriesIterator(GeneralizedContinuedFraction fraction) {
            this.fraction = fraction;
            this.n = 1;
            this.A_prev_prev = BigInteger.ONE;
            this.B_prev_prev = BigInteger.ZERO;
            this.A_prev = fraction.bFunction.apply(BigInteger.ZERO);
            this.B_prev = BigInteger.ONE;
        }

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public SimpleFraction next() {
            BigInteger bn = fraction.bFunction.apply(BigInteger.valueOf(n));
            BigInteger an = fraction.aFunction.apply(BigInteger.valueOf(n));
            BigInteger A = bn.multiply(A_prev).add(an.multiply(A_prev_prev));
            BigInteger B = bn.multiply(B_prev).add(an.multiply(B_prev_prev));

            A_prev_prev = A_prev;
            B_prev_prev = B_prev;
            A_prev = A;
            B_prev = B;
            n++;
            return SimpleFraction.create(A, B);
        }
    }
}
