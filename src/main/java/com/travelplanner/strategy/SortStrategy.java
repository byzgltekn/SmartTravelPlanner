package com.travelplanner.strategy;

import com.travelplanner.model.City;
import java.util.List;

public interface SortStrategy {
    void sort(List<City> cities);
}
