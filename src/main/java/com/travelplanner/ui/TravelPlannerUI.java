package com.travelplanner.ui;

import com.travelplanner.command.*;
import com.travelplanner.composite.ActivityLeaf;
import com.travelplanner.composite.ActivityPlan;
import com.travelplanner.composite.PlanComponent;
import com.travelplanner.decorator.*;
import com.travelplanner.iterator.CityIteratorFactory;
import com.travelplanner.model.City;
import com.travelplanner.model.WeatherState;
import com.travelplanner.observer.WeatherObserver;
import com.travelplanner.observer.WeatherReportProvider;
import com.travelplanner.repository.CityRepository;
import com.travelplanner.strategy.*;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.*;

public class TravelPlannerUI extends BorderPane implements WeatherObserver {

    private final CityRepository cityRepository;
    private final CommandHistory commandHistory;
    private final WeatherReportProvider weatherProvider;

    // UI Components
    private ListView<City> allCitiesView;
    private ListView<City> filteredCitiesView;
    private ComboBox<String> sortComboBox;
    private ComboBox<WeatherState> weatherComboBox;
    private ListView<String> planTreeView;
    private Canvas temperatureChart;
    private Canvas weatherDistributionChart;
    private Button undoButton;
    private Button redoButton;
    
    // Activity Checkboxes (for UI synchronization)
    private CheckBox museumCheckBox;
    private CheckBox mallCheckBox;
    private CheckBox parkCheckBox;
    private CheckBox centerCheckBox;

    // Data
    private Map<City, ActivityPlan> cityPlans;
    private ActivityPlan currentRootPlan;
    private City selectedCity;
    private List<City> currentCityList;

    public TravelPlannerUI() {
        this.cityRepository = CityRepository.getInstance();
        this.commandHistory = new CommandHistory();
        this.weatherProvider = WeatherReportProvider.getInstance();
        this.cityPlans = new HashMap<>();
        this.currentCityList = new ArrayList<>(cityRepository.getCities());

        setupUI();
        setupWeatherUpdates();
    }

    private void setupUI() {
        setPadding(new Insets(10));

        // Top: Controls
        HBox topControl = createControlPanel();
        setTop(topControl);

        // Center: Main content (split view)
        HBox centerContent = createCenterContent();
        setCenter(centerContent);

        // Bottom: Undo/Redo buttons
        HBox bottomControl = createBottomPanel();
        setBottom(bottomControl);
    }

    private HBox createControlPanel() {
        HBox control = new HBox(10);
        control.setPadding(new Insets(10));
        control.setStyle("-fx-border-color: #cccccc; -fx-border-radius: 5");

        Label sortLabel = new Label("Sort by:");
        sortComboBox = new ComboBox<>();
        sortComboBox.getItems().addAll("Name", "Population", "Area");
        sortComboBox.setValue("Name");
        sortComboBox.setOnAction(e -> applySorting());

        Label weatherLabel = new Label("Filter by Weather:");
        weatherComboBox = new ComboBox<>();
        weatherComboBox.getItems().addAll(WeatherState.values());
        weatherComboBox.setValue(WeatherState.SUNNY);
        weatherComboBox.setOnAction(e -> applyWeatherFilter());

        Button clearPlanBtn = new Button("Clear Plan");
        clearPlanBtn.setOnAction(e -> clearPlan());

        control.getChildren().addAll(
                sortLabel, sortComboBox,
                new Separator(), // Separator
                weatherLabel, weatherComboBox,
                new Separator(),
                clearPlanBtn
        );

        return control;
    }

    private HBox createCenterContent() {
        HBox center = new HBox(10);
        center.setPadding(new Insets(10));

        // Left: City lists
        VBox leftPanel = createCityPanel();

        // Middle: Activity planner
        VBox middlePanel = createActivityPlannerPanel();

        // Right: Charts
        VBox rightPanel = createChartsPanel();

        center.getChildren().addAll(
                leftPanel,
                new Separator(),
                middlePanel,
                new Separator(),
                rightPanel
        );

        HBox.setHgrow(leftPanel, javafx.scene.layout.Priority.ALWAYS);
        HBox.setHgrow(middlePanel, javafx.scene.layout.Priority.ALWAYS);
        HBox.setHgrow(rightPanel, javafx.scene.layout.Priority.ALWAYS);

        return center;
    }

