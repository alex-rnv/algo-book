package com.alexrnv.algobook.math.algebra.numbers.fractions;

import com.alexrnv.algobook.util.Pair;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class SternBrocotTreeTest {

    @Test
    public void testParentOf() {
        assertEquals(SimpleFraction.create(13, 9), SternBrocotTree.parentOf(SimpleFraction.create(23, 16)));
        assertEquals(SimpleFraction.create(13, 9), SternBrocotTree.parentOf(SimpleFraction.create(16, 11)));
        assertEquals(SimpleFraction.create(9, 13), SternBrocotTree.parentOf(SimpleFraction.create(16, 23)));
        assertEquals(SimpleFraction.create(7741, 232), SternBrocotTree.parentOf(SimpleFraction.create(11111, 333)));
    }

    @Test
    public void testChildrenOf() {
        assertArrayEquals(
                new SimpleFraction[]{
                        SimpleFraction.create(23, 16),
                        SimpleFraction.create(16, 11)
                },
                SternBrocotTree.childrenOf(SimpleFraction.create(13, 9)));
    }

    @Test
    public void testCompareRecursiveAndCycleSmallLimitInSmallRange() {
        Counter counter1 = new Counter();
        Counter counter2 = new Counter();
        SimpleFraction start = SimpleFraction.create(1, 3);
        SimpleFraction end = SimpleFraction.create(1, 2);
        BigInteger limit = BigInteger.valueOf(12);
        SternBrocotTree.traverseFareySequenceNeighboursRecursive(limit, start, end, counter1);
        SternBrocotTree.traverseFareySequenceNeighboursInCycle(limit, start, end, counter2);
        System.out.println(counter1.n.get());
        System.out.println(counter2.n.get());
        assertEquals(counter1.n.get(), counter2.n.get());
    }

    @Test
    public void testCompareRecursiveAndCycleSmallInBigRange() {
        Counter counter1 = new Counter();
        Counter counter2 = new Counter();
        SimpleFraction start = SimpleFraction.create(0, 1);
        SimpleFraction end = SimpleFraction.create(3, 1);
        BigInteger limit = BigInteger.valueOf(12);
        SternBrocotTree.traverseFareySequenceNeighboursRecursive(limit, start, end, counter1);
        SternBrocotTree.traverseFareySequenceNeighboursInCycle(limit, start, end, counter2);
        System.out.println(counter1.n.get());
        System.out.println(counter2.n.get());
        assertEquals(counter1.n.get(), counter2.n.get());
    }

    @Test
    public void testCompareRecursiveAndCycleBigLimitInSmallRange() {
        Counter counter1 = new Counter();
        Counter counter2 = new Counter();
        SimpleFraction start = SimpleFraction.create(1, 3);
        SimpleFraction end = SimpleFraction.create(1, 2);
        BigInteger limit = BigInteger.valueOf(2000);
        SternBrocotTree.traverseFareySequenceNeighboursRecursive(limit, start, end, counter1);
        SternBrocotTree.traverseFareySequenceNeighboursInCycle(limit, start, end, counter2);
        System.out.println(counter1.n.get());
        System.out.println(counter2.n.get());
        assertEquals(counter1.n.get(), counter2.n.get());
    }

    @Test
    public void testCompareRecursiveAndCycleBigLimitInBigRange() {
        Counter counter1 = new Counter();
        Counter counter2 = new Counter();
        SimpleFraction start = SimpleFraction.create(0, 1);
        SimpleFraction end = SimpleFraction.create(3, 2);
        BigInteger limit = BigInteger.valueOf(2000);
        SternBrocotTree.traverseFareySequenceNeighboursRecursive(limit, start, end, counter1);
        SternBrocotTree.traverseFareySequenceNeighboursInCycle(limit, start, end, counter2);
        System.out.println(counter1.n.get());
        System.out.println(counter2.n.get());
        assertEquals(counter1.n.get(), counter2.n.get());
    }

    @Test
    public void testCompareWithFareySequence() {
        List<Pair<SimpleFraction>> pairs = new ArrayList<>() {{
            add(new Pair<>(SimpleFraction.create(1, 3), SimpleFraction.create(1, 2)));
            add(new Pair<>(SimpleFraction.create(0, 1), SimpleFraction.create(1, 1)));
            add(new Pair<>(SimpleFraction.create(1, 4), SimpleFraction.create(1, 2)));
            add(new Pair<>(SimpleFraction.create(1, 5), SimpleFraction.create(1, 2)));
        }};
        long N = 4096;
        BigInteger limit = BigInteger.valueOf(N);
        for (Pair<SimpleFraction> pair : pairs) {
            SimpleFraction start = pair.left;
            SimpleFraction end = pair.right;

            Counter counter = new Counter();
            SternBrocotTree.traverseFareySequenceNeighboursInCycle(limit, start, end, counter);

            long f = countFareySequence(N, start, end);

            System.out.println(counter.n.get());
            System.out.println(f);
            assertEquals(f, counter.n.get());
        }

    }

    private long countFareySequence(long N, SimpleFraction start, SimpleFraction end) {
        Iterator<SimpleFraction> iterator = FareySequence.iterator(N, start, end);
        long num = 0;
        while (iterator.hasNext()) {
            num++;
            iterator.next();
        }
        //exclude end
        num--;
        return num;
    }


    private static class Counter implements Function<SimpleFraction, Object> {

        private final AtomicLong n = new AtomicLong();

        @Override
        public Object apply(SimpleFraction fraction) {
            return n.incrementAndGet();
        }
    }

}