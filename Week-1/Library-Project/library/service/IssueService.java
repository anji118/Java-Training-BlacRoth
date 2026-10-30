package com.training.library.service;




import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.training.library.exceptions.BookAlreadyIssuedException;
import com.training.library.exceptions.BookNotFoundException;
import com.training.library.exceptions.InvalidMemberException;
import com.training.library.exceptions.LibraryDataException;
import com.training.library.model.Book;
import com.training.library.model.Loan;
import com.training.library.repository.LibraryDataStore;

;

public class IssueService {

    private static final int LOAN_DAYS = 14;
    private static final double FINE_PER_DAY = 5.0;

    private final BookService bookService;
    private final MemberService memberService;
    private final LibraryDataStore dataStore;
    private final List<Loan> loans = new ArrayList<>();

    public IssueService(
            BookService bookService,
            MemberService memberService,
            LibraryDataStore dataStore) {

        if (bookService == null || memberService == null
                || dataStore == null) {
            throw new IllegalArgumentException(
                    "Services and data store cannot be null");
        }

        this.bookService = bookService;
        this.memberService = memberService;
        this.dataStore = dataStore;

        try {
            loans.addAll(dataStore.loadLoans());
        } catch (IOException e) {
            throw new LibraryDataException(
                    "Failed to load loans", e);
        }
    }

    public Loan issueBook(int bookId, int memberId) {

        Book book = bookService.searchBookById(bookId)
                .orElseThrow(() -> new BookNotFoundException(
                        "Book not found with ID: " + bookId));

        if (!book.isAvailable()) {
            throw new BookAlreadyIssuedException(
                    "Book is already issued: " + bookId);
        }

        if (!memberService.isValidMember(memberId)) {
            throw new InvalidMemberException(
                    "Member is not valid or active: " + memberId);
        }

        LocalDate issueDate = LocalDate.now();
        LocalDate dueDate = issueDate.plusDays(LOAN_DAYS);

        Loan loan = new Loan(
                bookId, memberId, issueDate, dueDate);

        loans.add(loan);
        book.setAvailable(false);

        bookService.saveChanges();
        saveLoans();

        return loan;
    }

    public double returnBook(int bookId) {

        Loan activeLoan = loans.stream()
                .filter(loan -> loan.getBookId() == bookId)
                .filter(loan -> !loan.isReturned())
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException(
                        "No active loan found for book ID: " + bookId));

        Book book = bookService.searchBookById(bookId)
                .orElseThrow(() -> new BookNotFoundException(
                        "Book not found with ID: " + bookId));

        activeLoan.returnBook(LocalDate.now());
        book.setAvailable(true);

        double fine = activeLoan.calculateFine(FINE_PER_DAY);

        bookService.saveChanges();
        saveLoans();

        System.out.println("Book returned successfully.");
        System.out.println("Late days: " + activeLoan.getLateDays());
        System.out.println("Fine: ₹" + fine);

        return fine;
    }

    public List<Loan> getAllLoans() {
        return new ArrayList<>(loans);
    }

    public List<Loan> getActiveLoans() {
        return loans.stream()
                .filter(loan -> !loan.isReturned())
                .toList();
    }

    public double getFinePerDay() {
        return FINE_PER_DAY;
    }

    private void saveLoans() {
        try {
            dataStore.saveLoans(loans);
        } catch (IOException e) {
            throw new LibraryDataException(
                    "Failed to save loans", e);
        }
    }
}