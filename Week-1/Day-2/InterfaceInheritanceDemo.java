
/**
 * Multiple inheritance:
 * 1.Multiple Inheritance means one child class trying to inherit from more than one parent class.
 * 2.It's not possible in classes, it achive via interfaces.
 * INTERFACE:
 * 1.In java interface is a referance type similar to a class that contains only abstarct methoda (until java7).
 * 2.Default and static methods(since java 8) and private methods(java 9).
 * 3.It is used to achieve abstrcation and multiple inheritance in java.
 */
package com.training.examples;

interface Sem1 {
    void marks1(int m1);
}

interface Sem2 {
    void marks2(int m2);
}

// Multiple inheritance of type through interfaces
class Acadameic implements Sem1, Sem2 {
    @Override
    public void marks1(int m1) {
    		
        System.out.println("Sem1 Marks"+" "+ m1);
    }

    @Override
    public void marks2(int m2) {
        System.out.println("Sem2 Marks" +" "+m2);
    }
}

public class InterfaceInheritanceDemo {
    public static void main(String[] args) {
        Acadameic ac = new Acadameic();

        ac.marks1(23);
        ac.marks2(56);
    }
}
