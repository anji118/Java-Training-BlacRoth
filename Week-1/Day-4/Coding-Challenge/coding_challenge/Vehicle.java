/*
 * Concept: Inheritance and Method Overriding
 */

package com.training.coding_challenge;

class Car {
	public void start() {
		System.out.println("Car is starting");
	}
}

class Brand extends Car{
	@Override
	public void start() {
		System.out.println("Audi Car is starting");	
	}
	public void audi() {
		System.out.println("Car is audi Brand");
	}
}




public class Vehicle {
	public static void main(String[] args) {
		Brand br=new Brand();
		br.audi();
		br.start();
		
	}

}
