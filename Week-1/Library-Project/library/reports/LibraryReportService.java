package com.training.library.reports;




import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.training.library.model.Loan;
import com.training.library.model.Member;
import com.training.library.service.BookService;
import com.training.library.service.IssueService;
import com.training.library.service.MemberService;



/**
 * Generates library reports.
 */
public class LibraryReportService {

    private final BookService bookService;
    private final MemberService memberService;
    private final IssueService issueService;

    public LibraryReportService(
            BookService bookService,
            MemberService memberService,
            IssueService issueService) {

        if (bookService == null
                || memberService == null
                || issueService == null) {
            throw new IllegalArgumentException(
                    "Services cannot be null");
        }

        this.bookService = bookService;
        this.memberService = memberService;
        this.issueService = issueService;
    }

    public void showAvailableBooks() {
        System.out.println("\n--- Available Books ---");

        bookService.getAvailableBooks()
                .forEach(System.out::println);
    }

    public void showBorrowedBooks() {
        System.out.println("\n--- Borrowed Books ---");

        List<Loan> activeLoans = issueService.getActiveLoans();

        if (activeLoans.isEmpty()) {
            System.out.println("No borrowed books.");
            return;
        }

        for (Loan loan : activeLoans) {
            System.out.println(
                    "Book ID: " + loan.getBookId()
                    + ", Member ID: " + loan.getMemberId()
                    + ", Due Date: " + loan.getDueDate());
        }
    }

    public void showTopBorrowers() {
        System.out.println("\n--- Top Borrowers ---");

        Map<Integer, Integer> borrowCounts = new HashMap<>();

        for (Loan loan : issueService.getAllLoans()) {
            borrowCounts.merge(
                    loan.getMemberId(), 1, Integer::sum);
        }

        List<Map.Entry<Integer, Integer>> ranking =
                new ArrayList<>(borrowCounts.entrySet());

        ranking.sort(
                Map.Entry.<Integer, Integer>comparingByValue(
                        Comparator.reverseOrder()));

        if (ranking.isEmpty()) {
            System.out.println("No borrowing records.");
            return;
        }

        for (Map.Entry<Integer, Integer> entry : ranking) {
            int memberId = entry.getKey();
            int count = entry.getValue();

            String name = memberService.findMemberById(memberId)
                    .map(Member::getName)
                    .orElse("Unknown Member");

            System.out.println(
                    name + " (ID: " + memberId
                    + ") - Books borrowed: " + count);
        }
    }

    public void showFineReport() {
        System.out.println("\n--- Fine Report ---");

        boolean found = false;
        double finePerDay = issueService.getFinePerDay();

        for (Loan loan : issueService.getAllLoans()) {
            double fine = loan.calculateFine(finePerDay);

            if (fine > 0) {
                found = true;

                System.out.println(
                        "Book ID: " + loan.getBookId()
                        + ", Member ID: " + loan.getMemberId()
                        + ", Late Days: " + loan.getLateDays()
                        + ", Fine: Rs. " + fine);
            }
        }

        if (!found) {
            System.out.println("No fines recorded.");
        }
    }
}
