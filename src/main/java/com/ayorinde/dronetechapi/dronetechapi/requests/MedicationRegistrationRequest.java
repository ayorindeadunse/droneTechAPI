package com.ayorinde.dronetechapi.dronetechapi.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class MedicationRegistrationRequest {

    @NotBlank
    @Pattern(regexp = "[A-Za-z0-9_.-]*")
    private String name;
    @NotBlank
    private int medicineWeight;

    @NotBlank
    @Pattern(regexp = "[A-Z0-9_]")
    private String code;
    private String medicationImage;

}

