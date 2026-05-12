package com.travelplanner.strategy;

import com.travelplanner.model.City;
import java.util.List;

public class SortByNameStrategy implements SortStrategy {
    @Override
    public void sort(List<City> cities) {
        cities.sort((c1, c2) -> c1.getName().compareTo(c2.getName()));
    }
}
