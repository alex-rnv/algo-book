package com.alexrnv.algobook.math.algebra.numbers.fractions;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class FareySequenceTest {

    @Test
    public void testSmallNFullRange() {
        List<SimpleFraction> list = FareySequence.produce(8, SimpleFraction.create(0, 1), SimpleFraction.create(1, 1));
        Assert.assertEquals("[( 0 / 1 ), ( 1 / 8 ), ( 1 / 7 ), ( 1 / 6 ), ( 1 / 5 ), ( 1 / 4 ), ( 2 / 7 ), ( 1 / 3 ), ( 3 / 8 ), ( 2 / 5 ), ( 3 / 7 ), ( 1 / 2 ), ( 4 / 7 ), ( 3 / 5 ), ( 5 / 8 ), ( 2 / 3 ), ( 5 / 7 ), ( 3 / 4 ), ( 4 / 5 ), ( 5 / 6 ), ( 6 / 7 ), ( 7 / 8 )]", list.toString());
    }

    @Test
    public void testSmallNLimitedRange() {
        List<SimpleFraction> list = FareySequence.produce(8, SimpleFraction.create(1, 3), SimpleFraction.create(1, 2));
        Assert.assertEquals("[( 1 / 3 ), ( 3 / 8 ), ( 2 / 5 ), ( 3 / 7 )]", list.toString());
    }

    @Test
    public void testBigNCount() {
        Iterator<SimpleFraction> iterator = FareySequence.iterator(12_000, SimpleFraction.create(1, 3), SimpleFraction.create(1, 2));
        long num = 0;
        while (iterator.hasNext()) {
            num++;
            iterator.next();
        }
        Assert.assertEquals(7295373, num);
    }

}