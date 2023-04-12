package com.hargclinical.harg.services.exceptions;

public class DuplicateEntryException extends RuntimeException{

    public DuplicateEntryException(String message){
        super(message);
    }
}
