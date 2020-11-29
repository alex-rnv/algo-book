package com.alexrnv.algobook.various.roman;

public class RomanNumerals {

    public static int convertToNumeric(String romanNumber) {
        return new RomanToNumeric().convert(romanNumber);
    }

    public static String convertToRoman(int number) {
        return new NumericToRoman().convert(number);
    }

    public static String convertToMinForm(String romanNumber) {
        int number = new RomanToNumeric().convert(romanNumber);
        return new NumericToRoman().convert(number);
    }

}
