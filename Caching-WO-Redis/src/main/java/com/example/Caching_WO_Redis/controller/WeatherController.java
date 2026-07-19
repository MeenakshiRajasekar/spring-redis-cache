package com.example.Caching_WO_Redis.controller;

import com.example.Caching_WO_Redis.enitity.Weather;
import com.example.Caching_WO_Redis.repository.WeatherRepository;
import com.example.Caching_WO_Redis.service.CacheInspectionService;
import com.example.Caching_WO_Redis.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController      {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private CacheInspectionService cacheInspectionService;

    @GetMapping
    public String getWeatherDataByCity(@RequestParam String city){
        String weatherByCity = weatherService.getWeatherByCity(city);
        return weatherByCity;
    }

    @PostMapping
    public Weather saveWeatherData(@RequestBody Weather weather){
       return weatherRepository.save(weather);
    }

    @GetMapping("/all")
    public List<Weather> getAllWeatherData(){
        return weatherRepository.findAll();
    }

    @PutMapping("/{city}")
    public String updateWeatherData(@PathVariable String city, @RequestParam String weatherData){
         return weatherService.updateWeather(city, weatherData);
    }

    @DeleteMapping("/{city}")
    public void deleteWeatherData(@PathVariable String city){
        weatherService.deleteWeatherData(city);
        System.out.println("Weather data for " +city +" has been deleted");
    }

    @GetMapping("/get-cache")
    public void getCacheData(String name){
        cacheInspectionService.getCachedata(name);
    }
}
