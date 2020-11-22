package com.alexrnv.algobook.math.algebra.numbers.continuedfractions;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * https://en.wikipedia.org/wiki/Periodic_continued_fraction.
 * Periodic continued fraction, is defined as a sequence of partial denominators,
 * and consists of initial non-repeating block and repeating block.
 */
public class PeriodicContinuedFraction {

    private final long[] initialPart;
    private final long[] repeatingPart;

    protected PeriodicContinuedFraction(long[] initialPart, long[] repeatingPart) {
        this.initialPart = initialPart;
        this.repeatingPart = repeatingPart;
    }

    protected PeriodicContinuedFraction(List<Long> initialPart, List<Long> repeatingPart) {
        this.initialPart = initialPart.stream().mapToLong(i->i).toArray();
        this.repeatingPart = repeatingPart.stream().mapToLong(i->i).toArray();
    }

    public long[] getInitialPart() {
        return Arrays.copyOf(initialPart, initialPart.length);
    }

    public long[] getRepeatingPart() {
        return Arrays.copyOf(repeatingPart, repeatingPart.length);
    }

    public GeneralizedContinuedFraction toGeneralizedForm() {
        return new GeneralizedContinuedFraction(n -> BigInteger.ONE, new BFunction(initialPart, repeatingPart));
    }

    @Override
    public String toString() {
        return "[" + arrayToStringWithoutBrackets(initialPart) + ";(" + arrayToStringWithoutBrackets(repeatingPart) + ")]";
    }

    private String arrayToStringWithoutBrackets(long[] array) {
        return Arrays.toString(array).replaceAll("[\\[\\] ]", "");
    }

    private static class BFunction implements Function<BigInteger, BigInteger> {
        private final long[] initialPart;
        private final long[] repeatingPart;
        private int cursor = 0;

        private BFunction(long[] initialPart, long[] repeatingPart) {
            this.initialPart = initialPart;
            this.repeatingPart = repeatingPart;
        }

        @Override
        public BigInteger apply(BigInteger bigInteger) {
            if (cursor < initialPart.length) {
                return BigInteger.valueOf(initialPart[cursor++]);
            } else {
                int i = cursor - initialPart.length;
                cursor++;
                if (cursor == (initialPart.length + repeatingPart.length))
                    cursor = initialPart.length;
                return BigInteger.valueOf(repeatingPart[i]);
            }
        }
    }
}
