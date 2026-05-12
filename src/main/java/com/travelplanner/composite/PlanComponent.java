package com.travelplanner.composite;

public interface PlanComponent {
    String getName();
    double getTotalCost();
    double getTotalTimeInHours();
    String getDescription();
    void display(String indent);
}
