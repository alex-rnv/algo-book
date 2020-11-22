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
        prepareNextBlock(true);
    }

    private boolean prepareNextBlock() {
        return prepareNextBlock(false);
    }

    private boolean prepareNextBlock(boolean adjustShiftInFirstBlock) {
        long blockOffset = (long) blockIndex * blockSize;

        if (blockOffset >= upperBound)
            return false;

        if ((blockOffset + blockSize) > upperBound) {
            this.currentBlock = new Block((int)(upperBound - blockOffset + 1), blockOffset);
        } else {
            this.currentBlock = new Block(blockSize, blockOffset);
        }
        this.currentBlock.precalculate(basePrimes);
        if (adjustShiftInFirstBlock) {
            this.shiftInBlock = (int) (basePrimes.get(basePrimes.size() - 1) - blockOffset + 1);
        } else {
            this.shiftInBlock = 0;
        }
        ++blockIndex;
        return true;
    }

    long nextPrime() {
        if (shiftInBlock >= currentBlock.getBlockSize()) {
            if (!prepareNextBlock()) {
                return 0;
            }
        }
        while (currentBlock.get(shiftInBlock)) {
            shiftInBlock++;
            if (shiftInBlock >= currentBlock.getBlockSize()) {
                if (!prepareNextBlock()) {
                    return 0;
                }
            }
        }
        long prime = currentBlock.getBlockOffset() + shiftInBlock;
        shiftInBlock++;
        return prime;
    }
}
