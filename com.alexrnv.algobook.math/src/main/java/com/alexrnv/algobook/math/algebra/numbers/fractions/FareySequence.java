package com.alexrnv.algobook.math.algebra.numbers.fractions;

import com.alexrnv.algobook.math.algebra.fundamentals.linearequations.Diophantine;
import com.alexrnv.algobook.math.algebra.fundamentals.linearequations.SolutionSpace;
import com.alexrnv.algobook.util.Pair;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * https://en.wikipedia.org/wiki/Farey_sequence
 */
public class FareySequence {

    /**
     * Checks if two fractions are neighbours in any Farey sequence.
     * Neighbours a/b and c/d will satisfy the property: cb - ad = 1.
     */
    public static boolean areNeighbors(SimpleFraction a, SimpleFraction b) {
        return a.getDenominator().multiply(b.getNumerator()).subtract(a.getNumerator().multiply(b.getDenominator())).equals(BigInteger.ONE);
    }

    /**
     * @param N - quotient
     * @param start inclusive start fraction
     * @param end exclusive end fraction
     * @return Farey sequence - sorted list of proper fractions in the range [start; end)
     */
    public static List<SimpleFraction> produce(long N, SimpleFraction start, SimpleFraction end) {
        return produce(BigInteger.valueOf(N), start, end);
    }

    /**
     * @param N - quotient
     * @return Farey sequence - sorted list of proper fractions in the range [0; 1)
     */
    public static List<SimpleFraction> produce(long N) {
        return produce(BigInteger.valueOf(N), SimpleFraction.create(0, 1), SimpleFraction.create(1, 1));
    }

    /**
     * @param N - quotient
     * @return Farey sequence - sorted list of proper fractions in the range [0; 1)
     */
    public static List<SimpleFraction> produce(BigInteger N) {
        return produce(N, SimpleFraction.create(0, 1), SimpleFraction.create(1, 1));
    }

    /**
     * @param N - quotient
     * @param start inclusive start fraction
     * @param end exclusive end fraction
     * @return Farey sequence - sorted list of proper fractions in the range [start; end)
     */
    public static List<SimpleFraction> produce(BigInteger N, SimpleFraction start, SimpleFraction end) {
        List<SimpleFraction> fractions = new ArrayList<>();
        Iterator<SimpleFraction> iterator = iterator(N, start, end);
        while (iterator.hasNext()) {
            fractions.add(iterator.next());
        }
        return fractions;
    }

    /**
     * Recursive calculation iterator for Farey sequence. Useful when the sequence needs to be built iteratively until
     * some condition is satisfied.
     * @param N - quotient
     * @param start inclusive start fraction
     * @param end exclusive end fraction
     * @return sorted proper fraction iterator
     */
    public static Iterator<SimpleFraction> iterator(long N, SimpleFraction start, SimpleFraction end) {
        return new FareyIterator(BigInteger.valueOf(N), start.getNumerator(), start.getDenominator(), end.getNumerator(), end.getDenominator());
    }

    /**
     * Recursive calculation iterator for Farey sequence. Useful when the sequence needs to be built iteratively until
     * some condition is satisfied.
     * @param N - quotient
     * @param start inclusive start fraction
     * @param end exclusive end fraction
     * @return sorted proper fraction iterator
     */
    public static Iterator<SimpleFraction> iterator(BigInteger N, SimpleFraction start, SimpleFraction end) {
        return new FareyIterator(N, start.getNumerator(), start.getDenominator(), end.getNumerator(), end.getDenominator());
    }


    private static class FareyIterator implements Iterator<SimpleFraction> {

        private BigInteger N, a, b, c, d, e, f, en, fn;

        public FareyIterator(BigInteger N, BigInteger a0, BigInteger b0, BigInteger en, BigInteger fn) {
            this.N = N;
            this.a = a0;
            this.b = b0;
            this.en = en;
            this.fn = fn;
            initCD();
        }

        private void initCD() {
            SolutionSpace space = Diophantine.solve(b, a.negate(), BigInteger.ONE);
            BigInteger c = BigInteger.ZERO;
            BigInteger d = BigInteger.ZERO;
            long k = 0;
            assert space != null;
            while(c.equals(BigInteger.ZERO) || d.equals(BigInteger.ZERO) ) {
                Pair<BigInteger> solution = space.getKthSolution(k);
                c = solution.left;
                d = solution.right;
                k++;
            }

            this.c = c.add(N.subtract(d).divide(b).multiply(a));
            this.d = d.add(N.subtract(d).divide(b).multiply(b));
        }

        @Override
        public boolean hasNext() {
            return c.multiply(fn).compareTo(d.multiply(en)) <= 0;
        }

        @Override
        public SimpleFraction next() {
            SimpleFraction fraction = SimpleFraction.create(a, b);
            BigInteger k = N.add(b).divide(d);
            e = k.multiply(c).subtract(a);
            f = k.multiply(d).subtract(b);
            a = c;
            b = d;
            c = e;
            d = f;
            return fraction;
        }
    }
}
