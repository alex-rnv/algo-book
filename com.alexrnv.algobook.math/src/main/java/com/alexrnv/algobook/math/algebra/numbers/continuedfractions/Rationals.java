package com.alexrnv.algobook.math.algebra.numbers.continuedfractions;

import com.alexrnv.algobook.math.algebra.numbers.fractions.SimpleFraction;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Rationals {

    private static final BigInteger MAX_LONG = BigInteger.valueOf(Long.MAX_VALUE);
    private static final BigInteger MIN_LONG = BigInteger.valueOf(Long.MIN_VALUE);

    public static long[] toContinuedFraction(SimpleFraction fraction) {
        if (fraction.getDenominator().equals(BigInteger.ZERO)) return null;

        List<Long> list = new ArrayList<>();
        BigInteger remainder = null;
        SimpleFraction current = fraction;
        while (!BigInteger.ZERO.equals(remainder)) {
            BigInteger[] quotientAndRemainder = current.getNumerator().divideAndRemainder(current.getDenominator());
            list.add(toLong(quotientAndRemainder[0]));
            remainder = quotientAndRemainder[1];
            current = SimpleFraction.create(current.getDenominator(), remainder);
        }
        return list.stream().mapToLong(i->i).toArray();
    }

    public static SimpleFraction fromContinuedFraction(long[] continuedFraction) {
        if (continuedFraction.length == 0) return null;

        long elem = continuedFraction[continuedFraction.length-1];
        SimpleFraction fraction = SimpleFraction.create(elem, 1);
        for (int k = continuedFraction.length-2; k >= 0; k--) {
            fraction = SimpleFraction.create(continuedFraction[k], 1).add(fraction.reciprocal());
        }
        return fraction;
    }

    private static long toLong(BigInteger v) {
        if (v.compareTo(MAX_LONG) > 0 || v.compareTo(MIN_LONG) < 0)
            throw new IllegalArgumentException("overflow");//time to implement BigInteger version

        return v.longValue();
    }
}
