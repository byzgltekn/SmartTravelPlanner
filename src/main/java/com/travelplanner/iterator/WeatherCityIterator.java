package com.travelplanner.iterator;

import com.travelplanner.model.City;
import com.travelplanner.model.WeatherState;
import java.util.Iterator;
import java.util.List;

public class WeatherCityIterator implements CityIterator {
    private final List<City> cities;
    private final WeatherState weatherState;
    private int index = 0;

    public WeatherCityIterator(List<City> cities, WeatherState weatherState) {
        this.cities = cities;
        this.weatherState = weatherState;
    }

    @Override
    public boolean hasNext() {
        while (index < cities.size()) {
            if (cities.get(index).getCurrentWeatherState() == weatherState) {
                return true;
            }
            index++;
        }
        return false;
    }

    @Override
    public City next() {
        return cities.get(index++);
    }
}
