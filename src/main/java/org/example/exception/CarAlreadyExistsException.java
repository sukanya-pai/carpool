package org.example.exception;

public class CarAlreadyExistsException extends Exception{

    public CarAlreadyExistsException(String message){
        super(message);
    }
}
