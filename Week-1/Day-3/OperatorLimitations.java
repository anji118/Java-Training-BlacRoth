/**
 * What are operator limitations in Java?
 * 1.Java does not support user-defined operator overloading. 
 * 2.Operators such as +, ==, and > have specific behaviors and cannot be customized for our own classes.
 * 3.For object comparison, we use equals(), Comparable, or Comparator. For custom operations, we create methods. 
 * 4.These alternatives make Java programs easier to understand and maintain.”
 *
 *Operator limitation	                                  Appropriate alternative
Cannot overload + for custom classes	                   Create a method such as add()
== checks object identity	                          Use equals() for content equality
Cannot use < or > directly on arbitrary objects	        Use Comparable or Comparator
Operators have fixed behavior	                     Create methods for custom operations
Complex logical expressions become difficult to read	     Use descriptive methods
 *
 */


package com.training.objectmethods;
class Employe {
    int salary;

    Employe(int salary) {
        this.salary = salary;
    }
}
public class OperatorLimitations {
	public static void main(String[] args) {
        Employe e1 = new Employe(10000);
        Employe e2 = new Employe(20000);

        // System.out.println(e1 + e2); // Compilation error
        System.out.println(e1.salary + e2.salary);
    }
}
