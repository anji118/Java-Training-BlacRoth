package com.training.library.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Represents a book issue and return record.
 */
public class Loan {

    private final int bookId;
    private final int memberId;
    private final LocalDate issueDate;
    private final LocalDate dueDate;
    private LocalDate returnDate;

    public Loan(int bookId, int memberId,
                LocalDate issueDate, LocalDate dueDate) {

        if (bookId <= 0 || memberId <= 0) {
            throw new IllegalArgumentException(
                    "Book ID and member ID must be positive");
        }

        if (issueDate == null || dueDate == null
                || dueDate.isBefore(issueDate)) {
            throw new IllegalArgumentException(
                    "Invalid issue or due date");
        }

        this.bookId = bookId;
        this.memberId = memberId;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
    }

    public int getBookId() {
        return bookId;
    }

    public int getMemberId() {
        return memberId;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public boolean isReturned() {
        return returnDate != null;
    }

    public void returnBook(LocalDate date) {
        if (isReturned()) {
            throw new IllegalStateException(
                    "Book has already been returned");
        }

        if (date == null || date.isBefore(issueDate)) {
            throw new IllegalArgumentException(
                    "Invalid return date");
        }

        this.returnDate = date;
    }

    public long getLateDays() {
        LocalDate effectiveDate =
                returnDate != null ? returnDate : LocalDate.now();

        return Math.max(
                0, ChronoUnit.DAYS.between(dueDate, effectiveDate));
    }

    public double calculateFine(double finePerDay) {
        if (!Double.isFinite(finePerDay) || finePerDay < 0) {
            throw new IllegalArgumentException(
                    "Invalid fine rate");
        }

        return getLateDays() * finePerDay;
    }

    @Override
    public String toString() {
        return "Loan{bookId=" + bookId
                + ", memberId=" + memberId
                + ", issueDate=" + issueDate
                + ", dueDate=" + dueDate
                + ", returnDate=" + returnDate + "}";
    }
}