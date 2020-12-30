package com.alexrnv.algobook.math.algebra.fundamentals.primes;

import java.util.ArrayList;

class BlockIterator {
    private final int blockSize;
    private final long upperBound;
    private final ArrayList<Long> basePrimes;
    private int blockIndex;
    private Block currentBlock;
    private int shiftInBlock;

    BlockIterator(int blockSize, long upperBound, int firstBlockIndex, ArrayList<Long> basePrimes) {
        this.blockSize = blockSize;
        this.upperBound = upperBound;
        this.basePrimes = basePrimes;
        this.blockIndex = firstBlockIndex;
        prepareNextBlockOrFinish(true);
    }

    private boolean prepareNextBlockOrFinish() {
        return prepareNextBlockOrFinish(false);
    }

    private boolean prepareNextBlockOrFinish(boolean adjustShiftInFirstBlock) {
        long blockOffset = (long) blockIndex * blockSize;

        if (blockOffset >= upperBound)
            return true;

        if ((blockOffset + blockSize) > upperBound) {
            this.currentBlock = new Block((int)(upperBound - blockOffset + 1), blockOffset);
        } else {
            this.currentBlock = new Block(blockSize, blockOffset);
        }
        this.currentBlock.precalculate(basePrimes);
        if (adjustShiftInFirstBlock) {
            int nextPrimeIndexInBlock = (int)(basePrimes.get(basePrimes.size() - 1) - blockOffset + 1);
            this.shiftInBlock = Math.max(nextPrimeIndexInBlock, 0);
        } else {
            this.shiftInBlock = 0;
        }
        ++blockIndex;
        return false;
    }

    long nextPrime() {
        if (shiftInBlock >= currentBlock.getBlockSize()) {
            if (prepareNextBlockOrFinish()) {
                return 0;
            }
        }
        while (currentBlock.get(shiftInBlock)) {
            shiftInBlock++;
            if (shiftInBlock >= currentBlock.getBlockSize()) {
                if (prepareNextBlockOrFinish()) {
                    return 0;
                }
            }
        }
        long prime = currentBlock.getBlockOffset() + shiftInBlock;
        shiftInBlock++;
        return prime;
    }
}
