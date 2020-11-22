package com.alexrnv.algobook.math.algebra.numbers.continuedfractions;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QuadraticIrrationalTest {

    @Test
    public void testSimplify() {
        QuadraticIrrational q = new QuadraticIrrational(1,1,2,1);
        assertEquals(1, q.a);
        assertEquals(1, q.b);
        assertEquals(2, q.c);
        assertEquals(1, q.d);

        q = new QuadraticIrrational(2,2,2,2);
        assertEquals(1, q.a);
        assertEquals(1, q.b);
        assertEquals(2, q.c);
        assertEquals(1, q.d);

        q = new QuadraticIrrational(3,6,3,9);
        assertEquals(1, q.a);
        assertEquals(2, q.b);
        assertEquals(3, q.c);
        assertEquals(3, q.d);

        q = new QuadraticIrrational(0,1,5,1);
        assertEquals(0, q.a);
        assertEquals(1, q.b);
        assertEquals(5, q.c);
        assertEquals(1, q.d);

        q = new QuadraticIrrational(0,10,5,10);
        assertEquals(0, q.a);
        assertEquals(1, q.b);
        assertEquals(5, q.c);
        assertEquals(1, q.d);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroBThrowsException() {
        QuadraticIrrational q = new QuadraticIrrational(1,0,2,1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroDThrowsException() {
        QuadraticIrrational q = new QuadraticIrrational(1,3,2,0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSquareCThrowsException() {
        QuadraticIrrational q = new QuadraticIrrational(1,3,4,4);
    }

    @Test
    public void testEqualsAndHashCode() {
        QuadraticIrrational q1 = new QuadraticIrrational(1, 3, 7, 4);
        QuadraticIrrational q2 = new QuadraticIrrational(1, 3, 7, 4);
        assertEquals(q1, q2);
        assertEquals(q1.hashCode(), q2.hashCode());


        QuadraticIrrational q3 = new QuadraticIrrational(2, 4, 11, 4);
        QuadraticIrrational q4 = new QuadraticIrrational(1, 2, 11, 2);
        assertEquals(q3, q4);
        assertEquals(q3.hashCode(), q4.hashCode());
    }

}