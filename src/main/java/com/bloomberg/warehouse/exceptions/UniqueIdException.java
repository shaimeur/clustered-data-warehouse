package com.bloomberg.warehouse.exceptions;

public class UniqueIdException extends RuntimeException{
    public UniqueIdException(String message){
        super(message);
    }
}