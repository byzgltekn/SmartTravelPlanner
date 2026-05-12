package com.travelplanner.decorator;

public class ParkVisit extends ActivityDecorator {
    private static final double COST = 5.0;
    private static final double TIME_HOURS = 1.5;

    public ParkVisit(Activity activity) {
        super(activity);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", visit park";
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
