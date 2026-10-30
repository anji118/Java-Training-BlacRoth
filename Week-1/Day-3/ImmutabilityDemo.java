/**
 * What is immutability?
Immutability means an object's state cannot be changed after creation.
For example: Java's String class is immutable. 
When you modify a string, Java creates a new string rather than changing the original one.
final: Prevents reassignment of a reference, but does not make the referenced object immutable.
 */


package com.training.objectmethods;


//final class Student prevents inheritance.
final class Student {
	
	//prevent reassignment after initialization.
    private final int id;
    private final String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

public class ImmutabilityDemo {
	public static void main(String[] args) {
        Student s1 = new Student(101, "Ravi");

        System.out.println(s1.getId());
        System.out.println(s1.getName());
    }
}
