package com.itplus24.geolocator.utils;

import com.itplus24.geolocator.entity.Location;
import org.json.JSONObject;

import java.util.stream.Collectors;

public class Mappings {

    public static Location mapJsonToLocation(JSONObject jsonObject) {
        Location location = new Location();

        location.setOsm_id(jsonObject.getLong("osm_id"));
        location.setPlaceRank(jsonObject.getLong("place_rank"));
        location.setBoundingBox(jsonObject.getJSONArray("boundingbox").toList().stream().map(Object::toString).map(Double::valueOf).collect(Collectors.toList()));
        location.setImportance(jsonObject.getDouble("importance"));
        location.setLongitude(Double.valueOf(jsonObject.getString("lon")));
        location.setLatitude(Double.valueOf(jsonObject.getString("lat")));
        location.setType(jsonObject.getString("type"));
        location.setDisplayName(jsonObject.getString("display_name"));
        location.setOmsType(jsonObject.getString("osm_type"));
        location.setName(jsonObject.getString("name"));
        location.setAddressType(jsonObject.getString("addresstype"));
        location.setAddressClass(jsonObject.getString("class"));
        location.setPlaceId(jsonObject.getLong("place_id"));

        return location;
    }
}
