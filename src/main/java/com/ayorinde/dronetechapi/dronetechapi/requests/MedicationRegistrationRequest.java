package com.ayorinde.dronetechapi.dronetechapi.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
public class MedicationRegistrationRequest {

    @Pattern(regexp = "[A-Za-z0-9_.-]*")
    private String name;
    private int medicineWeight;

    @Pattern(regexp = "[A-Z0-9_]")
    private String code;
    private String medicationImage;

}

