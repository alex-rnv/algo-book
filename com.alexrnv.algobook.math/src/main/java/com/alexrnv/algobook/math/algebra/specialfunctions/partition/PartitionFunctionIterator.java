package com.alexrnv.algobook.math.algebra.specialfunctions.partition;

import com.alexrnv.algobook.math.algebra.numbers.poly.penta.Pentagonal;
import com.alexrnv.algobook.math.algebra.numbers.poly.penta.PentagonalCalculator;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Generates subsequent values of partition function (https://en.wikipedia.org/wiki/Partition_function_(number_theory)),
 * using dynamic programming approach and Pentagonal number theorem (https://en.wikipedia.org/wiki/Pentagonal_number_theorem).
 *
 */
public class PartitionFunctionIterator implements Iterator<BigInteger> {

    private final List<BigInteger> Pn = new ArrayList<>();
    private final PentagonalCalculator pentagonalCalculator = Pentagonal.newCalculator(-64, 64);

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public BigInteger next() {
        if (Pn.size() < 2) {
            Pn.add(BigInteger.ONE);
            return BigInteger.ONE;
        }
        return calculateNext();
    }

    private BigInteger calculateNext() {
        int n = Pn.size();
        int minK = getMinK(n);
        int maxK = getMaxK(n);
        BigInteger value = BigInteger.ZERO;
        for (int k = minK-1; k < maxK+1; k++) {
            if (k == 0) continue;
            int p = (int) pentagonalCalculator.calculate(k);
            if (p > n) continue;
            BigInteger signed = signed(k, Pn.get(n-p));
            value = value.add(signed);
        }
        Pn.add(value);
        return value;
    }

    private static int getMaxK(int n) {
        return (int) Math.ceil(1. + Math.sqrt(24 * n + 1) / 6.);
    }

    private static int getMinK(int n) {
        return (int) Math.floor(1. - Math.sqrt(24 * n + 1) / 6.);
    }

    private static BigInteger signed(int k, BigInteger component) {
        return isPositive(k+1) ? component : component.negate();
    }

    private static boolean isPositive(int k) {
        return (k&1) == 0;
    }

}
