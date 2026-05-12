package com.travelplanner.observer;

import com.travelplanner.model.City;
import com.travelplanner.model.WeatherState;
import com.travelplanner.repository.CityRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WeatherReportProvider implements Runnable {
    private static WeatherReportProvider instance;
    private final List<WeatherObserver> observers = new ArrayList<>();
    private volatile boolean running = false;
    private Thread updateThread;
    private final Random random = new Random();

    private WeatherReportProvider() {
    }

    public static synchronized WeatherReportProvider getInstance() {
        if (instance == null) {
            instance = new WeatherReportProvider();
        }
        return instance;
    }

    public void subscribe(WeatherObserver observer) {
        observers.add(observer);
    }

    public void unsubscribe(WeatherObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (WeatherObserver observer : observers) {
            observer.update();
        }
    }

    public void startWeatherUpdates() {
        if (!running) {
            running = true;
            updateThread = new Thread(this);
            updateThread.setDaemon(true);
            updateThread.start();
        }
    }

    public void stopWeatherUpdates() {
        running = false;
        if (updateThread != null) {
            try {
                updateThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(3000); // Update every 3 seconds
                updateWeatherData();
                notifyObservers();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private void updateWeatherData() {
        List<City> cities = CityRepository.getInstance().getCities();
        WeatherState[] states = WeatherState.values();

        for (City city : cities) {
            // Random weather update
            WeatherState newState = states[random.nextInt(states.length)];
            city.setCurrentWeatherState(newState);

            // Random temperature update (between -10 and 35 degrees)
            double newTemp = -10 + (random.nextDouble() * 45);
            city.setCurrentTemperature(newTemp);
        }
    }
}
