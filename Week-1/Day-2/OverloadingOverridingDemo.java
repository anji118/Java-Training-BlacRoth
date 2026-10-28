/**
 * METHOD OVERLOADING:
 * 1.Method Overloading means having multiple methods with same name in the class but differnt parameters.
 * 2.Compile Time.
 * 3.Static Binding.
 * 
 * METHOD OVERRIDING:
 * 1.Method Overriding Means a child class provides its own implementation of method that is already defined in the parent class.
 * 2.Runtime 
 * 3.Dynamic Binding
 */
package com.training.examples;

class Calculator {
    // Overloading: two integer parameters
    int add(int a, int b) {
        return a + b;
    }

    // Overloading: three integer parameters
    int add(int a, int b, int c) {
        return a + b + c;
    }
}

class AdvancedCalculator extends Calculator {
    // Overriding the inherited add(int, int) method
    @Override
    int add(int a, int b) {
        System.out.println("Advanced addition");
        return a + b;
    }
}

public class OverloadingOverridingDemo {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println(calculator.add(2, 3));
        System.out.println(calculator.add(2, 3, 4));

        Calculator advanced = new AdvancedCalculator();
        System.out.println(advanced.add(5, 6));
    }
}
