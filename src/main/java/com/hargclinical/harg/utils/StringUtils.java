package com.hargclinical.harg.utils;

public class StringUtils {
    private StringUtils() {}

    public static boolean containsLettersAndDigits(String str) {
        return str.matches("^(?=.*[a-zA-Z])(?=.*\\d).+$");
    }

    public static boolean containsOnlyDigits(String str) {
        return str.matches("\\d+");
    }
    
    public static boolean containsOnlyLetters(String str) {
        return str.matches("[a-zA-Z]+");
    }
}
