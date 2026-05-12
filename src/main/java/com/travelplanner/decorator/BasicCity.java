package com.travelplanner.decorator;

public class BasicCity implements Activity {
    private final String cityName;

    public BasicCity(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String getDescription() {
        return "Visit " + cityName;
    }

    @Override
    public double getCost() {
        return 0;
    }

    @Override
    public double getTimeInHours() {
        return 0;
    }
}
