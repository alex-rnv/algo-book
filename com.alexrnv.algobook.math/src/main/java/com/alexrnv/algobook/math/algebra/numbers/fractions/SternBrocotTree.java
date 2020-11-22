package com.alexrnv.algobook.math.algebra.numbers.fractions;

import com.alexrnv.algobook.math.algebra.numbers.continuedfractions.Rationals;

import java.math.BigInteger;
import java.util.List;
import java.util.Stack;
import java.util.function.Function;

/**
 * https://en.wikipedia.org/wiki/Stern%E2%80%93Brocot_tree
 */
public class SternBrocotTree {

    /**
     * Computes parent node in the Stern-Brocot tree using continued fraction representation properties.
     */
    public static SimpleFraction parentOf(SimpleFraction fraction) {
        long[] continuedFraction = Rationals.toContinuedFraction(fraction);
        assert continuedFraction != null;
        continuedFraction[continuedFraction.length-1]--;
        return Rationals.fromContinuedFraction(continuedFraction);
    }

    /**
     * Computes two child nodes in the Stern-Brocot tree using continued fraction representation properties.
     */
    public static SimpleFraction[] childrenOf(SimpleFraction fraction) {
        long[] continuedFraction = Rationals.toContinuedFraction(fraction);
        assert continuedFraction != null;
        continuedFraction[continuedFraction.length-1]++;
        SimpleFraction right = Rationals.fromContinuedFraction(continuedFraction);
        continuedFraction[continuedFraction.length-1] -= 2;
        long[] leftFraction = new long[continuedFraction.length+1];
        System.arraycopy(continuedFraction, 0, leftFraction, 0, continuedFraction.length);
        leftFraction[leftFraction.length-1] = 2;
        return new SimpleFraction[]{ Rationals.fromContinuedFraction(leftFraction), right};
    }

    /**
     * Stern-Brocot tree traversal in the given range.
     * Implementation detail:
     * Assuming the boundaries are [a/b; c/d).
     * If both boundaries are the neighbours in any Farey Sequence, algorithm works by
     * recursively checking the median (a+c)/(b+d), etc., until the limit is reached.
     * Otherwise the range [start, end) is divided into the Farey Sequence F(max(b,d)) and similar algorithm is performed
     * between every subsequent pair.
     * @param limit maximum denominator in the fraction
     * @param start inclusive left bound
     * @param end exclusive right bound
     * @param callback - client defined callback, executed with each proper fraction traversed top-to-bottom.
     */
    public static void traverseInRange(long limit, SimpleFraction start, SimpleFraction end, Function<SimpleFraction, Object> callback) {
        BigInteger lim = BigInteger.valueOf(limit);
        if (FareySequence.areNeighbors(start, end)) {
            traverseFareySequenceNeighboursInCycle(lim, start, end, callback);
        } else {
            List<SimpleFraction> fareySequence = FareySequence.produce(start.getDenominator().max(end.getDenominator()), start, end);
            fareySequence.add(end);
            for (int i=1; i<fareySequence.size(); i++) {
                if (i != (fareySequence.size()-1))
                    callback.apply(fareySequence.get(i));

                traverseFareySequenceNeighboursInCycle(lim, fareySequence.get(i-1), fareySequence.get(i), callback);
            }
        }
    }

    /**
     * Stack-based implementation of Stern-Brocot tree traversal.
     * @param limit maximum denominator in the fraction
     * @param start inclusive left bound
     * @param end exclusive right bound
     * @param callback - client defined callback, executed with each proper fraction traversed top-to-bottom.
     */
    protected static void traverseFareySequenceNeighboursInCycle(BigInteger limit, SimpleFraction start, SimpleFraction end,
                                                                 Function<SimpleFraction, Object> callback) {

        Stack<SimpleFraction> stack = new Stack<>();
        SimpleFraction left = start;
        SimpleFraction right = end;

        while (true) {
            SimpleFraction median = getMedian(left, right);
            if (isLimitReached(limit, median)) {
                if (stack.empty())
                    break;
                left = right;
                right = stack.pop();
            } else {
                callback.apply(median);
                stack.push(right);
                right = median;
            }
        }
    }

    /**
     * Recursive implementation of Stern-Brocot tree traversal.
     * Left here for reference, comparison and tests.
     * Subject to StackOverflow.
     * Use {@link SternBrocotTree#traverseFareySequenceNeighboursInCycle}
     * @param limit maximum denominator in the fraction
     * @param start inclusive left bound
     * @param end exclusive right bound
     * @param callback - client defined callback, executed with each proper fraction traversed top-to-bottom.
     */
    @Deprecated
    protected static void traverseFareySequenceNeighboursRecursive(BigInteger limit, SimpleFraction start, SimpleFraction end, Function<SimpleFraction, Object> callback) {
        SimpleFraction median = getMedian(start, end);
        if (isLimitReached(limit, median)) return;

        callback.apply(median);

        traverseFareySequenceNeighboursRecursive(limit, start, median, callback);
        traverseFareySequenceNeighboursRecursive(limit, median, end, callback);
    }

    private static SimpleFraction getMedian(SimpleFraction start, SimpleFraction end) {
        return SimpleFraction.create(start.getNumerator().add(end.getNumerator()), start.getDenominator().add(end.getDenominator()));
    }

    private static boolean isLimitReached(BigInteger limit, SimpleFraction fraction) {
        return fraction.getDenominator().compareTo(limit) > 0;
    }

}
