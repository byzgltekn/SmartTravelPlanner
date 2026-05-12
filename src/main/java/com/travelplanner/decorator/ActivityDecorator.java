package com.travelplanner.decorator;

public abstract class ActivityDecorator implements Activity {
    protected Activity activity;

    public ActivityDecorator(Activity activity) {
        this.activity = activity;
    }

    @Override
    public String getDescription() {
        return activity.getDescription();
    }

    @Override
    public double getCost() {
        return activity.getCost();
    }

    @Override
    public double getTimeInHours() {
        return activity.getTimeInHours();
    }
}
