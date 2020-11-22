package com.alexrnv.algobook.math.algebra.fundamentals.factorization;

import com.alexrnv.algobook.math.algebra.fundamentals.primes.Primes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Divisors {

    //O(log(sqrt((n))) space
    //O(sqrt(n)) time = sqrt(n) to get primes + log(n) to iterate primes
    public static List<Long> factorsAll(long n) {
        if (n == 1) return Collections.singletonList(1L);
        List<Long> primes = Primes.primes((int) (Math.sqrt(n)));
        List<Long> result = new ArrayList<>();
        for (long prime : primes) {
            while (n % prime == 0) {
                n /= prime;
                result.add(prime);
            }
        }
        if (n > 1) result.add(n);
        return result;
    }

    //O(log(sqrt((n))) space
    //O(sqrt(n)) time = sqrt(n) to get primes + log(n) to iterate primes
    public static List<Long> factorsUnique(long n) {
        if (n == 0) return Collections.emptyList();
        if (n == 1) return Collections.singletonList(1L);
        List<Long> result = new ArrayList<>();
        if ((n&1) == 0) {
            result.add(2L);
            while((n&1) == 0) {
                n = n >> 1;
            }
        }
        List<Long> primes = Primes.primes(n);
        factorsUnique(n, primes, result);
        return result;
    }

    //O(sqrt(n)) time = log(n) to iterate primes
    public static void factorsUnique(long n, List<Long> primes, List<Long> result) {
        if (n == 1) return;
        for (long prime : primes) {
            if (n % prime == 0) {
                n /= prime;
                result.add(prime);
            }
            while (n % prime == 0) {
                n /= prime;
            }
        }
        if (n > 1)
            result.add(n);
    }

    //O(sqrt(n)) time = log(n) to iterate primes
    public static int numFactorsUnique(long n, List<Long> primes) {
        if (n == 1) return 1;
        int result = 0;
        for (long prime : primes) {
            if (n % prime == 0) {
                n /= prime;
                result++;
            }
            while (n % prime == 0) {
                n /= prime;
            }
        }
        if (n > 1)
            result++;

        return result;
    }

    //O(log(sqrt((n))) space
    //O(sqrt(n)) time = sqrt(n) to get primes + log(n) to iterate primes
    public static int numberOfDivisors(long n) {
        if (n < 0) n = -n;
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;

        int power = 0;
        while (n % 2 == 0) {
            n /= 2;
            power++;
        }
        int result = power + 1;

        List<Long> primes = Primes.primes(n);
        for (long prime : primes) {
            if (prime == 2) continue;
            power = 0;
            while (n % prime == 0) {
                power++;
                n /= prime;
            }
            result *= (power + 1);
            if (n == 1) break;
        }
        if (n > 1) result++;
        return result;
    }

    /**
     * @return sum of divisors including the number itself
     */
    public static long sumOfDivisors(long n) {
        if (n < 0) n = -n;
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 3;

        long p = 2;
        while (n % 2 == 0) {
            n /= 2;
            p *= 2;
        }
        long result = p - 1;

        List<Long> primes = Primes.primes(n);
        for (long prime : primes) {
            if (prime == 2) continue;
            p = prime;
            while (n % prime == 0) {
                p *= prime;
                n /= prime;
            }
            result *= ((p - 1) / (prime - 1));
            if (n == 1) break;
        }
        if (n > 1) result *= ((n * n - 1) / (n - 1));
        return result;
    }

    /**
     * @return sum of divisors excluding the number itself
     */
    public static long sumOfProperDivisors(long n) {
        return sumOfDivisors(n) - n;
    }

    /**
     * @return true if the sum of all proper divisors is greater than n
     */
    public static boolean isAbundantNumber(int n) {
        return sumOfProperDivisors(n) > n;
    }

    /**
     * @return true if the sum of all proper divisors is less than n
     */
    public static boolean isDeficientNumber(int n) {
        return sumOfProperDivisors(n) < n;
    }

    /**
     * @return true if the sum of all proper divisors is equal to n
     */
    public static boolean isPerfectNumber(int n) {
        return sumOfProperDivisors(n) == n;
    }

}
