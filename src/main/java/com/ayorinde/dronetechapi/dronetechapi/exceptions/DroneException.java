package com.ayorinde.dronetechapi.dronetechapi.exceptions;

public class DroneException extends Exception{
    private String message;

    public DroneException(String message) {
        super(String.format(message));
    }
}
