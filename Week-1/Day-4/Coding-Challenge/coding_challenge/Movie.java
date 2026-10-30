/*
 * Concept: Encapsulation and validation
 */

package com.training.coding_challenge;


class Ticket {

    private String movieName;
    private double rating;

    public Ticket(String movieName) {
        this.movieName = movieName;
    }

    public void setRating(double rating) {
        if (rating >= 0 && rating <= 10) {
            this.rating = rating;
        } else {
            System.out.println("Rating must be between 0 and 10");
        }
    }

    public void displayMovie() {
        System.out.println("Movie: " + movieName);
        System.out.println("Rating: " + rating + "/10");
    }
}
public class Movie {
	public static void main(String[] args) {
		Ticket tk=new Ticket("RRR");
		tk.setRating(7.9);
		tk.displayMovie();
	}
}
