package com.travelplanner.iterator;

import com.travelplanner.model.City;
import com.travelplanner.model.WeatherState;
import java.util.List;

public class CityIteratorFactory {
    public static CityIterator createIterator(List<City> cities, WeatherState weatherState) {
        return new WeatherCityIterator(cities, weatherState);
    }
}
