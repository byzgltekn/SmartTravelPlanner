package com.travelplanner.composite;

import java.util.ArrayList;
import java.util.List;

public class ActivityPlan implements PlanComponent {
    private final String name;
    private final List<PlanComponent> components = new ArrayList<>();

    public ActivityPlan(String name) {
        this.name = name;
    }

    public void add(PlanComponent component) {
        components.add(component);
    }

    public void remove(PlanComponent component) {
        components.remove(component);
    }

    public List<PlanComponent> getComponents() {
        return new ArrayList<>(components);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getTotalCost() {
        return components.stream()
                .mapToDouble(PlanComponent::getTotalCost)
                .sum();
    }

    @Override
    public double getTotalTimeInHours() {
        return components.stream()
                .mapToDouble(PlanComponent::getTotalTimeInHours)
                .sum();
    }

    @Override
    public String getDescription() {
        return String.format("%s (Total Cost: $%.2f, Total Time: %.1f hrs, Items: %d)",
                name, getTotalCost(), getTotalTimeInHours(), components.size());
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + getDescription());
        for (PlanComponent component : components) {
            component.display(indent + "  ");
        }
    }
}
