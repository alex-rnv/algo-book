package com.alexrnv.algobook.math.algebra.numbers.roots;

import com.alexrnv.algobook.math.algebra.fundamentals.Digits;

import java.math.BigInteger;

/**
 * Calculates Nth root of the number, digit-by-digit: https://en.wikipedia.org/wiki/Shifting_nth_root_algorithm.
 */
public class ShiftingNthDigitByDigitAlgorithm {

    public static String[] solve(int precision, BigInteger[] radicand, int degree) {
        return new Solver(precision).solve(radicand, degree);
    }

    private static class Solver {
        private final int base = 10;
        private final int precision;
        private final BigInteger B = BigInteger.valueOf(base);

        private int n;
        private BigInteger Bn;
        private BigInteger y = BigInteger.ZERO;
        private BigInteger r = BigInteger.ZERO;
        private BigInteger alpha = BigInteger.ZERO;
        private BigInteger beta = BigInteger.ZERO;
        private BigInteger left;
        private BigInteger right;

        private byte[] integers;
        private byte[] decimals;
        private int p0, p1;

        /**
         * @param precision - number of decimal digits to calculate for irrational numbers
         */
        private Solver(int precision) {
            this.precision = precision;
        }

        /**
         * Implementation details:
         * B = base.
         * n - degree of the root.
         * x - radicand processed so far.
         * y - root extracted so far.
         * r - remainder.
         * alpha - next n digits of the radicand.
         * beta - next digit of the root.
         *
         * Initial value of alpha is the leftmost aligned block of n integers, meaning that the decimal point falls between blocks.
         * E.g.: the consecutive blocks for number 123.4 are 01, 23, 40.
         *
         * Current implementation avoid using x variable as described in the article, since it contributes to the biggest
         * computation as being the biggest BigInteger values on each iteration. Instead, temporary variables
         * left = (By+beta)^n - B^n*y^n
         * and
         * right =  B^n*r + alpha
         * are reused on each iteration. Please, check linked article for details.
         *
         * @param radicand - the number to extract root from, as an array of integer and decimal parts.
         * @param degree - degree of the root (n)
         * @return array of two strings corresponding to integer and decimal part of computed value (note the length of decimal part is limited by precision parameter).
         */
        private String[] solve(BigInteger[] radicand, int degree) {
            this.n = degree;
            this.Bn = B.pow(n);

            parseRadicandIntoDigitArrays(radicand);
            initBlockPointers();

            String[] result = new String[2];

            processIntegerPartOfRadicand();

            result[0] = y.toString();

            resetBlockPointers();

            int l0 = lengthInDigits(y);
            processDecimalPartOfTheRadicand(l0);

            result[1] = y.toString().substring(l0);
            return result;
        }


        private void processIntegerPartOfRadicand() {
            while(p1 <= integers.length) {
                setNextAlignedIntegerBlockToAlpha();
                findBeta();
                updateYAndR();
                incrementBlockPointers();
            }
        }

        private void updateYAndR() {
            y = y.multiply(B).add(beta);
            r = this.right.subtract(this.left);
        }

        private void setNextAlignedIntegerBlockToAlpha() {
            this.alpha = BigInteger.valueOf(Digits.fromDigitArray(integers, p0, p1));
        }

        private void processDecimalPartOfTheRadicand(int l0) {
            while(lengthInDigits(y) < l0 + precision) {
                setNextAlignedDecimalBlockIterator();
                findBeta();
                updateYAndR();
                incrementBlockPointers();
            }
        }

        private void setNextAlignedDecimalBlockIterator() {
            alpha = BigInteger.ZERO;
            if (decimals != null && p0 < decimals.length) {
                if (p1 <= decimals.length) {
                    alpha = BigInteger.valueOf(Digits.fromDigitArray(decimals, p0, p1));
                } else {
                    int diff = p1 - decimals.length;
                    alpha = BigInteger.valueOf(Digits.fromDigitArray(decimals, p0, decimals.length)).multiply(B.pow(diff));
                }
            }
        }

        /**
         * Evaluates the largest beta value that satisfies expression:
         * (By+beta)^n - B^n*y^n <= B^n*r + alpha
         * The left and right parts are stored in this.left and this.right variables and reused to
         * calculate next y and r values.
         */
        private void findBeta() {
            long beta = 0;

            BigInteger By = y.multiply(B);
            BigInteger ByN = By.pow(n);

            this.right = Bn.multiply(r).add(alpha);
            BigInteger rightInvariant = right.add(ByN);
            BigInteger leftSideVarPrev = ByN;

            //b == 0 wil always satisfy
            //todo: binary search
            for (long b = 1; b < base; b++) {
                BigInteger leftSideVar = By.add(BigInteger.valueOf(b)).pow(n);
                if (leftSideVar.compareTo(rightInvariant) > 0) break;
                beta = b;
                leftSideVarPrev = leftSideVar;
            }

            this.left = leftSideVarPrev.subtract(ByN);
            this.beta = BigInteger.valueOf(beta);
        }

        private void parseRadicandIntoDigitArrays(BigInteger[] radicand) {
            byte[][] digits = toDigits(radicand);
            integers = digits[0];
            decimals = digits[1];
        }

        private static byte[][] toDigits(BigInteger[] number) {
            byte[][] result = new byte[2][];

            String integer = number[0].toString();
            result[0] = stringToDigits(integer);
            if (number.length > 1) {
                String decimal = number[1].toString();
                result[1] = stringToDigits(decimal);
            }
            return result;
        }

        private static byte[] stringToDigits(String str) {
            byte[] result = new byte[str.length()];
            for (int i=0; i<str.length(); i++) {
                result[i] = (byte) Character.getNumericValue(str.charAt(i));
            }
            return result;
        }

        private static int lengthInDigits(BigInteger bigInteger) {
            return bigInteger.toString().length();
        }

        private void initBlockPointers() {
            p0 = 0;
            p1 = integers.length % n;
            if (p1 == 0) p1 = n;
        }

        private void resetBlockPointers() {
            p0 = 0;
            p1 = n;
        }

        private void incrementBlockPointers() {
            p0 = p1;
            p1 += n;
        }

    }
}
