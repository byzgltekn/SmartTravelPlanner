package com.travelplanner.command;

import com.travelplanner.model.City;
import java.util.List;

public class AddCityCommand implements Command {
    private final List<City> citiesList;
    private final City city;

    public AddCityCommand(List<City> citiesList, City city) {
        this.citiesList = citiesList;
        this.city = city;
    }

    @Override
    public void execute() {
        if (!citiesList.contains(city)) {
            citiesList.add(city);
        }
    }

    @Override
    public void undo() {
        citiesList.remove(city);
    }
}
