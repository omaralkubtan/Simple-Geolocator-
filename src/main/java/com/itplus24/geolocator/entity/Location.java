package com.itplus24.geolocator.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "locations")
public class Location {

    @Id
    @Column(name = "place_id")
    private long placeId;

    @Column(name = "osm_id")
    private long osm_id;

    @Column(name = "place_rank")
    private long placeRank;

    @Column(name = "bounding_box")
    private List<Double> boundingBox;

    @Column(name = "importance")
    private double importance;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "type")
    private String type;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "oms_type")
    private String omsType;

    @Column(name = "name")
    private String name;

    @Column(name = "address_type")
    private String addressType;

    @Column(name = "address_class")
    private String addressClass;

}
