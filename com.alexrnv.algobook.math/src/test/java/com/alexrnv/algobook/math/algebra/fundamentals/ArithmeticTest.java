package com.alexrnv.algobook.math.algebra.fundamentals;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArithmeticTest {

    @Test
    public void testBinPow() {
        assertEquals(1L, Arithmetic.binpow(1L, 1L));
        assertEquals(4L, Arithmetic.binpow(2L, 2L));
        assertEquals(100000L, Arithmetic.binpow(10L, 5L));
        assertEquals(0L, Arithmetic.binpow(0L, 5L));
        assertEquals(1L, Arithmetic.binpow(14L, 0L));

        assertEquals(-1L, Arithmetic.binpow(-1L, 1L));
        assertEquals(1L, Arithmetic.binpow(-1L, 2L));
        assertEquals(-1L, Arithmetic.binpow(-1L, 3L));
        assertEquals(1L, Arithmetic.binpow(-1L, 100L));
    }

    @Test(expected = AssertionError.class)
    public void testBinPowWithNegativePowerDoesNotWork() {
        assertEquals(-1L, Arithmetic.binpow(1L, -1L));
    }

}