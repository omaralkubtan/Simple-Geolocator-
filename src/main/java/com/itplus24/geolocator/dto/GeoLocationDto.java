package com.itplus24.geolocator.dto;

import lombok.Data;

import java.util.List;

@Data
public class GeoLocationDto {

    private Long placeId;
    private long osm_id;
    private long placeRank;
    private List<Double> boundingBox;
    private Double importance;
    private Double longitude;
    private Double latitude;
    private String type;
    private String displayName;
    private String omsType;
    private String name;
    private String addressType;
    private String addressClass;

    @Override
    public String toString() {
        return "{" +
                "placeId=" + placeId +
                ", osm_id=" + osm_id +
                ", placeRank=" + placeRank +
                ", boundingBox=" + boundingBox +
                ", importance=" + importance +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", type='" + type + '\'' +
                ", displayName='" + displayName + '\'' +
                ", omsType='" + omsType + '\'' +
                ", name='" + name + '\'' +
                ", addressType='" + addressType + '\'' +
                ", addressClass='" + addressClass + '\'' +
                '}';
    }

}
