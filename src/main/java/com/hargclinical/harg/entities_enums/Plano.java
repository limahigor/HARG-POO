package com.hargclinical.harg.entities_enums;

import com.hargclinical.harg.services.exceptions.IllegalArgument;

public enum Plano {
    NENHUM(0),
    BRONZE(1),
    PRATA(2),
    OURO(3),
    PLATINA(4);

    private final int code;

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
        
        throw new IllegalArgument("Código do plano inválido! [0-4]");
    }

}