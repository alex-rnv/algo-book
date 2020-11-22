package com.alexrnv.algobook.cs.strings;

import org.junit.Test;

import java.util.Arrays;

import static com.alexrnv.algobook.cs.strings.KnuthMorrisPratt.prefixFunction;
import static org.junit.Assert.assertTrue;

public class KnuthMorrisPrattTest {

    @Test
    public void testPrefixFunction() {
        assertTrue(Arrays.equals(new int[]{0,0,0,1,2,3,0}, prefixFunction("abcabcd")));
        assertTrue(Arrays.equals(new int[]{0,1,0,1,2,2,3}, prefixFunction("aabaaab")));
    }

}