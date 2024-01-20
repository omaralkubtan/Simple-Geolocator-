package com.itplus24.geolocator.repository;

import com.itplus24.geolocator.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GeoLocationRepository extends JpaRepository<Location, Long> {

    @Query(value = "SELECT * FROM locations WHERE name LIKE :name " +
            " OR display_name LIKE :name " , nativeQuery = true)
    Optional<Location> findByName(String name);
}
