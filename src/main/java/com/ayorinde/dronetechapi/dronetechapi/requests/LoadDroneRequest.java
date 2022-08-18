package com.ayorinde.dronetechapi.dronetechapi.requests;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class LoadDroneRequest {
    private String serialNumber;
    private List<String> medicineCode;
}
