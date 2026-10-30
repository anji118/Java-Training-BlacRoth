
/*
 * Concept: Composition and ArrayList
 */
package com.training.coding_challenge;

import java.util.ArrayList;
import java.util.List;

 class Cart {

    private List<String> products = new ArrayList<>();

    public void addProduct(String product) {
        products.add(product);
        System.out.println(product + " added to cart");
    }

    public void removeProduct(String product) {
        products.remove(product);
        System.out.println(product + " removed from cart");
    }

    public void displayCart() {
        System.out.println("Shopping Cart:");

        for (String product : products) {
            System.out.println(product);
        }
    }
}

public class ShoppingCart {
	public static void main(String[] args) {
		Cart cart = new Cart();
        cart.addProduct("Laptop");
        cart.addProduct("Mouse");
        cart.displayCart();
        cart.removeProduct("Laptop");
        cart.displayCart();
	}

}
