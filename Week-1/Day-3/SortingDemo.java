/**
 * NOTE:Both Comparable and Comparator are used for sorting objects in java.
 * -But they used in different ways and different purpose.
 * 1.COMPARABLE:
 * -It is used to define the natural Ordering of Objects.
 * -It is implemented inside the class.
 * -Has only one Method--> Compare To()
 * 
 * 2.COMPARATOR:
 * -IT is used to define the coustom Ordering of Objects.
 * -It is implemented outside the class.
 * -Has only one Method--> Compare()
 */



package com.training.objectmethods;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Comparable defines the natural ordering of Employee objects.
class Employee2 implements Comparable<Employee2> {

    private int id;
    private String name;
    private double salary;

    public Employee2(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    // Natural ordering: sort by employee ID.
    @Override
    public int compareTo(Employee2 other) {
        return Integer.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        return id + " " + name + " " + salary;
    }
}


public class SortingDemo {
	  public static void main(String[] args) {

	        List<Employee2> employees = new ArrayList<>();

	        employees.add(new Employee2(103, "Ravi", 50000));
	        employees.add(new Employee2(101, "Anu", 60000));
	        employees.add(new Employee2(102, "Kiran", 45000));

	        // Comparable: sort by ID (natural ordering).
	        employees.sort(null);
	        System.out.println("Sorted by ID:");
	        employees.forEach(System.out::println);

	        // Comparator: sort by salary, lowest to highest.
	        employees.sort(
	            Comparator.comparingDouble(Employee2::getSalary)
	        );
	        System.out.println("Sorted by Salary:");
	        employees.forEach(System.out::println);

	        // Custom Comparator: sort by name alphabetically.
	        employees.sort(
	            Comparator.comparing(Employee2::getName)
	        );
	        System.out.println("Sorted by Name:");
	        employees.forEach(System.out::println);
	    }

}
