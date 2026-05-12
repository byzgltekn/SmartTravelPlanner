package com.travelplanner.strategy;

import com.travelplanner.model.City;
import java.util.List;

public class SortByAreaStrategy implements SortStrategy {
    @Override
    public void sort(List<City> cities) {
        cities.sort((c1, c2) -> Double.compare(c2.getArea(), c1.getArea()));
    }
}
