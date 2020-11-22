package com.alexrnv.algobook.math.numericalmethods;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

import static com.alexrnv.algobook.math.numericalmethods.Newton.nearestIntegerRootBelow;
import static java.math.BigInteger.valueOf;

public class NewtonTest {

    @Test
    public void testNearestSquareBelowBigInteger() {
        Assert.assertEquals(BigInteger.ONE, nearestIntegerRootBelow(BigInteger.ONE));
        Assert.assertEquals(BigInteger.ONE, nearestIntegerRootBelow(BigInteger.TWO));
        Assert.assertEquals(BigInteger.ONE, nearestIntegerRootBelow(valueOf(3L)));
        Assert.assertEquals(BigInteger.TWO, nearestIntegerRootBelow(valueOf(4L)));

        Assert.assertEquals(valueOf(Integer.MAX_VALUE), nearestIntegerRootBelow(valueOf(Integer.MAX_VALUE).pow(2).add(BigInteger.TEN)));
        Assert.assertEquals(valueOf(Integer.MAX_VALUE-1), nearestIntegerRootBelow(valueOf(Integer.MAX_VALUE).pow(2).subtract(BigInteger.TEN)));
    }


    @Test
    public void testNearestSquareBelowLong() {
        Assert.assertEquals(1L, nearestIntegerRootBelow(1L));
        Assert.assertEquals(1L, nearestIntegerRootBelow(2L));
        Assert.assertEquals(1L, nearestIntegerRootBelow(3L));
        Assert.assertEquals(2L, nearestIntegerRootBelow(4L));

        Assert.assertEquals(Integer.MAX_VALUE, nearestIntegerRootBelow((long)Integer.MAX_VALUE * (long)Integer.MAX_VALUE + 10));
        Assert.assertEquals(Integer.MAX_VALUE-1, nearestIntegerRootBelow((long)Integer.MAX_VALUE * (long)Integer.MAX_VALUE - 10));
    }
}