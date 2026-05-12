package com.travelplanner.command;

import com.travelplanner.model.City;
import java.util.List;

public class RemoveCityCommand implements Command {
    private final List<City> citiesList;
    private final City city;
    private int previousIndex;

    public RemoveCityCommand(List<City> citiesList, City city) {
        this.citiesList = citiesList;
        this.city = city;
    }

    @Override
    public void execute() {
        previousIndex = citiesList.indexOf(city);
        citiesList.remove(city);
    }

    @Override
    public void undo() {
        if (previousIndex >= 0 && previousIndex <= citiesList.size()) {
            citiesList.add(previousIndex, city);
        } else {
            citiesList.add(city);
        }
    }
}
