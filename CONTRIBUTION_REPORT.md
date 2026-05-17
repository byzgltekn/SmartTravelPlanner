# SENG 324 - Software Design Patterns
## Contribution Report

### Student Information

| Name | Student Number |
|------|-----------------|
| Beyza Gültekin | 220201038 |
| Şeyma Aslan | 220201004 |

**Term:** Spring 2025-2026

---

## Team Members
- **Beyza Gültekin** (220201038)
- **Şeyma Aslan** (220201004)

---

## Individual Contributions

### Beyza Gültekin
- **JavaFX GUI Implementation** - Designed and implemented the main user interface with BorderPane layout, responsive controls, and visual components
- **Strategy Pattern Implementation** - Developed SortStrategy interface with SortByNameStrategy, SortByPopulationStrategy, and SortByAreaStrategy classes
- **Iterator Pattern Implementation** - Created CityIterator interface and WeatherCityIterator for weather-based city filtering
- **Weather Filtering System** - Implemented weather filter dropdown and corresponding filtering logic
- **Chart Integration** - Integrated temperature bar chart and weather distribution pie chart for data visualization

### Şeyma Aslan
- **Singleton Repository Implementation** - Designed CityRepository with lazy initialization pattern and thread-safe singleton getInstance() method
- **Observer Pattern Implementation** - Implemented WeatherObserver interface and WeatherReportProvider with real-time weather updates (3-second intervals)
- **Decorator Pattern Implementation** - Created Activity decorator hierarchy with ActivityDecorator, BasicCity, MuseumVisit, ShoppingMallVisit, ParkVisit, and CityCenterVisit classes
- **Composite Pattern Implementation** - Developed PlanComponent interface, ActivityPlan (composite), and ActivityLeaf (leaf) for hierarchical activity structures
- **Command Pattern with Undo/Redo Functionality** - Implemented Command interface, all command classes (AddActivityCommand, RemoveActivityCommand, AddCityCommand, RemoveCityCommand, MoveActivityCommand, ClearPlanCommand), and CommandHistory manager

### Joint Work
- **Project Architecture Design** - Collaborative design of overall system architecture and design pattern integration strategy
- **Testing and Debugging** - Joint testing of all features, pattern interactions, and GUI components
- **UML Diagram Preparation** - Created comprehensive UML class diagram documenting all 7 patterns and their relationships
- **README and Documentation** - Collaboratively wrote detailed README with installation guide, user manual, and pattern explanations

---

## Technical Implementation Summary

### Design Patterns Implemented (7/7)
1. ✓ **Singleton** - CityRepository with lazy initialization
2. ✓ **Strategy** - City sorting by name, population, or area
3. ✓ **Iterator** - Weather-based city iteration
4. ✓ **Observer** - Real-time weather updates
5. ✓ **Decorator** - Activity types with pricing and duration
6. ✓ **Composite** - Hierarchical activity plan structure
7. ✓ **Command** - Complete undo/redo functionality with command history

### Key Technologies
- **Java 17** - Primary programming language
- **JavaFX 17.0.2** - GUI framework with charts and controls
- **Maven 3.9.15** - Build and dependency management
- **Gson 2.10.1** - JSON city data deserialization
- **Maven Shade Plugin** - Fat JAR creation with all dependencies

### Deliverables
- ✓ Full source code (7 design patterns implemented)
- ✓ Executable JAR file with dependencies
- ✓ cities.json (12 Turkish cities with weather data)
- ✓ Comprehensive README.md
- ✓ pom.xml with proper configuration
- ✓ Working GUI application with all required features

---

## Learning Outcomes

Through this project, both team members gained practical experience in:
- Applying multiple design patterns in a single, cohesive application
- Collaborative software development and task distribution
- JavaFX GUI development and real-time UI updates
- JSON data handling and deserialization
- Maven build automation and dependency management
- Pattern interaction and integration in a real-world scenario
