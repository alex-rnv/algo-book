package com.alexrnv.algobook.math.algebra.numbers.poly.penta;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PentagonalCalculatorTest {

    @Test
    public void testEnsurePrecalculatedValues() {
        PentagonalCalculator calculator = new PentagonalCalculator(-6, 7);
        assertEquals(1L, calculator.getPrecalculatedValue(1L));
        assertEquals(5L, calculator.getPrecalculatedValue(2L));
        assertEquals(12L, calculator.getPrecalculatedValue(3L));
        assertEquals(22L, calculator.getPrecalculatedValue(4L));
        assertEquals(35L, calculator.getPrecalculatedValue(5L));
        assertEquals(51L, calculator.getPrecalculatedValue(6L));

        assertEquals(2L, calculator.getPrecalculatedValue(-1L));
        assertEquals(7L, calculator.getPrecalculatedValue(-2L));
        assertEquals(15L, calculator.getPrecalculatedValue(-3L));
        assertEquals(26L, calculator.getPrecalculatedValue(-4L));
        assertEquals(40L, calculator.getPrecalculatedValue(-5L));
        assertEquals(57L, calculator.getPrecalculatedValue(-6L));
    }

    @Test(expected = AssertionError.class)
    public void testNotPrecalculatedValue() {
        PentagonalCalculator calculator = new PentagonalCalculator(1, 10);
        calculator.getPrecalculatedValue(-1L);
    }

}