package com.alexrnv.algobook.math.algebra.fundamentals.factorization;

import com.alexrnv.algobook.math.algebra.fundamentals.primes.Primes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * All the tasks related to number factorization, with analysis.
 * Links:
 * https://en.wikipedia.org/wiki/Prime_omega_function
 * https://en.wikipedia.org/wiki/Prime-counting_function
 * https://en.wikipedia.org/wiki/Divisor_function#Approximate_growth_rate
 * http://ramanujan.sirinudi.org/Volumes/published/ram35.pdf
 *
 */
public class Divisors {

    /**
     * Returns the list of all factors of n (with repetitions). <br/>
     * Uses the fact that we only need to examine primes in the range [2; ⎣√n⎦].  <br/><br/>
     *
     * Algorithm analysis: <br/>
     * Time complexity O(√n*log(log(√n)): <br/>
     * <ol>
     *     <li> O(√n*log(log(√n)) - to calculate primes from 0 to √n (See {@link Primes#primes(long)}). </li>
     *     <li> O(√n/log(√n)) - to iterate over primes and collect results. <sup>1</sup> </li>
     * </ol>
     * In case of pre-calculated primes time complexity could be reduced O(√n). <br/><br/>
     * Space complexity O(√n*log(log(√n))): <br/>
     * <ol>
     *     <li>O(√n/log(√n)) - to store a list of primes below √n <sup>1</sup></li>
     *     <li>O(√n*log(log(√n))) - the upper bound to the number of divisors in result array <sup>2</sup></li>
     * </ol>
     * <br/>
     * <sup>1</sup> - see <a href="https://en.wikipedia.org/wiki/Prime-counting_function">Prime-counting function</a> <br/>
     * <sup>2</sup> - also known as Ω(n), see <a href="https://en.wikipedia.org/wiki/Divisor_function#Approximate_growth_rate">Robin's inequality</a>
     *
     * @see Primes#primes(long)
     */
    public static List<Long> factorsAll(long n) {
        if (n == 1) return Collections.singletonList(1L);
        int bound = (int) (Math.sqrt(n));
        List<Long> primes = Primes.primes(bound);
        // for huge numbers the factor will exceed 2, so the array must be reallocated
        // in majority of situations, the factor of 2 is a very safe estimate
        List<Long> result = new ArrayList<>(bound * 2);
        for (long prime : primes) {
            if (prime > n)
                break;
            while (n % prime == 0) {
                n /= prime;
                result.add(prime);
            }
        }
        if (n > 1)
            result.add(n);

        return result;
    }

    /**
     * Returns the list of unique factors of n (no repetitions). <br/>
     * As an optimization, the factor of 2 is processed separately.
     * The number is divided by 2 until it is odd, yielding smaller number in many cases, and
     * a smaller number of primes to calculate. <br/>
     *
     * Algorithm analysis: <br/>
     * Time complexity O(√n*log(log(√n)): <br/>
     * <ol>
     *     <li> O(√n*log(log(√n)) - to calculate primes from 0 to √n (See {@link Primes#primes(long)}). </li>
     *     <li> O(√n/log(√n)) - {@link Divisors#factorsUnique(long, java.util.List, java.util.List)}. <sup>1</sup> </li>
     * </ol>
     * In case of pre-calculated primes time complexity could be reduced to O(√n). <br/><br/>
     * Space complexity O(√n/log(log(√n))): <br/>
     * <ol>
     *     <li>O(√n/log(√n)) - to store a list of primes below √n <sup>1</sup></li>
     *     <li>O(log(√n)/log(log(√n))) - the upper bound to the number of unique divisors in result array <sup>2</sup></li>
     * </ol>
     * <br/>
     * <sup>1</sup> - see <a href="https://en.wikipedia.org/wiki/Prime-counting_function">Prime-counting function</a> <br/>
     * <sup>2</sup> - also known as ω(n), see <a href="http://ramanujan.sirinudi.org/Volumes/published/ram35.pdf">Ramanujan's work</a>
     *
     * @see Divisors#factorsUnique(long, java.util.List, java.util.List)
     */
    public static List<Long> factorsUnique(long n) {
        if (n == 0) return Collections.emptyList();
        if (n == 1) return Collections.singletonList(1L);

        boolean add2 = false;
        if ((n&1) == 0) {
            add2 = true;
            n = n >> 1;
            while((n&1) == 0) {
                n = n >> 1;
            }
        }

        int bound = (int) (Math.sqrt(n));
        // for huge numbers the factor will exceed 2, so the array must be reallocated
        // in majority of situations, the factor of 2 is a very safe estimate
        // +1 element for prime number 2
        List<Long> result = new ArrayList<>(bound * 2 + 1);
        if (add2)
            result.add(2L);

        List<Long> primes = Primes.primes(bound);
        factorsUnique(n, primes, result);
        return result;
    }

    /**
     * Adds only unique factors of n from the provided list of primes into result list. <br/>
     * Uses the fact that we only need to examine primes in the range [2; ⎣√n⎦].  <br/><br/>
     * For complexity analysis refer to {@link Divisors#factorsUnique(long)} <br/>
     *
     * @see Divisors#factorsUnique(long)
     * @see Primes#primes(long)
     */
    private static void factorsUnique(long n, List<Long> primes, List<Long> result) {
        if (n == 1) return;
        for (long prime : primes) {
            if (prime > n)
                break;
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

    /**
     * Similar to {@link Divisors#factorsUnique(long, java.util.List, java.util.List)}, but only stores the number of results
     * instead of the list.
     * @see Divisors#factorsUnique(long, java.util.List, java.util.List)
     */
    private static int numFactorsUnique(long n, List<Long> primes) {
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

    /**
     * Returns the number of divisors of n, including n itself. <br/>
     * Uses idea described <a href="https://cp-algorithms.com/algebra/divisors.html">here</a> to count the number of
     * divisors without storing them. <br/>
     * Check {@link Divisors#factorsUnique(long)} for complexity analysis.
     *
     * @see Divisors#factorsUnique(long)
     * @see Divisors#sumOfDivisors(long)
     */
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
     * Returns the sum of divisors of n, including n itself. <br/>
     * Uses idea described <a href="https://cp-algorithms.com/algebra/divisors.html">here</a> to count the number of
     * divisors without storing them. <br/>
     * Check {@link Divisors#factorsUnique(long)} for complexity analysis.
     *
     * @see Divisors#factorsUnique(long)
     * @see Divisors#numberOfDivisors(long)
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
