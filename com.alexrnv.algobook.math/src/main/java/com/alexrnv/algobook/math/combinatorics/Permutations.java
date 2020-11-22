package com.alexrnv.algobook.math.combinatorics;

import com.alexrnv.algobook.math.algebra.fundamentals.Arithmetic;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations {

    //tbd ok for small arrays
    public static boolean arePermutations(byte[] array1, byte[] array2) {
        if (array1.length != array2.length) return false;

        long[] hist1 = new long[Byte.MAX_VALUE - Byte.MIN_VALUE];
        long[] hist2 = new long[Byte.MAX_VALUE - Byte.MIN_VALUE];

        for (int i=0; i<array1.length; i++) {
            hist1[array1[i]]++;
            hist2[array2[i]]++;
        }
        for (int i=0; i<hist1.length; i++) {
            if (hist1[i] != hist2[i]) return false;
        }
        return true;
    }

    //tbd ok for small arrays
    public static boolean arePermutations(BigInteger b1, BigInteger b2) {
        if (b1.signum() != b2.signum()) return false;
        if (b1.signum() < 0) {
            b1 = b1.negate();
        }
        if (b2.signum() < 0) {
            b2 = b2.negate();
        }
        String[] str1 = b1.toString().split("");
        String[] str2 = b2.toString().split("");

        if (str1.length != str2.length) return false;

        long[] hist1 = new long[10];
        long[] hist2 = new long[10];

        for (int i=0; i<str1.length; i++) {
            hist1[Integer.parseInt(str1[i])]++;
            hist2[Integer.parseInt(str2[i])]++;
        }
        for (int i=0; i<hist1.length; i++) {
            if (hist1[i] != hist2[i]) return false;
        }
        return true;
    }

    //https://www.programcreek.com/2014/06/leetcode-next-permutation-java/
    public static void generateNextLexicographicPermutation(int[] array) {
        //find first decreasing digit
        int mark = -1;
        for (int i = array.length - 1; i > 0; i--) {
            if (array[i] > array[i - 1]) {
                mark = i - 1;
                break;
            }
        }

        if (mark == -1) {
            reverse(array, 0, array.length - 1);
            return;
        }

        int idx = array.length-1;
        for (int i = array.length-1; i >= mark+1; i--) {
            if (array[i] > array[mark]) {
                idx = i;
                break;
            }
        }

        swap(array, mark, idx);

        reverse(array, mark + 1, array.length - 1);
    }

    //https://www.programcreek.com/2014/06/leetcode-next-permutation-java/
    public static void generateNextLexicographicPermutation(byte[] array) {
        //find first decreasing digit
        int mark = -1;
        for (int i = array.length - 1; i > 0; i--) {
            if (array[i] > array[i - 1]) {
                mark = i - 1;
                break;
            }
        }

        if (mark == -1) {
            reverse(array, 0, array.length - 1);
            return;
        }

        int idx = array.length-1;
        for (int i = array.length-1; i >= mark+1; i--) {
            if (array[i] > array[mark]) {
                idx = i;
                break;
            }
        }

        swap(array, mark, idx);

        reverse(array, mark + 1, array.length - 1);
    }

    public static List<int[]> generateAllPermutations(int[] array) {
        BigInteger n = Arithmetic.factorial(array.length);
        List<int[]> result = new ArrayList<>(n.intValue());
        int[] copy = Arrays.copyOf(array, array.length);
//        basicRemoveAlgorithm(array, array.length, result);
        heapsAlgorithm(copy, copy.length, result);
        return result;
    }

    public static List<byte[]> generateAllPermutations(byte[] array) {
        BigInteger n = Arithmetic.factorial(array.length);
        List<byte[]> result = new ArrayList<>(n.intValue());
        byte[] copy = Arrays.copyOf(array, array.length);
        heapsAlgorithm(copy, copy.length, result);
        return result;
    }

    private static void heapsAlgorithm(int[] array, int n, List<int[]> result) {
        if(n == 1) {
            result.add(Arrays.copyOf(array, array.length));
        } else {
            for(int i = 0; i < (n - 1);i++) {
                heapsAlgorithm(array, n-1, result);
                // always swap the first when odd,
                // swap the i-th when even
                if(n % 2 == 0) swap(array, n-1, i);
                else swap(array, n-1, 0);
            }
            heapsAlgorithm(array, n-1, result);
        }
    }

    private static void heapsAlgorithm(byte[] array, int n, List<byte[]> result) {
        if(n == 1) {
            result.add(Arrays.copyOf(array, array.length));
        } else {
            for(int i = 0; i < (n - 1); i++) {
                heapsAlgorithm(array, n-1, result);
                // always swap the first when odd,
                // swap the i-th when even
                if(n % 2 == 0) swap(array, n-1, i);
                else swap(array, n-1, 0);
            }
            heapsAlgorithm(array, n-1, result);
        }
    }

    private static void basicRemoveAlgorithm(int[] array, int n, List<int[]> result) {
        if (n == 1) {
            result.add(Arrays.copyOf(array, array.length));
        } else {
            for (int i=0; i<n; i++) {
                swap(array, i, n-1);
                basicRemoveAlgorithm(array, n-1, result);
                swap(array, n-1, i);
            }
        }
    }

    private static void basicRemoveAlgorithm(byte[] array, int n, List<byte[]> result) {
        if (n == 1) {
            result.add(Arrays.copyOf(array, array.length));
        } else {
            for (int i=0; i<n; i++) {
                swap(array, i, n-1);
                basicRemoveAlgorithm(array, n-1, result);
                swap(array, n-1, i);
            }
        }
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }

    private static void swap(byte[] a, int i, int j) {
        byte t = a[i]; a[i] = a[j]; a[j] = t;
    }

    private static void reverse(int[] a, int i, int j) {
        while(i < j) {
            swap(a, i, j);
            i++;
            j--;
        }
    }

    private static void reverse(byte[] a, int i, int j) {
        while(i < j) {
            swap(a, i, j);
            i++;
            j--;
        }
    }
}
