
/**
 * Has-A Relationship in Java (Composition vs Aggregation)
 * 
 * Composition: 
 * 1.Strong coupling. Dependent object cannot exist alone.
 * 2.The child object dies if the parent is destroyed.
 * 3.The child object is created inside the parent class.
 
   Aggregation:
   1.Loose coupling. Objects exist independently.
   2.The child object survives if the parent is destroyed.
   3.The child object is passed in via constructor or setter.
 */
package com.training.examples;

// Composition example: Engine is created and owned by Car
class Engine {
    void start() {
        System.out.println("Engine started");
    }
}

class CarWithEngine {
    private final Engine engine = new Engine();

    void startCar() {
        engine.start();
        System.out.println("Car started");
    }
}

// Aggregation example: Department refers to an existing Teacher
class Teacher {
    private final String name;

    Teacher(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
}

class Department {
    private final Teacher teacher;

    Department(Teacher teacher) {
        this.teacher = teacher;
    }

    void showTeacher() {
        System.out.println("Teacher: " + teacher.getName());
    }
}

public class CompositionAggregationDemo {
    public static void main(String[] args) {
    	
        // Composition
        CarWithEngine car = new CarWithEngine();
        car.startCar();

        // Aggregation
        Teacher teacher = new Teacher("koti");
        Department department = new Department(teacher);
        department.showTeacher();
    }
}
