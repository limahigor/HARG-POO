package com.hargclinical.harg.utils;

public class StringUtils {
    public static boolean containsLettersAndDigits(String str) {
        return str.matches("^(?=.*[a-zA-Z])(?=.*[0-9]).+$");
    }

    public static boolean containsOnlyDigits(String str) {
        return str.matches("\\d+");
    }
    
    public static boolean containsOnlyLetters(String str) {
        return str.matches("[a-zA-Z]+");
    }
}
