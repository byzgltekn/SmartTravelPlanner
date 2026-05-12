package com.travelplanner;

import com.travelplanner.ui.TravelPlannerUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private TravelPlannerUI ui;

    @Override
    public void start(Stage stage) {
        ui = new TravelPlannerUI();
        Scene scene = new Scene(ui, 1400, 800);
        
        stage.setTitle("Smart Travel Planner Platform");
        stage.setScene(scene);
        stage.setOnCloseRequest(e -> ui.shutdown());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}