package com.ayorinde.dronetechapi.dronetechapi.exceptions;

public class MedicationException extends Exception {
    public MedicationException(String message) {
        super(String.format(message));
    }
}
