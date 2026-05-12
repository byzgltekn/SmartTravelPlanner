package com.travelplanner.command;

import com.travelplanner.composite.PlanComponent;
import java.util.List;

public class MoveActivityCommand implements Command {
    private final List<PlanComponent> activities;
    private final PlanComponent activity;
    private final int fromIndex;
    private final int toIndex;

    public MoveActivityCommand(List<PlanComponent> activities, PlanComponent activity, int fromIndex, int toIndex) {
        this.activities = activities;
        this.activity = activity;
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
    }

    @Override
    public void execute() {
        if (fromIndex >= 0 && fromIndex < activities.size() &&
            toIndex >= 0 && toIndex < activities.size()) {
            activities.remove(fromIndex);
            activities.add(toIndex, activity);
        }
    }

    @Override
    public void undo() {
        if (toIndex >= 0 && toIndex < activities.size()) {
            activities.remove(toIndex);
            activities.add(fromIndex, activity);
        }
    }
}
