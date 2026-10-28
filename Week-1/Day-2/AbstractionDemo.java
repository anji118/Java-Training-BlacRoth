
/**
 * ABSTRACTION:
 * 1.Abstraction is a one of the principle of OOPs.
 * 2.Minly the purpose of abstraction hidding implementation details and shows only the essential features to the user.
 * 3.We can achieve abstraction using an abstract class or an interface.
 * INTERFACE:
 * 1.In java interface is a referance type similar to a class that contains only abstarct methoda (until java7).
 * 2.Default and static methods(since java 8) and private methods(java 9).
 * 3.It is used to achieve abstrcation and multiple inheritance in java.
 */
package com.training.examples;

// Abstract class
abstract class Animal {

    // Abstract method: no body
    abstract void sound();

    // Concrete method: has a body
    void sleep() {
        System.out.println("Animal is sleeping");
    }
}

// Interface
interface Printable {
    void printDetails();
}

class Dog extends Animal implements Printable {

    @Override
    void sound() {
        System.out.println("Dog barks");
    }

    @Override
    public void printDetails() {
        System.out.println("This is a Dog");
    }
}

public class AbstractionDemo {

    public static void main(String[] args) {

        Dog dog = new Dog();

        dog.sound();
        dog.sleep();
        dog.printDetails();
    }
}
