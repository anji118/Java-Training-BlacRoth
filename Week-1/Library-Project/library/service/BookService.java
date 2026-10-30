package com.training.library.service;







import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.training.library.exceptions.BookNotFoundException;
import com.training.library.exceptions.DuplicateBookException;
import com.training.library.exceptions.LibraryDataException;
import com.training.library.model.Book;
import com.training.library.repository.LibraryDataStore;



public class BookService {

    private final List<Book> books = new ArrayList<>();
    private final LibraryDataStore dataStore;

    public BookService(LibraryDataStore dataStore) {
        if (dataStore == null) {
            throw new IllegalArgumentException(
                    "Data store cannot be null");
        }

        this.dataStore = dataStore;

        try {
            books.addAll(dataStore.loadBooks());
        } catch (IOException e) {
            throw new LibraryDataException(
                    "Failed to load books", e);
        }
    }

    public void addBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException(
                    "Book cannot be null");
        }

        if (searchBookById(book.getId()).isPresent()) {
            throw new DuplicateBookException(
                    "Book ID already exists: " + book.getId());
        }

        books.add(book);
        saveBooks();
    }

    public Optional<Book> searchBookById(int id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst();
    }

    public List<Book> searchBooksByTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException(
                    "Title cannot be empty");
        }

        return books.stream()
                .filter(book -> book.getTitle()
                        .toLowerCase()
                        .contains(title.trim().toLowerCase()))
                .toList();
    }

    public void updateBook(
            int id, String title, String author) {

        Book book = searchBookById(id)
                .orElseThrow(() -> new BookNotFoundException(
                        "Book not found with ID: " + id));

        book.updateDetails(title, author);
        saveBooks();
    }

    public void deleteBook(int id) {
        Book book = searchBookById(id)
                .orElseThrow(() -> new BookNotFoundException(
                        "Book not found with ID: " + id));

        if (!book.isAvailable()) {
            throw new IllegalStateException(
                    "Cannot delete a book that is currently issued");
        }

        books.remove(book);
        saveBooks();
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    public List<Book> getAvailableBooks() {
        return books.stream()
                .filter(Book::isAvailable)
                .toList();
    }

    public void saveChanges() {
        saveBooks();
    }

    private void saveBooks() {
        try {
            dataStore.saveBooks(books);
        } catch (IOException e) {
            throw new LibraryDataException(
                    "Failed to save books", e);
        }
    }
}