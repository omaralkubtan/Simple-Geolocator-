package com.itplus24.geolocator.dto;

import com.itplus24.geolocator.utils.Constants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SendResultDto {

    @NotBlank(message = "Please enter the email address!")
    @Pattern(regexp = Constants.EMAIL_REGEX, message = "Invalid Email!")
    private String email;

    private GeoLocationDto location;
}
