package com.hargclinical.harg.services.exceptions;

public class IllegalArgument extends RuntimeException {
    public IllegalArgument(String msg) {
        super("Invalid argument." + msg);
    }
}
