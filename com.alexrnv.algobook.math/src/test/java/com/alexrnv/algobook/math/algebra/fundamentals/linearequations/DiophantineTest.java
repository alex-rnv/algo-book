package com.alexrnv.algobook.math.algebra.fundamentals.linearequations;

import com.alexrnv.algobook.util.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

public class DiophantineTest {

    @Test
    public void testSolutionExists() {
        assertExistingSolution(55, 80, 15);
        assertExistingSolution(-55, 80, 25);

        assertExistingSolution(1, 0, 1);
        assertExistingSolution(0, 1, 1);
        assertExistingSolution(0, 0, 0);

        assertExistingSolution(1, 1, 4);
        assertExistingSolution(2, 2, 6);
        assertExistingSolution(3, 3, 12);

        assertExistingSolution(3, 2, 5);
        assertExistingSolution(6, 4, 8);
        assertExistingSolution(17, 3, 21);
        assertExistingSolution(3, -1, 1);
        assertExistingSolution(-3, 1, -4);

        assertExistingSolution(6, -2, 10);

        assertExistingSolution(1, 3, 2);
        assertExistingSolution(1, 7, 9);
    }

    @Test
    public void testSolutionNotExist() {
        assertNoSolution(55, 80, 12);
        assertNoSolution(-55, 80, 6);

        assertNoSolution(0, 0, 1);

        assertNoSolution(2, 2, 3);
        assertNoSolution(3, 3, 14);

        assertNoSolution(6, 4, 3);

        assertNoSolution(6, -2, 11);
    }

    private void assertNoSolution(long a, long b, long c) {
       Assert.assertNull(Diophantine.solve(a, b, c));
    }

    private void assertExistingSolution(long a, long b, long c) {
        assertExistingSolution(a, b, c, 10);
    }

    private void assertExistingSolution(long a0, long b0, long c0, int numIterations) {
        BigInteger a = BigInteger.valueOf(a0);
        BigInteger b = BigInteger.valueOf(b0);
        BigInteger c = BigInteger.valueOf(c0);
        SolutionSpace space = Diophantine.solve(a, b, c);
        assert space != null;
        for (int k=-numIterations/2; k<numIterations/2; k++) {
            Pair<BigInteger> solution = space.getKthSolution(k);
            //System.out.printf("asserting %d*%d + %d*%d = %d\n", a, solution.left, b, solution.right, c);
            Assert.assertEquals(a.multiply(solution.left).add(b.multiply(solution.right)), c);
        }
    }

}