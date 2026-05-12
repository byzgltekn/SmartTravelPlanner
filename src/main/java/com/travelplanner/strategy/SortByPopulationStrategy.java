package com.travelplanner.strategy;

import com.travelplanner.model.City;
import java.util.List;

public class SortByPopulationStrategy implements SortStrategy {
    @Override
    public void sort(List<City> cities) {
        cities.sort((c1, c2) -> Integer.compare(c2.getPopulation(), c1.getPopulation()));
    }
}
