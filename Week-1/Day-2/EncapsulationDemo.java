
/**Encapsulation:
	1.Encapsulation is defind as the wrapping up of data into a single unit.
	2.Declare all variables as private and give access throw getters and setters.
	3.The Primary Goal of Encapsulation is data hiding and Controlled Access.
	*/
package com.training.examples;

class Employee{
	
	//Private variables
	private String name;
	private int age;
	private String domain;
	private double salary;
	
	//Constructor
	public Employee(String name,int age, String domain,double salary) {
		this.name=name;
		setAge(age);
		this.domain=domain;
		setSalary(salary);
	}
	
	//Getters and setters
	public String getName() {
		return name;
		
	}
	public void setName(String name) {
		this.name=name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		if(age > 0) {
			this.age=age;
		}else {
			throw new IllegalArgumentException("Age must be Positive");
		}
	}
	public String getDomain() {
		return domain;
	}
	
	public void setDomain(String domain) {
		this.domain=domain;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public void setSalary(double salary) {
		if(salary > 0) {
			this.salary=salary;
		}else {
			throw new IllegalArgumentException("Salary can not be Negitive");
		}
	}
	
	 // Display employee details
	public void displayDetails() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Domain: " + domain);
        System.out.println("Salary: " + salary);
    }
	
}
public class EncapsulationDemo {
	
	public static void main(String[]args) {
		Employee emp=new Employee("Anjaneyulu",23,"Java Developer",30000);
		emp.displayDetails();
		
		// Updating salary through a setter
		emp.setSalary(35000);
		
		System.out.println("Updated Salary: " + emp.getSalary());
	}

}



