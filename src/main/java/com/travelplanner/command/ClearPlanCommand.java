package com.travelplanner.command;

import com.travelplanner.composite.ActivityPlan;
import java.util.HashMap;
import java.util.Map;

public class ClearPlanCommand implements Command {
    private final Map<ActivityPlan, java.util.List<com.travelplanner.composite.PlanComponent>> backup;
    private final ActivityPlan rootPlan;

    public ClearPlanCommand(ActivityPlan rootPlan) {
        this.rootPlan = rootPlan;
        this.backup = new HashMap<>();
    }

    @Override
    public void execute() {
        backup.put(rootPlan, new java.util.ArrayList<>(rootPlan.getComponents()));
        rootPlan.getComponents().clear();
    }

    @Override
    public void undo() {
        java.util.List<com.travelplanner.composite.PlanComponent> components = backup.get(rootPlan);
        if (components != null) {
            rootPlan.getComponents().clear();
            rootPlan.getComponents().addAll(components);
        }
    }
}
