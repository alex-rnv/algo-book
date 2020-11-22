package com.alexrnv.algobook.math.combinatorics;

import com.alexrnv.algobook.math.algebra.fundamentals.Digits;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;
import java.util.stream.Collectors;

public class PermutationsTest {

    @Test
    public void testGenerateAllPermutations() {
        Set<Long> permutations = Permutations.generateAllPermutations(new byte[]{4,1,0,6,3,6,2,5})
                .stream()
                .map(Digits::fromDigitArray)
                .collect(Collectors.toSet());
        Assert.assertTrue(permutations.contains(66430125l));
        Assert.assertTrue(permutations.contains(56623104l));

    }

}