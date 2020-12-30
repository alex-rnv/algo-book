package com.alexrnv.algobook.math.algebra.fundamentals.primes;

import java.util.*;

/**
 * https://cp-algorithms.com/algebra/factorization.html
 * https://cp-algorithms.com/algebra/divisors.html
 */
public class Primes {

    public static List<Long> primes(long upperBound) {
        return sieveOfEratosthenes(upperBound);
    }

    public static void collectPrimes(long upperBound, Collection<Long> result) {
        Iterator<Long> iterator = Primes.iterator(upperBound);
        while(iterator.hasNext()) {
            result.add(iterator.next());
        }
    }

    //O(n) space
    //O(nloglogn) time
    // n - upperBound
    protected static List<Long> sieveOfEratosthenes(long upperBound) {
        if (upperBound <= 1) return Collections.emptyList();
        if (upperBound > 2L * Integer.MAX_VALUE) throw new ArithmeticException("overflow");

        BitSet sieve = new BitSet((int)(upperBound / 2));

        //approx. number of primes under x is ln(x)
        List<Long> result = new ArrayList<>(Math.round((float) Math.log(upperBound) * 1.1f));
        result.add(2L);

        long sqrt = (long) Math.sqrt(upperBound);
        for (long n = 3; n <= sqrt; n+=2) {
            if (!sieve.get((int)(n>>1)-1)) {
                result.add(n);
                for (long j = n*n; j <= upperBound && j > 0; j += 2*n)
                    sieve.set((int)(j>>1)-1);
            }
        }
        if ((sqrt&1) == 0)
            sqrt--;
        for (long n = sqrt + 2; n <= upperBound; n+=2) {
            if (!sieve.get((int)(n>>1)-1)) {
                result.add(n);
            }
        }
        return result;
    }

    public static Iterator<Long> iterator(long upperBound) {
        return PrimesIteratorFactory.createIterator(upperBound);
    }

    public static boolean isPrime(int n) {
        if (n == 2) return true;
        if ((n & 1) == 0) return false;
        for (int d = 3; d * d <= n; d += 2) {
            if (n % d == 0) return false;
        }
        return true;
    }

    public static boolean isPrime(long n) {
        if (n == 2) return true;
        if ((n & 1) == 0) return false;
        for (long d = 3; d * d <= n; d += 2) {
            if (n % d == 0) return false;
        }
        return true;
    }

}