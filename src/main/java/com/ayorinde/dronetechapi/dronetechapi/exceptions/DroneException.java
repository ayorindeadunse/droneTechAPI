package com.ayorinde.dronetechapi.dronetechapi.exceptions;

public class DroneException extends Exception{
    public DroneException(String message) {
        super(String.format(message));
    }
}
