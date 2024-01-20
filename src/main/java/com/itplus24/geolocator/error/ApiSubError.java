package com.itplus24.geolocator.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApiSubError {
    private final String name;
    private final String message;
}
