
package com.training.library.app;

import com.training.library.exceptions.BookAlreadyIssuedException;
import com.training.library.exceptions.BookNotFoundException;
import com.training.library.exceptions.DuplicateBookException;
import com.training.library.exceptions.InvalidMemberException;
import com.training.library.exceptions.LibraryDataException;
import com.training.library.exceptions.MemberNotFoundException;
import com.training.library.model.Book;
import com.training.library.model.Loan;
import com.training.library.model.Member;
import com.training.library.reports.LibraryReportService;
import com.training.library.repository.LibraryDataStore;
import com.training.library.service.BookService;
import com.training.library.service.IssueService;
import com.training.library.service.MemberService;

public class LibraryTestApp {

    public static void main(String[] args) {

        try {
            // Create the data store
            LibraryDataStore dataStore = new LibraryDataStore();

            // Create services
            BookService bookService =
                    new BookService(dataStore);

            MemberService memberService =
                    new MemberService(dataStore);

            IssueService issueService =
                    new IssueService(
                            bookService,
                            memberService,
                            dataStore);

            LibraryReportService reportService =
                    new LibraryReportService(
                            bookService,
                            memberService,
                            issueService);

            System.out.println("===== LIBRARY MANAGEMENT SYSTEM =====");

            // Add sample books if they do not exist
            if (bookService.searchBookById(101).isEmpty()) {
                bookService.addBook(
                        new Book(101, "Java Programming", "James Gosling"));
            }

            if (bookService.searchBookById(102).isEmpty()) {
                bookService.addBook(
                        new Book(102, "Python Basics", "Guido van Rossum"));
            }

            // Register a sample member if not already registered
            if (memberService.findMemberById(1).isEmpty()) {
                memberService.registerMember(
                        new Member(1, "Anjaneyulu"));
            }

            // Issue a book if it is available
            if (bookService.searchBookById(101)
                    .map(Book::isAvailable)
                    .orElse(false)
                    && memberService.isValidMember(1)) {

                Loan loan = issueService.issueBook(101, 1);

                System.out.println("\nBook issued successfully!");
                System.out.println(loan);

            } else {
                System.out.println(
                        "\nBook is already issued or member is invalid.");
            }

            // Display reports
            System.out.println("\n===== AVAILABLE BOOKS =====");
            reportService.showAvailableBooks();

            System.out.println("\n===== BORROWED BOOKS =====");
            reportService.showBorrowedBooks();

            System.out.println("\n===== TOP BORROWERS =====");
            reportService.showTopBorrowers();

            System.out.println("\n===== FINE REPORT =====");
            reportService.showFineReport();

            System.out.println("\n===== APPLICATION COMPLETED =====");

        } catch (BookNotFoundException
                | MemberNotFoundException
                | BookAlreadyIssuedException
                | InvalidMemberException
                | DuplicateBookException e) {

            System.out.println(
                    "Operation failed: " + e.getMessage());

        } catch (LibraryDataException e) {

            System.err.println(
                    "Storage error: " + e.getMessage());

            e.printStackTrace();

        } catch (IllegalArgumentException
                | IllegalStateException e) {

            System.out.println(
                    "Invalid operation: " + e.getMessage());

        } catch (Exception e) {

            System.err.println(
                    "Unexpected error: " + e.getMessage());

            e.printStackTrace();
        }
    }
}
