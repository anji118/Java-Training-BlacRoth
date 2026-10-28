# Java OOP Fundamentals — Day 1

## 1. Class and Object

-A class is a blueprint or templates for creating objects.That defines data and behavior. 
-An object is an instance of a class.

Example:

```java
class Student {
    String name;

    void display() {
        System.out.println(name);
    }
}
```

Creating an object:

```java
Student student = new Student();
student.name = "Ravi";
student.display();
```

Real-world example: `Student` is a blueprint; each individual student is an object.


## 2. Constructor

-A constructor is a special method used to initialize an object. 
-It has the same name as its class and no return type.

```java
class Student {
    String name;
    int age;

    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```
Real-world Example: Every student object starts with its own registration details.
Types of Constructors:

1.Default Constructor(Compiler Generated):Provided Automatically by java when no complier is defined.

```class Student {
    String name;
    int age;
    }
    Student s=new Student();// Default Constructor
}
```

2.No-Argument Constructor(user defined): You can define a constructor without parameters.

```public class Book{
		Book(){
		System.out.println("Book Is Created");
		}
	}
	
```

3.Parametrized Constructor: Constructor with arguments is called parametrized constructor.

```public class Car{
	String name;
	String model;
	
 	public Car(String name, String model){
 	this.name=name;
 	this.model=model'
 System.out.println(name +" "+ model);
			 }
 
 }
 ```

## 3.Constructor chaining : 
- Constructor chaining in Java means calling one constructor from another constructor. It helps us reuse initialization code instead of writing the same code multiple times.

There are two main types:
1. Constructor chaining within the same class using this().
2. Constructor chaining from a parent class using super().

- Real-world example: Mobile phone
Imagine you are creating a mobile phone. Sometimes you provide only the brand, sometimes the brand and model, and sometimes all three details, including price.

```
class Mobile {

    String brand;
    String model;
    double price;

    // Constructor 1
    Mobile() {
        this("Samsung");
        System.out.println("No-argument constructor");
    }

    // Constructor 2
    Mobile(String brand) {
        this(brand, "Galaxy");
        System.out.println("Brand constructor");
    }

    // Constructor 3
    Mobile(String brand, String model) {
        this(brand, model, 20000);
        System.out.println("Brand and model constructor");
    }

    // Constructor 4
    Mobile(String brand, String model, double price) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        System.out.println("All details constructor");
    }

    void display() {
        System.out.println(brand + " " + model + " " + price);
    }

    public static void main(String[] args) {
        Mobile m = new Mobile();
        m.display();
    }
}
```

## 4.Instance Members:
- Instance members are variables and methods that belong to an object of a class. Each object has its own copy of instance variables.

Example: String name;

```class Student {
    String name; // Instance variable

    void display() { // Instance method
        System.out.println(name);
    }

    public static void main(String[] args) {
        Student s1 = new Student();
        s1.name = "Ravi";
        s1.display();
    }
}
```


## 5.Static Members:
- Static members are variables and methods that belong to the class rather than individual objects. Static variables are shared among all objects of that class.

Example: static String college = "ABC College";

```class Student {
    static String college = "ABC College";

    static void display() {
        System.out.println(college);
    }

    public static void main(String[] args) {
        Student.display();
    }
}
```

## 6. Access Modifiers
-Access Modifiers in java contorl the visibility and accessibilty of classes,variables,methods and constructors with in java programming.

- `private`: accessible only within the declaring class.
- Default (no modifier): accessible within the same package.
- `protected`: accessible within the same package and by subclasses, subject to Java access rules.
- `public`: accessible wherever the class or member is accessible.

Real-world example: Bank security
Imagine a bank account. Everyone can see some information, but sensitive information should be protected.


## 7. Encapsulation

-Encapsulation defind as the wrapping up of data into a single unit. 
-Declare all variables as private and providing controlled access through getters and setters methods.


```
class BankAccount {
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
}
```

Benefits:
- Protects data from direct modification.
- Allows validation.
- Makes code easier to maintain.

Real-world example: a bank account allows deposits and withdrawals through defined operations rather than allowing anyone to change its balance directly.



## 8. `this` and `super`

- `this`keyword is reference variable that refers to the current object.

```
public class Example{
	int value;
	public Example(int value){
	this.value=value;
	}
}	
```

- `super` refers to that allows a subclass to access variables,methods and constructors of its parent class.

