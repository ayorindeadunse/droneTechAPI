package com.ayorinde.dronetechapi.dronetechapi.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetDroneBatteryLevelRequest {
    private String SerialNumber;
}