package com.hargclinical.harg.utils;

public class CPF {
    public static boolean isCPFValid(String cpf) {
        if (cpf == null || cpf.length() != 11 || cpf.matches(cpf.charAt(0) + "{11}")) {
            return false;
        }

        int[] digits = new int[11];
        for (int i = 0; i < 11; i++) {
            digits[i] = Integer.parseInt(cpf.substring(i, i + 1));
        }

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += (10 - i) * digits[i];
        }
        int checkDigit1 = 11 - (sum % 11);
        if (checkDigit1 == 10 || checkDigit1 == 11) {
            checkDigit1 = 0;
        }
        if (checkDigit1 != digits[9]) {
            return false;
        }

        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += (11 - i) * digits[i];
        }
        int checkDigit2 = 11 - (sum % 11);
        if (checkDigit2 == 10 || checkDigit2 == 11) {
            checkDigit2 = 0;
        }
        return checkDigit2 == digits[10];
    }
}
