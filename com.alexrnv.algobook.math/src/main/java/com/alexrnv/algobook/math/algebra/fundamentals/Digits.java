package com.alexrnv.algobook.math.algebra.fundamentals;

import java.math.BigInteger;
import java.util.Iterator;

public class Digits {

    private static final int MAX_NUMBER_OF_DIGITS_IN_LONG = numberOfDigits(Long.MAX_VALUE);

    public static void main(String[] args) {
         System.out.println(isPandigital1X(1234, (byte)5));

//        System.out.println(isPandigital19(996019920));
//        System.out.println(isPandigital19(123456789));
//        System.out.println(isPandigital19(987654321));
//        System.out.println(isPandigital19(192837465));
//        System.out.println(isPandigital19(987111111));
//        System.out.println(isPandigital19(987001234));
//        System.out.println(isPandigital19(123456788));
//        System.out.println(isPandigital19(102345678));
//        System.out.println(isPandigital19(1234567890));
//        System.out.println(isPandigital19(1234567890));
//        System.out.println(isPandigital19(1234567891));
//        System.out.println(isPandigital19(1234567899));
//        System.out.println(isPandigital19(1234567891));
//
//
//        System.out.println(isPandigital09(996019920));
//        System.out.println(isPandigital09(123456789));
//        System.out.println(isPandigital09(987654321));
//        System.out.println(isPandigital09(192837465));
//        System.out.println(isPandigital09(987111111));
//        System.out.println(isPandigital09(987001234));
//        System.out.println(isPandigital09(123456788));
//        System.out.println(isPandigital09(102345678));
//        System.out.println(isPandigital09(1234567891));
//        System.out.println(isPandigital09(1234567899));
//        System.out.println(isPandigital09(1234567891));
//        System.out.println(isPandigital09(1234567890));
//        System.out.println(isPandigital09(9087654321l));
//        System.out.println(isPandigital09(9876543210l));
    }

    public static byte[] toDigitArray(long n) {
        int d = numberOfDigits(n);
        byte[] digits = new byte[d];
        Iterator<Byte> iter = digitIterator(n);
        int i=1;
        while (iter.hasNext()) {
            digits[d-i] = iter.next();
            i++;
        }
        return digits;
    }

    public static byte[] toDigitArray(BigInteger n) {
        String[] str = n.toString().split("");
        byte[] digits = new byte[str.length];
        for (int k=0; k< str.length; k++) {
            digits[k] = Byte.valueOf(str[k]);
        }
        return digits;
    }

    public static long fromDigitArray(byte[] digits, int from, int to) {
        long mult = 1, number = 0;
        for (int i=to-1; i>=from; i--) {
            number += mult*digits[i];
            mult *= 10;
        }
        return number;
    }

    public static long fromDigitArray(byte[] digits) {
        return fromDigitArray(digits, 0, digits.length);
    }

    public static boolean isPandigital09(long n) {
        return isPandigital(n, (byte)0, (byte)10);
    }

    public static boolean isPandigital19(long n) {
        return isPandigital(n, (byte)1, (byte)10);
    }

    public static boolean isPandigital0X(long n, byte maxInclusive) {
        return isPandigital(n, (byte)0, (byte)(maxInclusive+1));
    }

    public static boolean isPandigital1X(long n, byte maxInclusive) {
        return isPandigital(n, (byte)1, (byte)(maxInclusive+1));
    }

    private static boolean isPandigital(long n, byte s, byte e) {
        byte[] digits = toDigitArray(n);
        if (digits.length != (e-s)) return false;
        for (byte i=s; i<e; i++) {
            byte b = digits[i-s];
            byte d = b == Byte.MIN_VALUE ? 0 : (byte)Math.abs(b);
            byte q = (byte)(d-s);
            if (q < 0 || q >= digits.length) return false;
            byte p = digits[q];
            if (p > 0) {
                digits[q] = (byte) -p;
            } else if (p == 0) {
                digits[q] = Byte.MIN_VALUE;
            } else
                return false;
        }
        return true;
    }

    public static int numberOfDigits(long n) {
        assert n >= 0;
        return n == 0 ? 1 : (int)(Math.log10(n)+1); //TBD can do faster
    }

    public static long reverseDigits(long n) {
        assert n > 0;

        long reverse = 0;
        while (n > 0) {
            reverse = reverse*10 + (n%10);
            n /= 10;
        }
        return reverse;
    }

    public static long concat(long n1, long n2) {
        int len1 = numberOfDigits(n1);
        int len2 = numberOfDigits(n2);
        if ((len1 + len2) > MAX_NUMBER_OF_DIGITS_IN_LONG)
            throw new ArithmeticException("numbers are too big to be concatenated into long value");

        return n1 * Arithmetic.binpow(10, len2) + n2;
    }

    public static long concat(long n1, long n2, long n3) {
        int len1 = numberOfDigits(n1);
        int len2 = numberOfDigits(n2);
        int len3 = numberOfDigits(n3);
        if ((len1 + len2 + len3) > MAX_NUMBER_OF_DIGITS_IN_LONG)
            throw new ArithmeticException("numbers are too big to be concatenated into long value");

        return n1 * Arithmetic.binpow(10, len2 + len3) + n2 * Arithmetic.binpow(10, len3) + n3;
    }

    public static long concat(long[] a) {
        int sumLen = 0;
        int[] len = new int[a.length];
        for (int i=0; i<a.length; i++) {
            len[i] = numberOfDigits(a[i]);
            sumLen += len[i];
        }
        if (sumLen > MAX_NUMBER_OF_DIGITS_IN_LONG)
            throw new ArithmeticException("numbers are too big to be concatenated into long value");

        long result = a[a.length-1];
        int l = 0;
        for (int i=a.length-2; i>=0; i--) {
            l += len[i+1];
            result += a[i] * Arithmetic.binpow(10, l);
        }
        return result;
    }

    public static Iterator<Byte> digitIterator(long n) {
        return new DigitIterator(n);
    }

    /**
     * Iterates over digits of non-negative number, presenting them as byte.
     * Throws assertion error for negative number.
     */
    private static class DigitIterator implements Iterator<Byte> {

        private long number;

        private DigitIterator(long number) {
            assert number > 0;
            this.number = number;
        }

        @Override
        public boolean hasNext() {
            return number > 0;
        }

        @Override
        public Byte next() {
            byte next = (byte)(number % 10);
            number /= 10;
            return next;
        }
    }
}
