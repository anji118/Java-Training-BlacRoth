/**
 * INHERITANCE:
 * 1.Inheritance in java one class acquiring the properties(fields) and behaviors(methods) of another class.
 * 2.It hellps inn code "reusability" and "Method Overriding".
 * 3.Private Methods Not involved in Inheritance.
 * TYPES:
 * Single Inheritance:
 * Multilevel Inheritance:
 * Hierarchical Inheritance:
 * Multilevel Inheritance:(Not Possible via classes, to achiev via interfaces)
 * 
 */

package com.training.examples;

class GrandFather{
	void hai() {
		System.out.println("Grand Father says Hai");
	}
}

//Single Inheritance --> A child class inherit from a parent class.
class Father extends GrandFather{
	void hello() {
		System.out.println("Father says Hello");
			
		
	}
}

//Multilevel Inheritance --> One class inherit from a class which itself inherited from another class just like a chain. 

class Son1 extends Father{
	void koti() {
		System.out.println("Son1 name is Koti");
	}
}

//Hierarchical Interface --> Multiple child classes inherit from one parent class.

class Son2 extends Father{
	void anjaneyulu() {
		System.out.println("Son2 name is Anjaeyulu");
	}
}
public class InheritanceDemo {
	public static void main(String[] args) {
		
		 // Single inheritance
		Father fat=new Father();
		fat.hai();
		fat.hello();
		
		 // Multilevel inheritance
		
		Son1 son1=new Son1();
		son1.hai();
		son1.hello();
		son1.koti();
		
		// Hierarchical inheritance
		
		Son2 son2=new Son2();
		son2.hai();
		son2.hello();
		son2.anjaneyulu();
		
	}

}
