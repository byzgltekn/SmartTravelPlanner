# Smart Travel Planner Platform

![Java](https://img.shields.io/badge/Java-17-blue)
![Maven](https://img.shields.io/badge/Maven-3.8+-blue)
![JavaFX](https://img.shields.io/badge/JavaFX-17.0.2-blue)
![Status](https://img.shields.io/badge/Status-Complete-green)

## 📌 Overview

**Smart Travel Planner Platform** is a comprehensive desktop application built in Java that enables users to plan, organize, and manage trips across multiple cities with real-time weather monitoring and dynamic activity planning. The application demonstrates the practical implementation of **seven key software design patterns** in a cohesive, production-ready GUI application.



## Project Description

This project was developed for the **SENG 324 - Software Design Patterns** course.
The application allows users to create travel plans between cities, manage activities, and track real-time weather conditions.

## 🎯 Project Objective

The main objective of this project is to demonstrate how software design patterns can be applied in a real-world desktop application.

---

## 📋 Implemented Design Patterns

All seven required design patterns from SENG 324 are meaningfully implemented:

### 1. **Singleton Pattern**
- **Class:** `CityRepository`
- **Purpose:** Ensures a single, shared instance of the city database
- **Implementation:** Thread-safe lazy initialization; loads cities from JSON file once
- **Location:** `src/main/java/com/travelplanner/repository/`

### 2. **Strategy Pattern**
- **Interface:** `SortStrategy`
- **Implementations:** 
  - `SortByNameStrategy`
  - `SortByPopulationStrategy`
  - `SortByAreaStrategy`
- **Purpose:** Enables dynamic sorting of cities based on user selection
- **Location:** `src/main/java/com/travelplanner/strategy/`

### 3. **Iterator Pattern**
- **Interface:** `CityIterator`
- **Factory:** `CityIteratorFactory`
- **Implementations:**
  - `WeatherCityIterator` (for SUNNY, CLOUDY, RAINY, SNOWY)
- **Purpose:** Filters cities by weather conditions dynamically
- **Location:** `src/main/java/com/travelplanner/iterator/`

### 4. **Observer Pattern**
- **Subject:** `WeatherReportProvider` (Singleton)
- **Observer:** `WeatherObserver`
- **Implementation:** `TravelPlannerUI`
- **Purpose:** Real-time weather updates every 3 seconds; updates charts dynamically
- **Location:** `src/main/java/com/travelplanner/observer/`

### 5. **Decorator Pattern**
- **Component:** `Activity`
- **Decorators:**
  - `BasicCity` (base component)
  - `MuseumVisit` ($15, 2 hours)
  - `ShoppingMallVisit` ($20, 3 hours)
  - `ParkVisit` ($5, 1.5 hours)
  - `CityCenterVisit` ($10, 1 hour)
- **Purpose:** Dynamically add activity features to cities without modifying the City class
- **Location:** `src/main/java/com/travelplanner/decorator/`

### 6. **Composite Pattern**
- **Component:** `PlanComponent`
- **Composite:** `ActivityPlan`
- **Leaf:** `ActivityLeaf`
- **Purpose:** Create hierarchical travel plans with recursive structure
- **Features:**
  - Add/remove activities and sub-plans
  - Calculate total cost and time recursively
  - Display tree structure
- **Location:** `src/main/java/com/travelplanner/composite/`

### 7. **Command Pattern**
- **Interface:** `Command`
- **Implementations:**
  - `AddActivityCommand`
  - `RemoveActivityCommand`
  - `AddCityCommand`
  - `RemoveCityCommand`
  - `MoveActivityCommand`
  - `ClearPlanCommand`
- **Manager:** `CommandHistory`
- **Purpose:** Enable undo/redo functionality and decouple GUI from business logic
- **Location:** `src/main/java/com/travelplanner/command/`

---

## 🖥️ GUI Features & Components

The application provides a comprehensive, integrated interface with the following features:

### **Control Panel**
- **Sort by:** Dropdown selector for sorting strategy (Name, Population, Area)
- **Filter by Weather:** Dropdown selector for weather filtering (SUNNY, CLOUDY, RAINY, SNOWY)
- **Clear Plan:** Button to reset the travel plan

### **City Lists**
- **All Cities:** Complete list of 12 Turkish cities with full details
- **Filtered by Weather:** Dynamic list updated based on selected weather condition
- City information includes: Name, Population, Area, Temperature, Weather State

### **Charts & Visualizations**
- **Temperature Bar Chart:** Real-time visualization of all cities' temperatures (°C)
- **Weather Distribution Pie Chart:** Percentage breakdown of cities by weather condition
- Charts update automatically every 3 seconds via Observer pattern

### **Activity Planning**
- **Checkboxes:** Add activities to selected cities
  - Museum Visit ($15, 2h)
  - Shopping Mall ($20, 3h)
  - Park Visit ($5, 1.5h)
  - City Center ($10, 1h)

### **Travel Plan Panel**
- Hierarchical display of planned trips
- Shows all selected activities and their properties
- Total cost and time calculations

### **Command Controls**
- **Undo Button:** Reverse the last action
- **Redo Button:** Redo a previously undone action
- **Status Bar:** Shows current action status

---

## 📦 Technologies & Dependencies

| Technology | Version | Purpose |
|---|---|---|
| **Java** | 17+ | Core language |
| **JavaFX** | 17.0.2 | GUI framework |
| **Maven** | 3.8+ | Build & dependency management |
| **Gson** | 2.10.1 | JSON parsing |
| **Maven Shade Plugin** | 3.4.1 | Fat JAR packaging |

### Core Dependencies (from pom.xml)
- `javafx-controls` - UI controls
- `javafx-fxml` - FXML support
- `javafx-graphics` - Graphics rendering
- `javafx-swing` - Swing integration
- `gson` - JSON serialization/deserialization

---

## 🚀 Installation & Execution Guide

### System Requirements
- **Operating System:** Windows, macOS, Linux
- **Java:** JDK 17 or higher (OpenJDK 25+ recommended)
- **Maven:** 3.8 or higher
- **RAM:** Minimum 512MB, recommended 1GB+
- **Display:** Any screen with 1280x800 resolution or higher

### Prerequisites Installation

#### 1. Install Java 17+
```bash
# Verify installation
java -version
# Should output: openjdk version "17" or higher
```

#### 2. Install Maven
```bash
# Verify installation
mvn -version
# Should output: Apache Maven 3.8+
```

### Quick Start

#### **Option 1: Run with Maven (Development)**
```bash
cd C:\Projects\SmartTravelPlanner
mvn clean javafx:run
```
✅ Direct execution - No JAR creation needed
⏱️ Takes 10-20 seconds to start

#### **Option 2: Build & Run FAT JAR (Production)**
```bash
# Step 1: Build the project
cd C:\Projects\SmartTravelPlanner
mvn clean package

# Step 2: Run the JAR file
java -jar target/smarttravelplanner.jar
```
✅ Standalone executable
✅ Can be distributed as single file
⏱️ Build takes ~30 seconds, execution is immediate

#### **Option 3: Compile Only**
```bash
mvn clean compile
```

### Available Maven Commands

| Command | Purpose |
|---|---|
| `mvn clean compile` | Compile source code |
| `mvn clean javafx:run` | Run application directly |
| `mvn clean package` | Build Fat JAR (all dependencies included) |
| `mvn test` | Run unit tests |
| `mvn clean` | Remove target directory |

### Required Files

Ensure the following file is in the project root directory:
```
C:\Projects\SmartTravelPlanner\cities.json
```
This file contains the city data (12 Turkish cities) and is loaded by the Singleton repository.

---

## � User Guide

### How to Use the Application

#### 1. **Browse Cities**
- All 12 cities are displayed in the "All Cities" list
- Each city shows: Name, Population, Area, Temperature, Weather State

#### 2. **Sort Cities**
- Use the "Sort by:" dropdown to sort by:
  - **Name:** Alphabetical order
  - **Population:** Highest to lowest
  - **Area:** Largest to smallest
- The list updates immediately

#### 3. **Filter by Weather**
- Select a weather condition from "Filter by Weather:" dropdown
  - **SUNNY, CLOUDY, RAINY, SNOWY**
- The filtered list shows only cities with that weather condition
- Updates in real-time every 3 seconds as weather changes

#### 4. **Monitor Weather**
- **Temperature Chart:** Bar chart shows real-time temperatures
- **Weather Distribution:** Pie chart shows percentage of cities with each weather condition
- Both charts update automatically every 3 seconds

#### 5. **Plan Activities**
- Select a city from the sorted list
- Check the activities you want to add:
  - Museum Visit ($15, 2 hours)
  - Shopping Mall ($20, 3 hours)
  - Park Visit ($5, 1.5 hours)
  - City Center ($10, 1 hour)
- Selected activities are added to the Travel Plan

#### 6. **Manage Your Plan**
- **View Plan:** Hierarchical structure shown in Travel Plan panel
- **Total Cost:** Automatically calculated
- **Total Time:** Automatically calculated
- **Clear Plan:** Click "Clear Plan" to reset

#### 7. **Undo/Redo**
- **Undo:** Reverse the last action
- **Redo:** Restore the last undone action
- Status shows current operation

---

## 📂 Project Structure

```
SmartTravelPlanner/
├── pom.xml                           # Maven configuration
├── cities.json                       # City data (12 cities)
├── README.md                         # This file
├── src/main/java/com/travelplanner/
│   ├── Main.java                     # Application entry point
│   ├── model/
│   │   ├── City.java                 # City entity
│   │   └── WeatherState.java         # Weather enumeration
│   ├── repository/
│   │   └── CityRepository.java       # Singleton pattern
│   ├── strategy/
│   │   ├── SortStrategy.java         # Strategy interface
│   │   ├── SortByNameStrategy.java
│   │   ├── SortByPopulationStrategy.java
│   │   └── SortByAreaStrategy.java
│   ├── iterator/
│   │   ├── CityIterator.java         # Iterator interface
│   │   ├── CityIteratorFactory.java
│   │   └── WeatherCityIterator.java
│   ├── observer/
│   │   ├── WeatherObserver.java      # Observer interface
│   │   ├── WeatherReportProvider.java # Subject (Singleton)
│   │   └── WeatherState.java
│   ├── decorator/
│   │   ├── Activity.java             # Component interface
│   │   ├── ActivityDecorator.java
│   │   ├── BasicCity.java            # Base component
│   │   ├── MuseumVisit.java
│   │   ├── ShoppingMallVisit.java
│   │   ├── ParkVisit.java
│   │   └── CityCenterVisit.java
│   ├── composite/
│   │   ├── PlanComponent.java        # Component interface
│   │   ├── ActivityPlan.java         # Composite
│   │   └── ActivityLeaf.java         # Leaf
│   ├── command/
│   │   ├── Command.java              # Command interface
│   │   ├── CommandHistory.java       # Command manager
│   │   ├── AddActivityCommand.java
│   │   ├── RemoveActivityCommand.java
│   │   ├── AddCityCommand.java
│   │   ├── RemoveCityCommand.java
│   │   ├── MoveActivityCommand.java
│   │   └── ClearPlanCommand.java
│   └── ui/
│       └── TravelPlannerUI.java      # Main GUI
├── target/
│   └── smarttravelplanner.jar        # Compiled Fat JAR
└── .git/                              # Version control

```

---

## 👥 Team Members

| Name | Role | Responsibilities |
|---|---|---|
| **Beyza Gültekin** | Lead Developer | Observer Pattern, Weather Updates, Charts |
| **Şeyma Aslan** | Lead Developer | Command Pattern, Composite Pattern, GUI Integration |

---

## 📋 Deliverables Checklist

- ✅ **Source Code:** Complete Java application with 7 design patterns
- ✅ **JAR File:** Fat JAR executable (`smarttravelplanner.jar`)
- ✅ **JSON Data:** 12 Turkish cities with weather data (`cities.json`)
- ✅ **README:** Comprehensive user manual and documentation
- ⏳ **UML Diagram:** (To be generated - class diagram with all patterns)
- ⏳ **Contribution Report:** (Detailed breakdown of team contributions)

---

## 🏗️ Architecture Overview

```
┌─────────────────────────────────────────────────────────┐
│                   TravelPlannerUI (GUI)                 │
│              (implements WeatherObserver)               │
└──────────────────────┬──────────────────────────────────┘
                       │
        ┌──────────────┼──────────────┐
        │              │              │
        ▼              ▼              ▼
   ┌─────────┐  ┌──────────┐  ┌────────────┐
   │ Strategy │  │ Iterator │  │ Decorator  │
   │ Pattern  │  │ Pattern  │  │ Pattern    │
   └────┬────┘  └────┬─────┘  └─────┬──────┘
        │            │              │
        └────────────┼──────────────┘
                     │
        ┌────────────▼───────────┐
        │   CityRepository       │
        │  (Singleton Pattern)   │
        │   Loads cities.json    │
        └────────────┬───────────┘
                     │
        ┌────────────▼──────────────┐
        │ WeatherReportProvider     │
        │ (Observer Pattern Subject)│
        │ (Running on separate      │
        │  thread, updates every 3s)│
        └──────────────────────────┘
        
┌─────────────────────────────────────────┐
│  CommandHistory (Command Pattern)       │
│  Manages Undo/Redo for all operations   │
└─────────────────────────────────────────┘

┌─────────────────────────────────────────┐
│  ActivityPlan / ActivityLeaf            │
│  (Composite Pattern for hierarchical    │
│   travel planning)                      │
└─────────────────────────────────────────┘
```

---

## 🧪 Testing the Application

### Recommended Test Scenarios

1. **Singleton Test:**
   - Run the app multiple times
   - Verify that `CityRepository` loads `cities.json` only once

2. **Strategy Pattern Test:**
   - Change sorting from Name → Population → Area
   - Verify cities reorder correctly

3. **Iterator Pattern Test:**
   - Select different weather filters
   - Verify only matching cities appear

4. **Observer Pattern Test:**
   - Watch the charts update every 3 seconds
   - Verify temperature and weather distribution change

5. **Decorator Pattern Test:**
   - Select a city
   - Add multiple activities
   - Verify cost and time calculations

6. **Composite Pattern Test:**
   - Create multiple activities for a city
   - Verify hierarchical structure in Travel Plan

7. **Command Pattern Test:**
   - Perform actions
   - Click Undo/Redo
   - Verify operations reverse and restore

---

## 🔧 Troubleshooting

| Issue | Solution |
|---|---|
| **"Cannot find cities.json"** | Ensure `cities.json` is in project root: `C:\Projects\SmartTravelPlanner\cities.json` |
| **Maven build fails** | Run `mvn clean install` to download dependencies |
| **JavaFX window won't open** | Verify Java version with `java -version` (must be 17+) |
| **Charts not updating** | Check if thread is running; restart application |
| **Undo/Redo not working** | Ensure `CommandHistory` is initialized in UI |
| **"Port already in use"** | Another instance is running; close it first |
| **GUI freezes** | WeatherReportProvider is updating; wait 3-5 seconds |

---

## 📖 Learning Outcomes

Through this project, developers learn:
- ✅ Real-world application of 7 design patterns
- ✅ Multi-threaded GUI application development
- ✅ Observer pattern with real-time updates
- ✅ Composite pattern for hierarchical data
- ✅ Command pattern for undo/redo functionality
- ✅ Decorator pattern for flexible object composition
- ✅ Maven project configuration and packaging
- ✅ JSON data handling with Gson

---

## 📄 Academic Information

| Detail | Value |
|---|---|
| **Course** | SENG 324 - Software Design Patterns |
| **University** | Ankara Science University |
| **Semester** | 2025-2026 Spring |
| **Project Type** | Team-Based Extended Version |
| **Team Size** | 2 students |
| **Submission Date** | May 2026 |

**Evaluation Criteria:**
- Design quality
- Clarity of UML diagram
- Code organization
- Successful pattern usage
- GUI functionality
- Integration quality across team modules

---

## 📜 License

This project is created for educational purposes as part of the SENG 324 course at Ankara Science University.

---

## 📞 Support

For issues or questions:
1. Review the README carefully
2. Check the Troubleshooting section
3. Verify all requirements are met
4. Contact the development team

---

**Last Updated:** May 17, 2026  
**Version:** 1.0 Final