    private VBox createCityPanel() {
        VBox vbox = new VBox(5);
        vbox.setStyle("-fx-border-color: #cccccc; -fx-border-radius: 5; -fx-padding: 5");

        Label allCitiesLabel = new Label("All Cities");
        allCitiesLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 12");

        allCitiesView = new ListView<>();
        allCitiesView.setPrefHeight(150);
        allCitiesView.getItems().addAll(currentCityList);
        allCitiesView.setOnMouseClicked(e -> selectCity());

        Label filteredLabel = new Label("Filtered by Weather");
        filteredLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 12");

        filteredCitiesView = new ListView<>();
        filteredCitiesView.setPrefHeight(150);
        filteredCitiesView.setOnMouseClicked(e -> selectCity());

        vbox.getChildren().addAll(
                allCitiesLabel, allCitiesView,
                new Separator(),
                filteredLabel, filteredCitiesView
        );

        VBox.setVgrow(allCitiesView, javafx.scene.layout.Priority.ALWAYS);
        VBox.setVgrow(filteredCitiesView, javafx.scene.layout.Priority.ALWAYS);

        return vbox;
    }

    private VBox createActivityPlannerPanel() {
        VBox vbox = new VBox(5);
        vbox.setStyle("-fx-border-color: #cccccc; -fx-border-radius: 5; -fx-padding: 5");

        Label planLabel = new Label("Travel Plan");
        planLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 12");

        planTreeView = new ListView<>();
        planTreeView.setPrefHeight(250);
        updatePlanDisplay();

        GridPane activityGrid = new GridPane();
        activityGrid.setHgap(5);
        activityGrid.setVgap(5);

        museumCheckBox = new CheckBox("Museum ($15, 2h)");
        museumCheckBox.setOnAction(e -> {
            if (selectedCity != null) {
                addActivity("Museum", 15, 2, museumCheckBox.isSelected());
            }
        });

        mallCheckBox = new CheckBox("Shopping Mall ($20, 3h)");
        mallCheckBox.setOnAction(e -> {
            if (selectedCity != null) {
                addActivity("Shopping Mall", 20, 3, mallCheckBox.isSelected());
            }
        });

        parkCheckBox = new CheckBox("Park ($5, 1.5h)");
        parkCheckBox.setOnAction(e -> {
            if (selectedCity != null) {
                addActivity("Park", 5, 1.5, parkCheckBox.isSelected());
            }
        });

        centerCheckBox = new CheckBox("City Center ($10, 1h)");
        centerCheckBox.setOnAction(e -> {
            if (selectedCity != null) {
                addActivity("City Center", 10, 1, centerCheckBox.isSelected());
            }
        });

        activityGrid.add(museumCheckBox, 0, 0);
        activityGrid.add(mallCheckBox, 0, 1);
        activityGrid.add(parkCheckBox, 1, 0);
        activityGrid.add(centerCheckBox, 1, 1);

        VBox.setVgrow(planTreeView, javafx.scene.layout.Priority.ALWAYS);

        vbox.getChildren().addAll(
                planLabel, planTreeView,
                new Separator(),
                new Label("Add Activities:"),
                activityGrid
        );

        return vbox;
    }

    private VBox createChartsPanel() {
        VBox vbox = new VBox(5);
        vbox.setStyle("-fx-border-color: #cccccc; -fx-border-radius: 5; -fx-padding: 5");

        // Temperature Bar Chart
        Label tempLabel = new Label("City Temperatures");
        tempLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 12");

        temperatureChart = new Canvas(300, 200);
        updateTemperatureChart();

        // Weather Distribution Pie Chart
        Label weatherLabel = new Label("Weather Distribution");
        weatherLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 12");

        weatherDistributionChart = new Canvas(300, 200);
        updateWeatherChart();

        vbox.getChildren().addAll(
                tempLabel, temperatureChart,
                new Separator(),
                weatherLabel, weatherDistributionChart
        );

        VBox.setVgrow(temperatureChart, javafx.scene.layout.Priority.ALWAYS);
        VBox.setVgrow(weatherDistributionChart, javafx.scene.layout.Priority.ALWAYS);

        return vbox;
    }

    private HBox createBottomPanel() {
        HBox hbox = new HBox(10);
        hbox.setPadding(new Insets(10));
        hbox.setStyle("-fx-border-color: #cccccc; -fx-border-radius: 5");

        undoButton = new Button("Undo");
        undoButton.setDisable(true);
        undoButton.setOnAction(e -> {
            commandHistory.undo();
            updateUI();
        });

        redoButton = new Button("Redo");
        redoButton.setDisable(true);
        redoButton.setOnAction(e -> {
            commandHistory.redo();
            updateUI();
        });

        Label statusLabel = new Label("Ready");

        hbox.getChildren().addAll(undoButton, redoButton, new Separator(), statusLabel);
        HBox.setHgrow(statusLabel, javafx.scene.layout.Priority.ALWAYS);

        return hbox;
    }

