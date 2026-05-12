package com.travelplanner.repository;

import com.travelplanner.model.City;
import com.travelplanner.model.WeatherState;

import java.util.ArrayList;
import java.util.List;

public class CityRepository {

    private static CityRepository instance;
    private final List<City> cities;

    private CityRepository() {
        cities = new ArrayList<>();

        cities.add(new City("Ankara", 5780000, 24521, 18, WeatherState.SUNNY));
        cities.add(new City("Istanbul", 15800000, 5461, 21, WeatherState.CLOUDY));
        cities.add(new City("Izmir", 4460000, 11891, 24, WeatherState.SUNNY));
        cities.add(new City("Bursa", 3200000, 10813, 19, WeatherState.RAINY));
        cities.add(new City("Antalya", 2700000, 20723, 27, WeatherState.SUNNY));
        cities.add(new City("Erzurum", 750000, 25066, 5, WeatherState.SNOWY));
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
}