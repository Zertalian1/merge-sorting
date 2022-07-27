package com.company;

public class IncorrectDataException extends Exception{
    private final String message;

    public String getMassage() {
        return message;
    }

    public IncorrectDataException(String message){
        super(message);
        this.message = message;
    }
}