    private void applySorting() {
        String sortOption = sortComboBox.getValue();
        SortStrategy strategy = switch (sortOption) {
            case "Name" -> new SortByNameStrategy();
            case "Population" -> new SortByPopulationStrategy();
            case "Area" -> new SortByAreaStrategy();
            default -> new SortByNameStrategy();
        };

        List<City> sortedCities = new ArrayList<>(currentCityList);
        strategy.sort(sortedCities);

        allCitiesView.getItems().clear();
        allCitiesView.getItems().addAll(sortedCities);
    }

    private void applyWeatherFilter() {
        WeatherState weather = weatherComboBox.getValue();
        List<City> filtered = new ArrayList<>();

        var iterator = CityIteratorFactory.createIterator(currentCityList, weather);
        while (iterator.hasNext()) {
            filtered.add(iterator.next());
        }

        filteredCitiesView.getItems().clear();
        filteredCitiesView.getItems().addAll(filtered);
    }

    private void selectCity() {
        City city = allCitiesView.getSelectionModel().getSelectedItem();
        if (city != null) {
            selectedCity = city;
            if (!cityPlans.containsKey(city)) {
                currentRootPlan = new ActivityPlan("Plan for " + city.getName());
                cityPlans.put(city, currentRootPlan);
            } else {
                currentRootPlan = cityPlans.get(city);
            }
            updatePlanDisplay();
            synchronizeActivityCheckboxes();
        }
    }

    private void addActivity(String name, double cost, double duration, boolean add) {
        if (selectedCity == null || currentRootPlan == null) return;

        if (add) {
            ActivityLeaf leaf = new ActivityLeaf(name, cost, duration);
            AddActivityCommand command = new AddActivityCommand(currentRootPlan.getComponents(), leaf);
            commandHistory.execute(command);
            System.out.println("✓ Added: " + name + " ($" + cost + ", " + duration + "h) | Total items: " + currentRootPlan.getComponents().size());
        } else {
            // Removing activity
            currentRootPlan.getComponents().removeIf(c -> c.getName().equals(name));
            System.out.println("✗ Removed: " + name + " | Total items: " + currentRootPlan.getComponents().size());
        }
        updateUI();
    }

    private void clearPlan() {
        if (currentRootPlan != null) {
            ClearPlanCommand command = new ClearPlanCommand(currentRootPlan);
            commandHistory.execute(command);
            updateUI();
        }
    }

    private void updatePlanDisplay() {
        planTreeView.getItems().clear();
        if (currentRootPlan != null) {
            planTreeView.getItems().add(currentRootPlan.getDescription());
            for (PlanComponent component : currentRootPlan.getComponents()) {
                planTreeView.getItems().add("  " + component.getDescription());
            }
        }
    }

    private void updateTemperatureChart() {
        GraphicsContext gc = temperatureChart.getGraphicsContext2D();
        gc.clearRect(0, 0, temperatureChart.getWidth(), temperatureChart.getHeight());

        if (currentCityList.isEmpty()) return;

        double width = temperatureChart.getWidth();
        double height = temperatureChart.getHeight();
        double padding = 30;
        double chartWidth = width - 2 * padding;
        double chartHeight = height - 2 * padding;

        // Find temperature range
        double minTemp = Double.MAX_VALUE;
        double maxTemp = Double.MIN_VALUE;
        for (City city : currentCityList) {
            minTemp = Math.min(minTemp, city.getCurrentTemperature());
            maxTemp = Math.max(maxTemp, city.getCurrentTemperature());
        }

        double tempRange = maxTemp - minTemp;
        if (tempRange == 0) tempRange = 1;

        // Draw bars
        double barWidth = chartWidth / currentCityList.size();
        for (int i = 0; i < currentCityList.size(); i++) {
            City city = currentCityList.get(i);
            double normalizedTemp = (city.getCurrentTemperature() - minTemp) / tempRange;
            double barHeight = normalizedTemp * chartHeight;

            double x = padding + i * barWidth + 5;
            double y = height - padding - barHeight;

            gc.setFill(Color.web("#3498db"));
            gc.fillRect(x, y, barWidth - 10, barHeight);

            // Draw rotated city names
            gc.save();
            double textX = padding + i * barWidth + barWidth / 2;
            double textY = height - padding + 5;
            gc.translate(textX, textY);
            gc.rotate(45);
            gc.setFill(Color.BLACK);
            gc.setFont(javafx.scene.text.Font.font(9));
            gc.fillText(city.getName(), 0, 0);
            gc.restore();
        }

        // Draw axes
        gc.setStroke(Color.BLACK);
        gc.strokeLine(padding, height - padding, width - padding, height - padding);
        gc.strokeLine(padding, padding, padding, height - padding);
    }

