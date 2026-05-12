package com.travelplanner.decorator;

public class ShoppingMallVisit extends ActivityDecorator {
    private static final double COST = 20.0;
    private static final double TIME_HOURS = 3.0;

    public ShoppingMallVisit(Activity activity) {
        super(activity);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", visit shopping mall";
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
