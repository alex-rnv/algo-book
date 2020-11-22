package com.alexrnv.algobook.cs.strings;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Strings {

    public static Map<Character, Byte> ALPHABET = Collections.unmodifiableMap(new HashMap<>() {{
        put('a', (byte) 1);
        put('b', (byte) 2);
        put('c', (byte) 3);
        put('d', (byte) 4);
        put('e', (byte) 5);
        put('f', (byte) 6);
        put('g', (byte) 7);
        put('h', (byte) 8);
        put('i', (byte) 9);
        put('j', (byte) 10);
        put('k', (byte) 11);
        put('l', (byte) 12);
        put('m', (byte) 13);
        put('n', (byte) 14);
        put('o', (byte) 15);
        put('p', (byte) 16);
        put('q', (byte) 17);
        put('r', (byte) 18);
        put('s', (byte) 19);
        put('t', (byte) 20);
        put('u', (byte) 21);
        put('v', (byte) 22);
        put('w', (byte) 23);
        put('x', (byte) 24);
        put('y', (byte) 25);
        put('z', (byte) 26);
    }});


    public static String reverse(String str) {
        StringBuffer sb = new StringBuffer();
        for (int i=str.length()-1; i>=0; i--) {
            sb.append(str.charAt(i));
        }
        return new String(sb);
    }

    public static boolean isPalindrome(String str) {
        return isPalindrome(str.toCharArray());
    }

    public static boolean isPalindrome(char[] letters) {
       int i=0, j=letters.length-1;
       while(j>i) {
           if (letters[i] != letters[j])
               return false;
           i++;
           j--;
       }
       return true;
    }
}
