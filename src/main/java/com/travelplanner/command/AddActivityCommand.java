package com.travelplanner.command;

import com.travelplanner.composite.PlanComponent;
import java.util.List;

public class AddActivityCommand implements Command {
    private final List<PlanComponent> activities;
    private final PlanComponent activity;

    public AddActivityCommand(List<PlanComponent> activities, PlanComponent activity) {
        this.activities = activities;
        this.activity = activity;
    }

    @Override
    public void execute() {
        if (!activities.contains(activity)) {
            activities.add(activity);
        }
    }

    @Override
    public void undo() {
        activities.remove(activity);
    }
}
