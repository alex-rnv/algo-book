package com.alexrnv.algobook.math.algebra.numbers.poly.penta;

import org.junit.Test;

import java.math.BigInteger;
import java.util.stream.IntStream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PentagonalCheckerTest {

    @Test
    public void testTablePrecalculated() {
        PentagonalChecker checker = new PentagonalChecker(1, 11);
        IntStream.of(1, 5, 12, 22, 35, 51, 70, 92, 117, 145)
                .forEach(n -> assertTrue(checker.isInPentagonalTable(n)));
    }

    @Test
    public void testTableNotPrecalculated() {
        PentagonalChecker checker = new PentagonalChecker(0, 0);
        IntStream.of(1, 5, 12, 22, 35, 51, 70, 92, 117, 145)
                .forEach(n -> assertFalse(checker.isInPentagonalTable(n)));
    }

    @Test
    public void testOutsideOfTable() {
        PentagonalChecker checker = new PentagonalChecker(0, 3);
        IntStream.of(12, 22, 35, 51, 70, 92, 117, 145)
                .forEach(n -> assertFalse(checker.isInPentagonalTable(n)));
    }

    @Test
    public void testFormulaSimple() {
        PentagonalChecker checker = new PentagonalChecker(0, 1);
        IntStream.of(1, 5, 12, 22, 35, 51, 70, 92, 117, 145)
                .forEach(n -> assertTrue(checker.isPentagonalByFormula(BigInteger.valueOf(n))));
    }

}