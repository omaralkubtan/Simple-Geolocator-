package com.itplus24.geolocator.service;

import com.itplus24.geolocator.dto.GeoLocationDto;
import com.itplus24.geolocator.entity.Location;
import com.itplus24.geolocator.error.exceptions.SomethingWentWrongException;
import com.itplus24.geolocator.repository.GeoLocationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;

import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.itplus24.geolocator.utils.Mappings.mapJsonToLocation;

@Service
@RequiredArgsConstructor
public class GeocodingService {

    private final ModelMapper modelMapper;

    private final EmailService emailService;

    private final GeoLocationRepository geoLocationRepository;
    private final OkHttpClient httpClient = new OkHttpClient();


    @Transactional
    public Location search(String query)  {

        var location = geoLocationRepository.findByName(query);

        // If the location is not stored in the database, fetch it then store it the return it
        if (location.isEmpty()) {
            System.out.println("Fat Here");
            Request request = new Request.Builder()
                    .url("https://nominatim.openstreetmap.org/search?format=json&q=" + query)
                    .build();

            try (Response response = httpClient.newCall(request).execute()) {
                if (!response.isSuccessful()) throw new SomethingWentWrongException("Something went wrong, check your internet connection and try again");

                JSONArray jsonArray = new JSONArray(response.body().string());

                if (!jsonArray.isEmpty()) {
                    Location thirdpartyLocation =  mapJsonToLocation(jsonArray.getJSONObject(0));
                    geoLocationRepository.save(thirdpartyLocation);
                    return thirdpartyLocation;
                }

                return null;
            } catch (Exception e){

            }
        }

        return location.get();
    }

    public void sendGeoLocationEmail(GeoLocationDto request, String email) {
         emailService.sendEmail(email, "GeoLocation information", request.toString());
    }
}