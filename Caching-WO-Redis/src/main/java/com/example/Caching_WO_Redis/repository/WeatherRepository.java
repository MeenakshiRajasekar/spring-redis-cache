package com.example.Caching_WO_Redis.repository;

import com.example.Caching_WO_Redis.enitity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Integer> {

    Optional<Weather> findByCity(String city);
    void deleteByCity(String city);
}
