package com.alexrnv.algobook.math.algebra.specialfunctions.phi;

import com.alexrnv.algobook.math.algebra.fundamentals.factorization.Divisors;

import java.util.Iterator;
import java.util.List;

public class PhiFunction {

    protected static int PHI_TABLE_SIZE = 1024;
    protected static long[] PHI_TABLE = new long[PHI_TABLE_SIZE];

    static {
        arrayOfSize(PHI_TABLE_SIZE).fillTheTable(PHI_TABLE);
    }

    public static long of(long n) {
        if (n < PHI_TABLE_SIZE) return PHI_TABLE[(int)n];
        else return calculate(n);
    }

    public static long calculate(long n) {
        if (n == 1) return 1L;
        List<Long> uniqueFactors = Divisors.factorsUnique(n);
        long phi = n;
        for (long f : uniqueFactors) {
            phi = (phi / f) * (f - 1);
        }
        return phi;
    }

    public static Iterator<Long> iterator(int limit) {
        return new PhiIterator(limit);
    }

    public static PhiArray arrayOfSize(int N) {
        return new PhiArray(N);
    }

}
