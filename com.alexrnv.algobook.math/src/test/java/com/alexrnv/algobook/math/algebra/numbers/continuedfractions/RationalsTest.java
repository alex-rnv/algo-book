package com.alexrnv.algobook.math.algebra.numbers.continuedfractions;

import com.alexrnv.algobook.math.algebra.numbers.fractions.SimpleFraction;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class RationalsTest {

    @Test
    public void testToContinuedFraction() {
        assertArrayEquals(new long[]{4,2,6,7}, Rationals.toContinuedFraction(SimpleFraction.create(415, 93)));
        assertArrayEquals(new long[]{0,6,5}, Rationals.toContinuedFraction(SimpleFraction.create(15, 93)));

        assertArrayEquals(new long[]{1,2,4}, Rationals.toContinuedFraction(SimpleFraction.create(13, 9)));
    }

    @Test
    public void testFromContinuedFraction() {
        assertEquals(SimpleFraction.create(415, 93), Rationals.fromContinuedFraction(new long[]{4,2,6,7}));
        assertEquals(SimpleFraction.create(15, 93), Rationals.fromContinuedFraction(new long[]{0,6,5}));

        assertEquals(SimpleFraction.create(13, 9), Rationals.fromContinuedFraction(new long[]{1,2,3,1}));
        assertEquals(SimpleFraction.create(13, 9), Rationals.fromContinuedFraction(new long[]{1,2,4}));
    }

}