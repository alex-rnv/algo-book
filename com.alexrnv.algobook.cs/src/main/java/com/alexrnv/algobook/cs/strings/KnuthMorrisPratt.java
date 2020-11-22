package com.alexrnv.algobook.cs.strings;

public class KnuthMorrisPratt {

    public static int[] prefixFunction(String s) {
        int n = s.length();
        int[] pi = new int[n];
        for (int i = 1; i < n; i++) {
            int j = pi[i-1];
            while (j > 0 && s.charAt(i) != s.charAt(j))
                j = pi[j-1];
            if (s.charAt(i) == s.charAt(j))
                j++;
            pi[i] = j;
        }
        return pi;
    }
}
