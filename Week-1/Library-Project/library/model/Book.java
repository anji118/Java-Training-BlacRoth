package com.training.library.model;





/**
 * Represents a book in the library.
 */
public class Book {

    private final int id;
    private String title;
    private String author;
    private boolean available;

    public Book(int id, String title, String author) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    "Book ID must be positive");
        }

        validateDetails(title, author);

        this.id = id;
        this.title = title.trim();
        this.author = author.trim();
        this.available = true;
    }

    private void validateDetails(String title, String author) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException(
                    "Title cannot be empty");
        }

        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException(
                    "Author cannot be empty");
        }
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void updateDetails(String title, String author) {
        validateDetails(title, author);
        this.title = title.trim();
        this.author = author.trim();
    }

    /**
     * Changes availability. Used by the issue service.
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Book ID: " + id
                + ", Title: " + title
                + ", Author: " + author
                + ", Available: " + available;
    }
}
