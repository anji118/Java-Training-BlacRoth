package com.training.objectmethods;


  class Employee {

    private final String name;
    private final int id;
    private final double salary;

    public Employee(String name, int id, double salary) {

        if (name == null || name.isBlank() || id <= 0 || salary < 0) {
            throw new IllegalArgumentException("Invalid Employee Details");
        }

        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee [name=" + name
                + ", id=" + id
                + ", salary=" + salary + "]";
    }
}
 /**
  * toString:
  * 1.toString() is a method in Java's Object class that returns a string representation of an object. 
  * 2.We override it to display object details in a readable format.
  */
 
 
public class ToStringDemo {
	public static void main(String[] args) {
		Employee emp=new Employee("Anjaneyulu", 1332,35000);
		System.out.println(emp);
	}
}