```

class Vehicle {
    void start() {
        System.out.println("Vehicle is starting");
    }
}

class Car extends Vehicle {
    void drive() {
        System.out.println("Car is moving");
    }
}
```
--- Difference Between this and super keywords:

                  this()                                                 super()
    1.It refers to the current Class object.              1.It Refers to Immediate Parent Class.
    2.Used to access current class variable and method.   2.Used to access Parent class variable and methods.
    3.this calls Another constructor of the same Class.   3.Super Calls the parent class constructor.
    
                  
## 9.Packages:
- A package in Java is a group of related classes, interfaces, and subpackages used to organize code and avoid naming conflicts.

Real-world example: A college organizes students and teachers into separate departments or groups.

```
package college;

public class Student {
    public void display() {
        System.out.println("Student details");
    }
}
```


## 10.Object Lifecycle in Java: 
- The object lifecycle is the series of stages an object goes through, from creation to becoming eligible for garbage collection.

The main stages are:
1. Object Creation

new Student()

2. Initialization

Constructor initializes the object.

3. Object Usage

Access fields and methods.

4. Eligible for Garbage Collection

No longer reachable by the program

## 11. What is OOP?

-Object-Oriented Programming (OOP) is a programming approach based on classes and objects.
-It organizes code into, reuse components, and aiming to simplify software Development and maintance.

The four main OOP principles are:
- Encapsulation
- Inheritance
- Polymorphism
- Abstraction

## 12. Inheritance

Inheritance means one class(child)acquiring the properties(fields) and behaviors(methods) of another class using `extends` keyword.

Real-world example: Vehicle and Car. A car is a vehicle, so it can reuse common vehicle features like starting and stopping.

```java
class Vehicle {
    void start() {
        System.out.println("Vehicle is starting");
    }
}

class Car extends Vehicle {
    void drive() {
        System.out.println("Car is moving");
    }
}
```

Usage:

```java
Car car = new Car();
car.start();
car.drive();
```
Benefits:
- Code reuse.
- Easier extension.
- Clear parent-child relationships.

Types of inheritance in Java:

1.Single: one child class inherits from one parent class.

```
java
class Vehicle {
    void start() {
        System.out.println("Vehicle is starting");
    }
}

class Car extends Vehicle {
    void drive() {
        System.out.println("Car is moving");
    }
}
```

2.Multilevel: a class inherits from a class that already inherits from another class.

```
java
class Vehicle {
    void start() {
        System.out.println("Vehicle is starting");
    }
}

class Car extends Vehicle {
    void drive() {
        System.out.println("Car is moving");
    }
 
class Bike extends Car{
	void sound() {
	System.out.println("Bike is sounding");
	}   
}
```
3.Hierarchical: multiple child classes inherit from one parent.

```
java
class Vehicle {
    void start() {
        System.out.println("Vehicle is starting");
    }
}

class Car extends Vehicle {
    void drive() {
        System.out.println("Car is moving");
    }
 
class Bike extends Vehicle{
	void sound() {
	System.out.println("Bike is sounding");
	}   
}
```
4.Multiple inheritance of type is supported through interfaces, not through extending multiple classes.

## 13.Polymorphism:

- Polymorphism means one thing having many forms. 
- In Java, it is mainly achieved through method overloading and method overriding. 
- Overloading provides compile-time polymorphism, while overriding provides runtime polymorphism.


```class Calculator {
    int add(int a, int b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }

    public static void main(String[] args) {
        Calculator c = new Calculator();

        System.out.println(c.add(10, 20));
        System.out.println(c.add(10, 20, 30));
    }
}
```

-Types:
1.Compile-time Polymorphism(Method Overloading):Same method name but different parameters.

```class Anji{
	int fruits(){
	}
	int fruits(String apple){
	}
}
```
2.Runtime Polymorphism(Method Overriding):A child class provides its own implementation of a method inherited from its parent class.

```Class Animal{
	void sound(){
	System.Out.println("Animal Makes Sound");
	}
Class Dog extends Animal{
    @Override
	void bark(){
	System.out.println("Dog Barks");
 }
}
```

Real-world example: Think about a payment system. You can make payments using UPI, a credit card, or cash. The action is the same — pay — but the behavior is different.

## 14. Abstraction:

- Abstraction is an OOP concept that hides implementation details and shows only the essential features to the user.
- We can achieve abstraction using an abstract class or an interface.

Real-world example: ATM machine 


```abstract class ATM {
    abstract void withdraw();

    void checkBalance() {
        System.out.println("Checking balance");
    }
}

class SBIATM extends ATM {
    @Override
    void withdraw() {
        System.out.println("Cash withdrawn successfully");
    }
}

public class Main {
    public static void main(String[] args) {
        ATM atm = new SBIATM();

        atm.checkBalance();
        atm.withdraw();
    }
}```











