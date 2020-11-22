package com.alexrnv.algobook.math.algebra.specialfunctions.partition;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class PartitionFunctionIteratorTest {

    @Test
    public void verifyNumbers() {
        PartitionFunctionIterator iterator = new PartitionFunctionIterator();
        assertEquals(BigInteger.ONE, iterator.next()); //p(0)
        assertEquals(BigInteger.ONE, iterator.next()); //p(1)
        assertEquals(BigInteger.TWO, iterator.next());
        assertEquals(BigInteger.valueOf(3), iterator.next());
        assertEquals(BigInteger.valueOf(5), iterator.next());
        assertEquals(BigInteger.valueOf(7), iterator.next());
        assertEquals(BigInteger.valueOf(11), iterator.next());
        assertEquals(BigInteger.valueOf(15), iterator.next());
        assertEquals(BigInteger.valueOf(22), iterator.next());
        assertEquals(BigInteger.valueOf(30), iterator.next());
        assertEquals(BigInteger.valueOf(42), iterator.next());
        assertEquals(BigInteger.valueOf(56), iterator.next());
        assertEquals(BigInteger.valueOf(77), iterator.next());
        assertEquals(BigInteger.valueOf(101), iterator.next()); //p(13)

        for (int k=0; k<86; k++) iterator.next();
        assertEquals(BigInteger.valueOf(190569292), iterator.next()); //p(100)
        for (int k=0; k<899; k++) iterator.next();
        assertEquals(new BigInteger("24061467864032622473692149727991"), iterator.next()); //p(1000)
        for (int k=0; k<8999; k++) iterator.next();
        assertEquals(new BigInteger("36167251325636293988820471890953695495016030339315650422081868605887952568754066420592310556052906916435144"), iterator.next()); //p(10000)

    }

}