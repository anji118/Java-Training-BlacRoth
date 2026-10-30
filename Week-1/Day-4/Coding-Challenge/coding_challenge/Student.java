/*
 * Concept: Encapsulation:
 * 
 */


package com.training.coding_challenge;

class Student1{
	private int id;
	private String name;
	private int age;
	private double marks;
	
	public Student1(int id,String name, int age, double marks) {
		this.id=id;
		this.name=name;
		this.age=age;
		this.marks=marks;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getMarks() {
		return marks;
	}

	public void setMarks(double marks) {
		this.marks = marks;
	}
	
	public void display() {
		System.out.println("Student Id: "+ id);
		System.out.println("Student Name: " + name);
		System.out.println("Student Age: " + age);
		System.out.println("Student Got Marks: " + marks);
	}
}



public class Student {
	
	public static void main(String[] args) {
		Student1 s1=new Student1(101,"Sai",23,78.8);
		
		s1.display();
	}

}
