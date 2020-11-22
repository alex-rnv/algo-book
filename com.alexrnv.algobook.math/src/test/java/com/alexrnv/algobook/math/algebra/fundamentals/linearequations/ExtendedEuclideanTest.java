package com.alexrnv.algobook.math.algebra.fundamentals.linearequations;

import com.alexrnv.algobook.util.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

public class ExtendedEuclideanTest {

    @Test
    public void testExtendedEuclidean() {
        assertExtendedEuclideanIterator(55, 80);
        assertExtendedEuclideanIterator(-55, 80);

        assertExtendedEuclideanIterator(1, 0);
        assertExtendedEuclideanIterator(0, 1);
        assertExtendedEuclideanIterator(0, 0);

        assertExtendedEuclideanIterator(1, 1);
        assertExtendedEuclideanIterator(2, 2);
        assertExtendedEuclideanIterator(3, 3);

        assertExtendedEuclideanIterator(3, 2);
        assertExtendedEuclideanIterator(6, 4);
        assertExtendedEuclideanIterator(17, 3);
        assertExtendedEuclideanIterator(3, -1);
        assertExtendedEuclideanIterator(-3, 1);

        assertExtendedEuclideanIterator(6, -2);

        assertExtendedEuclideanIterator(1, 3);
        assertExtendedEuclideanIterator(1, 7);
    }

    private void assertExtendedEuclideanIterator(long a, long b) {
        assertExtendedEuclideanIterator(a, b, 10);
    }

    private void assertExtendedEuclideanIterator(long a0, long b0, int numIterations) {
        BigInteger a = BigInteger.valueOf(a0);
        BigInteger b = BigInteger.valueOf(b0);
        EuclideanSolutionSpace space = ExtendedEuclidean.solve(a, b);
        for (int k=-numIterations/2; k<numIterations/2; k++) {
            Pair<BigInteger> solution = space.getKthSolution(k);
            //System.out.printf("asserting %d*%d + %d*%d = %d\n", a, solution.left, b, solution.right, iterator.getGcd());
            Assert.assertEquals(a.multiply(solution.left).add(b.multiply(solution.right)), space.getGcd());;
        }
    }

}