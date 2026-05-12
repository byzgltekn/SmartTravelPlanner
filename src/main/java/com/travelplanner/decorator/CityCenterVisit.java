package com.travelplanner.decorator;

public class CityCenterVisit extends ActivityDecorator {
    private static final double COST = 10.0;
    private static final double TIME_HOURS = 1.0;

    public CityCenterVisit(Activity activity) {
        super(activity);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", visit city center";
    }

    @Override
    public double getCost() {
        return super.getCost() + COST;
    }

    @Override
    public double getTimeInHours() {
        return super.getTimeInHours() + TIME_HOURS;
    }
}
