package com.alexrnv.algobook.math.algebra.fundamentals;

public class Fibonacci {

    public static void main(String[] args) {
        long n = 100;
        System.out.println(getNth(n, LinearAlgorithm.getInstance()));
        System.out.println(getNth(n, FastDoublingAlgorithm.getInstance()));
    }

    public static long getNth(long n, Algorithm algorithm) {
        return algorithm.calc(n);
    }

    public interface Algorithm {
        long calc(long n);
    }

    public static class LinearAlgorithm implements Algorithm {

        private static LinearAlgorithm instance = new LinearAlgorithm();
        public static LinearAlgorithm getInstance() {
            return instance;
        }

        public long calc(long n) {
            long fib1=1, fib2=1, t;
            int k=2;
            while (k < n) {
                t = fib2;
                fib2 += fib1;
                fib1 = t;
                k++;
            }
            return fib2;
        }
    }

    /**
     * F(2k)=F(k)*(2F(k+1)-F(k)
     * F(2k+1)=F(k+1)^2+F(k)^2
     */
    public static class FastDoublingAlgorithm implements Algorithm {

        private static FastDoublingAlgorithm instance = new FastDoublingAlgorithm();
        public static FastDoublingAlgorithm getInstance() {
            return instance;
        }

        @Override
        public long calc(long n) {
            long fib1=1, fib2=1, t;
            int k=1;
            while ((2*k+1) <= n) {
                t = fib1 * (2*fib2 - fib1);
                fib2 = fib2*fib2 + fib1*fib1;
                fib1 = t;
                k *= 2;
            }
            while (k < (n-1)) {
                t = fib2;
                fib2 += fib1;
                fib1 = t;
                k++;
            }
            return fib2;
        }
    }
}
