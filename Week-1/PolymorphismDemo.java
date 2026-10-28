

/**
 * Polymorphism:
1.Polymorphism means one thing having many forms. 
2.In Java, it is mainly achieved through method overloading and method overriding. 
3.Overloading provides compile-time polymorphism, while overriding provides runtime polymorphism.
 */
package com.training.examples;

class Payment {
    void pay() {
        System.out.println("Processing payment");
    }
}

class CardPayment extends Payment {
	
    @Override
    void pay() {
        System.out.println("Paying by card");
    }
}



class UpiPayment extends Payment {
    @Override
    void pay() {
        System.out.println("Paying by UPI");
    }
}

public class PolymorphismDemo {
    public static void main(String[] args) {
        // Parent reference, child object
    	
        Payment payment = new CardPayment();
        payment.pay(); // Calls CardPayment's method

        payment = new UpiPayment();
        payment.pay(); // Calls UpiPayment's method
    }
}
