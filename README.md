# Smart Travel Planner Platform

## Project Description

This project was developed for the **SENG 324 - Software Design Patterns** course.
The application allows users to create travel plans between cities, manage activities, and track real-time weather conditions.

## 🎯 Project Objective

The main objective of this project is to demonstrate how software design patterns can be applied in a real-world desktop application.

---

## 📋 Implemented Design Patterns

### 1. Singleton Pattern

Used in `CityRepository` to maintain a single shared city data source.

### 2. Strategy Pattern

Used for sorting cities by:

* Name
* Population
* Area

### 3. Iterator Pattern

Used to filter cities according to weather conditions:

* Sunny
* Cloudy
* Rainy
* Snowy

### 4. Observer Pattern

Used for real-time weather updates every 3 seconds.

### 5. Decorator Pattern

Used for dynamically adding travel activities:

* Museum Visit
* Shopping Mall Visit
* Park Visit
* City Center Visit

### 6. Composite Pattern

Used to create hierarchical travel plans and activity structures.

### 7. Command Pattern

Used for:

* Undo operations
* Redo operations

---

## 🖥️ GUI Features

* ✅ City list and sorting controls
* ✅ Weather-based filtering
* ✅ Activity planner
* ✅ Travel plan visualization
* ✅ Temperature bar chart
* ✅ Weather distribution pie chart
* ✅ Undo / Redo functionality

---

## 📦 Technologies Used

* **Java 17**
* **JavaFX**
* **Maven**
* **Object-Oriented Programming**
* **Software Design Patterns**

---

## 🚀 Installation & Running

### Requirements

* Java 17+
* Maven 3.8+

### Run the Application

```bash
cd smarttravelplanner
mvn clean javafx:run
```

### Build JAR File

```bash
mvn clean package
```

---

## 📂 Project Structure

```text
src/main/java/com/travelplanner/
├── model/          (City, WeatherState)
├── repository/     (CityRepository - Singleton)
├── strategy/       (Sorting strategies)
├── iterator/       (Weather filtering iterators)
├── observer/       (Weather provider & observer)
├── decorator/      (Activity decorators)
├── composite/      (Activity plan hierarchy)
├── command/        (Command pattern implementation)
└── ui/             (TravelPlannerUI - Main GUI)
```

---

## 👥 Team Members

* **Beyza Gültekin**
* **Şeyma Aslan**

---

## ✨ Application Features

* 🔄 Real-time weather updates
* 💾 Undo / Redo support
* 📊 Dynamic charts and statistics
* 🌍 Multi-city travel planning
* 🎯 Hierarchical activity management

---

## 📝 Academic Information

This project was developed for academic purposes to demonstrate the implementation of fundamental software design patterns in Java.

**Course:** SENG 324 - Software Design Patterns
**University:** Ankara Science University
**Term:** 2025-2026 Spring Semester
