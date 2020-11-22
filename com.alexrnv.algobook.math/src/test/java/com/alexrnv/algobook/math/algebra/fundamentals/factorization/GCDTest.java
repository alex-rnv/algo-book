package com.alexrnv.algobook.math.algebra.fundamentals.factorization;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class GCDTest {

    @Test
    public void testGCD() {
        assertEquals(1, GCD.gcd(1, 1));
        assertEquals(1, GCD.gcd(1, 2));
        assertEquals(1, GCD.gcd(1, 3));
        assertEquals(2, GCD.gcd(2, 4));
        assertEquals(3, GCD.gcd(3, 6));
        assertEquals(2, GCD.gcd(4, 6));
        assertEquals(3, GCD.gcd(27, 51));
    }

    @Test
    public void testGCD2() {
        assertEquals(1, GCD.gcd2(1, 1));
        assertEquals(1, GCD.gcd2(1, 2));
        assertEquals(1, GCD.gcd2(1, 3));
        assertEquals(2, GCD.gcd2(2, 4));
        assertEquals(3, GCD.gcd2(3, 6));
        assertEquals(2, GCD.gcd2(4, 6));
        assertEquals(3, GCD.gcd2(27, 51));
        assertEquals(3, GCD.gcd2(51, 27));
    }

    @Test
    public void testEqualsToJVMImplementation() {
        int k=0;
        Random random = new Random();
        while(k++ < 100) {
            long a = random.nextLong();
            long b = random.nextLong();
            long gcd1 = GCD.gcdJVM(a, b);
            long gcd2 = GCD.gcd(a, b);

            assertEquals(String.format("a=%d, b=%d", a, b), gcd1, gcd2);
        }
    }

    @Test
    public void testMultiArg() {
        assertEquals(6, GCD.gcd(12,36,66,6666));
        assertEquals(1, GCD.gcd(1,4,4,2));
        assertEquals(2, GCD.gcd(8,4,4,2));
        assertEquals(1, GCD.gcd(7,2,4,6));
    }

    @Test
    public void testArray() {
        assertEquals(6, GCD.gcd(new long[]{12,36,66,6666}));
        assertEquals(1, GCD.gcd(new long[]{1,4,4,2}));
        assertEquals(2, GCD.gcd(new long[]{8,4,4,2}));
        assertEquals(1, GCD.gcd(new long[]{7,2,4,6}));
    }

}