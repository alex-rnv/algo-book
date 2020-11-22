package com.alexrnv.algobook.math.algebra.numbers.continuedfractions;

import com.alexrnv.algobook.math.algebra.fundamentals.Arithmetic;
import com.alexrnv.algobook.math.numericalmethods.Newton;
import com.alexrnv.algobook.math.algebra.fundamentals.factorization.Divisors;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * https://en.wikipedia.org/wiki/Quadratic_irrational_number
 */
public class QuadraticIrrationals {

    /**
     *  The best and the most straightforward way to define Quadratic Irrationals is to define them in terms of quadratic equation coefficients:
     *  ax^2+bx+c=0, where a,b,c are also integers.
     *  The implementation takes care of extracting square part from the root.
     */
    public static QuadraticIrrational fromQuadraticEquationCoefficients(long a0, long b0, long c0) {
        long a = -b0;
        long d = 2*a0;
        long cb2 = b0*b0 - 4*a0*c0;
        SquareExtractor se = new SquareExtractor(cb2);
        return new QuadraticIrrational(a, se.b, se.c, d);
    }

    public static QuadraticIrrational squareRootApproximation(long n) {
        return new QuadraticIrrational(0, 1, n, 1);
    }

    /**
     * Represents given Quadratic Irrational in the Periodic Continued Fraction form.
     * E.g.:
     * √2      ===>  [1;(2)]
     * 3√1111  ===>  [99;(1,198)]
     */
    public static PeriodicContinuedFraction toContinuedFraction(QuadraticIrrational q) {
        LinkedHashSet<CanonicalTriple> triples = new LinkedHashSet<>();
        long S = q.b*q.b*q.c;
        long A0 = Newton.nearestIntegerRootBelow(S);
        if (A0 == S) A0--;
        long Mn = q.a;
        long Dn = q.d;
        long An = A0;

        CanonicalTriple triple;
        for(;;) {
            triple = new CanonicalTriple(Mn, Dn, An);
            if (triples.contains(triple)) {
                break;
            } else {
                triples.add(triple);
                Mn = Dn * An - Mn;
                Dn = (S - Mn*Mn) / Dn;
                An = (A0 + Mn) / Dn;
            }
        }
        List<Long> initial = new ArrayList<>();
        List<Long> repeatable = new ArrayList<>();
        boolean init = true;
        for (CanonicalTriple t : triples) {
            if (t.equals(triple)) {
                init = false;
            }
            if (init) {
                initial.add(t.a);
            } else {
                repeatable.add(t.a);
            }
        }
        return new PeriodicContinuedFraction(initial, repeatable);
    }

    /**
     * Represents sqrt(S) in the form b*sqrt(c), where c - is the smallest possible square free factor.
     * E.g.:
     * sqrt(8) ==> 2*sqrt(2); b=2, c=2;
     * sqrt(45) ==> 3*sqrt(5); b=3, c=5;
     *
     */
    private static class SquareExtractor {
        private long b;
        private long c;

        private SquareExtractor(long cb2) {
            List<Long> factors = Divisors.factorsAll(cb2);
            Map<Long, Long> grouped = factors.stream()
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            b = 1;
            c = 1;
            for (Long factor : grouped.keySet()) {
                long count = grouped.get(factor);
                if (count >= 2) {
                    long p = count >> 1;
                    b *= Arithmetic.binpow(factor, p);
                    if ((count&1) != 0)
                        c *= factor;
                } else {
                    c *= factor;
                }
            }
        }
    }

    private static class CanonicalTriple {
        protected final long m;
        protected final long d;
        protected final long a;

        CanonicalTriple(long m, long d, long a) {
            this.m = m;
            this.d = d;
            this.a = a;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CanonicalTriple that = (CanonicalTriple) o;
            return m == that.m &&
                    d == that.d &&
                    a == that.a;
        }

        @Override
        public int hashCode() {
            return Objects.hash(m, d, a);
        }
    }
}
