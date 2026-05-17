package com.travelplanner.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.travelplanner.model.City;
import com.travelplanner.model.WeatherState;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CityRepository {

    private static CityRepository instance;
    private final List<City> cities;

    private CityRepository() {
        cities = new ArrayList<>();
        loadCitiesFromJSON();
    }

    private void loadCitiesFromJSON() {
        try {
            String jsonPath = "cities.json";
            String content = new String(Files.readAllBytes(Paths.get(jsonPath)));
            
            Gson gson = new Gson();
            List<CityData> cityDataList = gson.fromJson(content, 
                new TypeToken<List<CityData>>(){}.getType());
            
            for (CityData data : cityDataList) {
                cities.add(new City(
                    data.name,
                    data.population,
                    data.area,
                    data.currentTemperature,
                    WeatherState.valueOf(data.currentWeatherState)
                ));
            }
        } catch (IOException e) {
            // Fallback to default cities if JSON not found
            cities.add(new City("Ankara", 5780000, 24521, 18, WeatherState.SUNNY));
            cities.add(new City("Istanbul", 15800000, 5461, 21, WeatherState.CLOUDY));
            cities.add(new City("Izmir", 4460000, 11891, 24, WeatherState.SUNNY));
            cities.add(new City("Bursa", 3200000, 10813, 19, WeatherState.RAINY));
            cities.add(new City("Antalya", 2700000, 20723, 27, WeatherState.SUNNY));
            cities.add(new City("Erzurum", 750000, 25066, 5, WeatherState.SNOWY));
            System.out.println("Warning: cities.json not found. Using default cities.");
        }
    }

    public static CityRepository getInstance() {
        if (instance == null) {
            instance = new CityRepository();
        }
        return instance;
    }

    public List<City> getCities() {
        return cities;
    }

    // Helper class for JSON deserialization
    private static class CityData {
        public String name;
        public int population;
        public int area;
        public double currentTemperature;
        public String currentWeatherState;
    }
}