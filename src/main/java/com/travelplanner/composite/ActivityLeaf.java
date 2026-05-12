package com.travelplanner.composite;

public class ActivityLeaf implements PlanComponent {
    private final String name;
    private final double cost;
    private final double timeInHours;

    public ActivityLeaf(String name, double cost, double timeInHours) {
        this.name = name;
        this.cost = cost;
        this.timeInHours = timeInHours;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getTotalCost() {
        return cost;
    }

    @Override
    public double getTotalTimeInHours() {
        return timeInHours;
    }

    @Override
    public String getDescription() {
        return String.format("%s (Cost: $%.2f, Time: %.1f hrs)", name, cost, timeInHours);
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + getDescription());
    }
}
