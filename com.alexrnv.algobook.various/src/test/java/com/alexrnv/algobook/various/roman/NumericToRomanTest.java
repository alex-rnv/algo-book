package com.alexrnv.algobook.various.roman;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NumericToRomanTest {

    @Test
    public void checkCorrectness() {
        assertEquals("", new NumericToRoman().convert(0));
        assertEquals("X", new NumericToRoman().convert(10));
        assertEquals("CXI", new NumericToRoman().convert(111));
        assertEquals("CMLXXXVII", new NumericToRoman().convert(987));
        assertEquals("DXIX", new NumericToRoman().convert(519));
    }
}