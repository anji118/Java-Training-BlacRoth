package com.training.library.repository;







import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.training.library.model.Book;
import com.training.library.model.Member;
import com.training.library.model.Loan;

/**
 * Saves and loads library data using JSON files.
 */
public class LibraryDataStore {

    private final ObjectMapper mapper;
    private final File dataDirectory;

    public LibraryDataStore() {
        this("data");
    }

    public LibraryDataStore(String directory) {
        if (directory == null || directory.isBlank()) {
            throw new IllegalArgumentException(
                    "Data directory cannot be empty");
        }

        this.dataDirectory = new File(directory);

        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
    }

    // ---------------- BOOKS ----------------

    public void saveBooks(List<Book> books) throws IOException {
        ArrayNode array = mapper.createArrayNode();

        for (Book book : books) {
            ObjectNode node = array.addObject();

            node.put("id", book.getId());
            node.put("title", book.getTitle());
            node.put("author", book.getAuthor());
            node.put("available", book.isAvailable());
        }

        saveJson("books.json", array);
    }

    public List<Book> loadBooks() throws IOException {
        List<Book> books = new ArrayList<>();
        JsonNode root = readJson("books.json");

        if (root == null || !root.isArray()) {
            return books;
        }

        for (JsonNode node : root) {
            int id = node.path("id").asInt();
            String title = node.path("title").asText("");
            String author = node.path("author").asText("");
            boolean available = node.path("available").asBoolean(true);

            try {
                Book book = new Book(id, title, author);
                book.setAvailable(available);
                books.add(book);
            } catch (IllegalArgumentException e) {
                throw new IOException(
                        "Invalid book data in books.json: "
                                + node, e);
            }
        }

        return books;
    }

    // ---------------- MEMBERS ----------------

    public void saveMembers(List<Member> members) throws IOException {
        ArrayNode array = mapper.createArrayNode();

        for (Member member : members) {
            ObjectNode node = array.addObject();

            node.put("id", member.getId());
            node.put("name", member.getName());
            node.put("active", member.isActive());
        }

        saveJson("members.json", array);
    }

    public List<Member> loadMembers() throws IOException {
        List<Member> members = new ArrayList<>();
        JsonNode root = readJson("members.json");

        if (root == null || !root.isArray()) {
            return members;
        }

        for (JsonNode node : root) {
            int id = node.path("id").asInt();
            String name = node.path("name").asText("");
            boolean active = node.path("active").asBoolean(true);

            try {
                Member member = new Member(id, name);
                member.setActive(active);
                members.add(member);
            } catch (IllegalArgumentException e) {
                throw new IOException(
                        "Invalid member data in members.json: "
                                + node, e);
            }
        }

        return members;
    }

    // ---------------- LOANS ----------------

    public void saveLoans(List<Loan> loans) throws IOException {
        ArrayNode array = mapper.createArrayNode();

        for (Loan loan : loans) {
            ObjectNode node = array.addObject();

            node.put("bookId", loan.getBookId());
            node.put("memberId", loan.getMemberId());
            node.put("issueDate", loan.getIssueDate().toString());
            node.put("dueDate", loan.getDueDate().toString());

            if (loan.getReturnDate() == null) {
                node.putNull("returnDate");
            } else {
                node.put("returnDate",
                        loan.getReturnDate().toString());
            }
        }

        saveJson("loans.json", array);
    }

    public List<Loan> loadLoans() throws IOException {
        List<Loan> loans = new ArrayList<>();
        JsonNode root = readJson("loans.json");

        if (root == null || !root.isArray()) {
            return loans;
        }

        int recordNumber = 0;

        for (JsonNode node : root) {
            recordNumber++;

            try {
                int bookId = node.path("bookId").asInt();
                int memberId = node.path("memberId").asInt();

                String issueDateText =
                        node.path("issueDate").asText("");

                String dueDateText =
                        node.path("dueDate").asText("");

                JsonNode returnDateNode = node.get("returnDate");

                // Issue date and due date are required.
                if (issueDateText.isBlank()
                        || dueDateText.isBlank()) {
                    throw new IOException(
                            "Empty issueDate or dueDate in loan record "
                                    + recordNumber);
                }

                LocalDate issueDate =
                        LocalDate.parse(issueDateText);

                LocalDate dueDate =
                        LocalDate.parse(dueDateText);

                Loan loan = new Loan(
                        bookId,
                        memberId,
                        issueDate,
                        dueDate
                );

                // A return date is optional.
                // null, missing, or empty means not returned.
                if (returnDateNode != null
                        && !returnDateNode.isNull()) {

                    String returnDateText =
                            returnDateNode.asText("").trim();

                    if (!returnDateText.isEmpty()
                            && !returnDateText.equalsIgnoreCase("null")) {
                        loan.returnBook(
                                LocalDate.parse(returnDateText));
                    }
                }

                loans.add(loan);

            } catch (java.time.format.DateTimeParseException
                    | IllegalArgumentException e) {
                throw new IOException(
                        "Invalid date or loan data in loans.json, "
                                + "record " + recordNumber
                                + ": " + node, e);
            }
        }

        return loans;
    }

    // ---------------- COMMON JSON METHODS ----------------

    private void saveJson(String fileName, JsonNode data)
            throws IOException {

        if (!dataDirectory.exists()
                && !dataDirectory.mkdirs()) {
            throw new IOException(
                    "Could not create data directory: "
                            + dataDirectory.getAbsolutePath());
        }

        File file = new File(dataDirectory, fileName);
        mapper.writerWithDefaultPrettyPrinter()
                .writeValue(file, data);
    }

    private JsonNode readJson(String fileName)
            throws IOException {

        File file = new File(dataDirectory, fileName);

        // First run: the file may not exist yet.
        if (!file.exists()) {
            return null;
        }

        if (file.length() == 0) {
            throw new IOException(
                    "JSON file is empty: "
                            + file.getAbsolutePath());
        }

        return mapper.readTree(file);
    }
}
