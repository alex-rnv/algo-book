package com.alexrnv.algobook.math.algebra.fundamentals.primes;

import java.util.ArrayList;
import java.util.BitSet;

/**
 * A block (segment) in the segmented sieve iterator.
 * Optimized to store only odd numbers (even numbers are non-prime, except 2, by definition).
 */
class Block {
    private final int blockSize;
    private final long blockOffset;
    private final BitSet block;


    Block(int blockSize, long blockOffset) {
        this.blockSize = blockSize;
        this.block = new BitSet(blockSize >> 1);
//        this.block = new BitSet(blockSize);
        this.blockOffset = blockOffset;
    }

    int getBlockSize() {
        return blockSize;
    }

    long getBlockOffset() {
        return blockOffset;
    }

    boolean get(int blockIndex) {
        return (blockIndex & 1) == 0 || block.get(bitIndex(blockIndex));
//        return block.get(blockIndex);
    }

    void precalculate(ArrayList<Long> basePrimes) {
        for (long prime : basePrimes) {
            if (prime == 2L) continue;
            long startIndex = (blockOffset + prime - 1) / prime;
            long primeSqIdx = Math.max(startIndex, prime) * prime - blockOffset;
            if (primeSqIdx > blockSize) continue;
            int j = (int)primeSqIdx;
            if ((j & 1) == 0) j += prime;
            for (; j < blockSize; j += 2*prime) {
//                if ((j & 1) != 0)
                    block.set(bitIndex(j));
//                block.set(j);
            }
        }
    }

    private int bitIndex(long i) {
        return (int) (i >> 1);
    }

}
