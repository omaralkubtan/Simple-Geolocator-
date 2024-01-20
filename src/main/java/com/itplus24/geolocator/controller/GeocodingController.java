package com.itplus24.geolocator.controller;

import com.itplus24.geolocator.dto.GeoLocationDto;
import com.itplus24.geolocator.dto.SendResultDto;
import com.itplus24.geolocator.service.GeocodingService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class GeocodingController {

    private final ModelMapper mapper;

    private final GeocodingService geocodingService;

    @GetMapping("/search")
    @Validated
    public GeoLocationDto search(@Valid @RequestParam("query") @NotBlank(message = "Please enter the address!") String query) {
        return mapper.map(geocodingService.search(query), GeoLocationDto.class);
    }

    @PostMapping("/search")
    @Validated
    public void sendResult(@Valid @RequestBody SendResultDto request) {
        geocodingService.sendGeoLocationEmail(request.getLocation(), request.getEmail());
    }

}