package com.alexrnv.algobook.various.roman;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RomanToNumericTest {

    @Test(expected = InvalidRomanNumeralException.class)
    public void checkInvalidTooLongSubtractiveForm() {
        new RomanToNumeric().convert("MMMMDCXXIIV");
    }

    @Test(expected = InvalidRomanNumeralException.class)
    public void checkInvalidMultipleV() {
        new RomanToNumeric().convert("MMMMDCXXVV");
    }

    @Test(expected = InvalidRomanNumeralException.class)
    public void checkInvalidWrongOrder() {
        new RomanToNumeric().convert("MMMMDCVXX");
    }

    @Test
    public void checkCorrectness() {
        assertEquals(0, new RomanToNumeric().convert(""));
        assertEquals(4626, new RomanToNumeric().convert("MMMMDCXXVI"));
        assertEquals(4419, new RomanToNumeric().convert("MMMMCDXIX"));
        assertEquals(964, new RomanToNumeric().convert("CMLXIV"));
    }
}