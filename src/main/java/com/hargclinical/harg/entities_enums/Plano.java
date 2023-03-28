package com.hargclinical.harg.entities_enums;

public enum Plano {
    BRONZE(1),
    PRATA(2),
    OURO(3),
    PLATINA(4);

    private int code;

    private Plano(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
    
    public static Plano valueOf(int code) {
        for(Plano value : Plano.values()) {
            if(value.getCode() == code) {
                return value;
            }
        }
        return null;
        // throw new IllegalArgumentException("Código de plano inválido!");
    }

}