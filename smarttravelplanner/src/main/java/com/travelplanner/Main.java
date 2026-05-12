package com.travelplanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        Label label = new Label("Smart Travel Planner");

        StackPane root = new StackPane();
        root.getChildren().add(label);

        Scene scene = new Scene(root, 600, 400);

        stage.setTitle("Travel Planner");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}