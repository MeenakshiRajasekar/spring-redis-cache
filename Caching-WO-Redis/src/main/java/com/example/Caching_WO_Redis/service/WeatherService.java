package com.example.Caching_WO_Redis.service;

import com.example.Caching_WO_Redis.enitity.Weather;
import com.example.Caching_WO_Redis.repository.WeatherRepository;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WeatherService {

    private final WeatherRepository weatherRepository;

    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @Cacheable(value = "weather", key="#city")
    public String getWeatherByCity(String city) {
        System.out.println("Fetching weather data for city: " + city);
        Optional<Weather> weather = weatherRepository.findByCity(city);
        return weather.map(w -> w.getForecast())
                .orElse("Weather data is not available for the city");
    }

    @CachePut(value = "weather", key="#city")
    public String updateWeather(String city, String updatedWeather){
        weatherRepository.findByCity(city).ifPresent(weather -> {
            weather.setForecast(updatedWeather);
            weatherRepository.save(weather);
        });
        return updatedWeather;
    }

    @Transactional
    @CacheEvict(value = "weather", key = "#city")
    public void deleteWeatherData(String city){
        System.out.println("Deleting weather data for the city : " +city);
        weatherRepository.deleteByCity(city);
    }


}