    private void updateWeatherChart() {
        GraphicsContext gc = weatherDistributionChart.getGraphicsContext2D();
        gc.clearRect(0, 0, weatherDistributionChart.getWidth(), weatherDistributionChart.getHeight());

        Map<WeatherState, Integer> weatherCounts = new HashMap<>();
        for (WeatherState state : WeatherState.values()) {
            weatherCounts.put(state, 0);
        }

        for (City city : currentCityList) {
            weatherCounts.put(city.getCurrentWeatherState(),
                    weatherCounts.get(city.getCurrentWeatherState()) + 1);
        }

        double totalWidth = weatherDistributionChart.getWidth();
        double totalHeight = weatherDistributionChart.getHeight();
        double centerX = totalWidth / 2;
        double centerY = totalHeight / 2;
        double radius = Math.min(totalWidth, totalHeight) / 2 - 20;

        int total = currentCityList.size();
        double currentAngle = 0;

        Color[] colors = {Color.web("#FF6B6B"), Color.web("#4ECDC4"), Color.web("#45B7D1"), Color.web("#FFA07A")};
        int colorIndex = 0;

        for (WeatherState state : WeatherState.values()) {
            int count = weatherCounts.get(state);
            double percentage = (count * 100.0) / total;
            double angle = (count * 360.0) / total;

            gc.setFill(colors[colorIndex % colors.length]);
            gc.fillArc(centerX - radius, centerY - radius, 2 * radius, 2 * radius, currentAngle, angle, javafx.scene.shape.ArcType.ROUND);

            // Draw label
            double labelAngle = currentAngle + angle / 2;
            double labelX = centerX + (radius + 30) * Math.cos(Math.toRadians(labelAngle));
            double labelY = centerY + (radius + 30) * Math.sin(Math.toRadians(labelAngle));
            gc.setFill(Color.BLACK);
            gc.fillText(state + " (" + count + ")", labelX - 20, labelY);

            currentAngle += angle;
            colorIndex++;
        }
    }

    private void setupWeatherUpdates() {
        weatherProvider.subscribe(this);
        weatherProvider.startWeatherUpdates();
    }

    @Override
    public void update() {
        Platform.runLater(this::updateUI);
    }

    private void updateUI() {
        synchronizeActivityCheckboxes();
        updateTemperatureChart();
        updateWeatherChart();
        updatePlanDisplay();
        undoButton.setDisable(!commandHistory.canUndo());
        redoButton.setDisable(!commandHistory.canRedo());
        applyWeatherFilter();
    }
    
    private void synchronizeActivityCheckboxes() {
        if (currentRootPlan == null) {
            // No plan selected, uncheck all
            museumCheckBox.setSelected(false);
            mallCheckBox.setSelected(false);
            parkCheckBox.setSelected(false);
            centerCheckBox.setSelected(false);
            return;
        }
        
        // Get activity names from current plan
        List<String> planActivities = new ArrayList<>();
        for (PlanComponent component : currentRootPlan.getComponents()) {
            planActivities.add(component.getName());
        }
        
        // Temporarily disable listeners to prevent triggering addActivity
        museumCheckBox.setOnAction(null);
        mallCheckBox.setOnAction(null);
        parkCheckBox.setOnAction(null);
        centerCheckBox.setOnAction(null);
        
        // Synchronize checkbox states with plan
        museumCheckBox.setSelected(planActivities.contains("Museum"));
        mallCheckBox.setSelected(planActivities.contains("Shopping Mall"));
        parkCheckBox.setSelected(planActivities.contains("Park"));
        centerCheckBox.setSelected(planActivities.contains("City Center"));
        
        // Re-enable listeners
        museumCheckBox.setOnAction(e -> {
            if (selectedCity != null) {
                addActivity("Museum", 15, 2, museumCheckBox.isSelected());
            }
        });
        mallCheckBox.setOnAction(e -> {
            if (selectedCity != null) {
                addActivity("Shopping Mall", 20, 3, mallCheckBox.isSelected());
            }
        });
        parkCheckBox.setOnAction(e -> {
            if (selectedCity != null) {
                addActivity("Park", 5, 1.5, parkCheckBox.isSelected());
            }
        });
        centerCheckBox.setOnAction(e -> {
            if (selectedCity != null) {
                addActivity("City Center", 10, 1, centerCheckBox.isSelected());
            }
        });
    }

    public void shutdown() {
        weatherProvider.stopWeatherUpdates();
        weatherProvider.unsubscribe(this);
    }
}
