package com.travelplanner.command;

import com.travelplanner.composite.PlanComponent;
import java.util.List;

public class RemoveActivityCommand implements Command {
    private final List<PlanComponent> activities;
    private final PlanComponent activity;
    private int previousIndex;

    public RemoveActivityCommand(List<PlanComponent> activities, PlanComponent activity) {
        this.activities = activities;
        this.activity = activity;
    }

    @Override
    public void execute() {
        previousIndex = activities.indexOf(activity);
        activities.remove(activity);
    }

    @Override
    public void undo() {
        if (previousIndex >= 0 && previousIndex <= activities.size()) {
            activities.add(previousIndex, activity);
        } else {
            activities.add(activity);
        }
    }
}
