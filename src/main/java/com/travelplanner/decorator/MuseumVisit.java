package com.travelplanner.decorator;

public class MuseumVisit extends ActivityDecorator {
    private static final double COST = 15.0;
    private static final double TIME_HOURS = 2.0;

    public MuseumVisit(Activity activity) {
        super(activity);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", visit museum";
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
