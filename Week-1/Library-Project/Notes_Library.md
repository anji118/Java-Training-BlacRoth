#Library Management System Notes

##1. Project overview
Project name: Library Management System

Objective: To manage books, library members, book issuing and returning, late fines, and reports using Java.

Main features:
- Add, update, delete, and search books.
- Register, update, and deactivate members.
- Issue and return books.
- Calculate late-return fines.
- Generate library reports.
- Save and load data using JSON files.

2. Object-Oriented Programming (OOP)
OOP is a programming approach that organizes code using classes and objects.
Class and object
- Class: A blueprint for creating objects.
- Object: An instance of a class.

Example: Book is a class; a particular Java book with ID 101 is an object.
Book book = new Book(101, "Java", "James");


Encapsulation:
-Encapsulation means keeping data private and accessing or modifying it through public methods.
private String title;

```public String getTitle() {
    return title;
}
```

Benefit: Protects data and allows validation before changing values.

Abstraction:
-Abstraction means hiding implementation details and exposing only necessary operations.
-In this project, service methods such as issueBook() let users issue books without needing to know all the internal steps.

Inheritance:
-Inheritance allows one class to reuse or extend another class's functionality.
-For example, a custom exception can extend RuntimeException.

```public class BookNotFoundException
        extends RuntimeException {
}
```

Polymorphism:
-Polymorphism means one interface or parent type can represent different implementations.
-In this project, exceptions share a common parent type, RuntimeException, but each exception represents a different problem.

3. Main project classes:

Class	Responsibility
Book	Stores book ID, title, author, and availability
Member	Stores member details and active status
Loan	Stores issue date, due date, return date, and fine calculation
BookService	Manages book operations
MemberService	Manages member operations
IssueService	Handles issuing and returning books
LibraryReportService	Generates library reports
LibraryDataStore	Saves and loads JSON data
LibraryTestApp	Runs the application

4. Collections and generics
Collections store and manage groups of objects.

List:
-A List stores multiple objects in an ordered collection.
-List<Book> books = new ArrayList<>();

Here, Book is the type of object stored in the list.

ArrayList:
-ArrayList is a resizable list implementation.
books.add(new Book(101, "Java", "James"));

Generics:
-Generics specify the type of data a collection can store.
-List<Member> members = new ArrayList<>();


-This helps prevent incompatible object types from being added.
Optional
Optional represents a value that might be present or absent.

```searchBookById(id)
    .orElseThrow(() ->
        new BookNotFoundException("Book not found"));
```

It helps handle missing books without directly using a null result.
Streams and filtering
Streams help process collections.

```
books.stream()
    .filter(Book::isAvailable)
    .toList();
```

This returns a list of available books.
5. Exception handling
Exception handling manages errors without abruptly terminating normal application flow.
Built-in exceptions
- IllegalArgumentException: Invalid input.
- IllegalStateException: An operation is not allowed in the current state.
- IOException: A file-reading or file-writing operation fails.
Custom exceptions
Exception	Meaning
BookNotFoundException	Requested book is missing
MemberNotFoundException	Requested member is missing
BookAlreadyIssuedException	Book is already issued
InvalidMemberException	Member cannot borrow a book
DuplicateBookException	Book ID already exists
LibraryDataException	Library data could not be loaded or saved

```try-catch
try {
    // Perform an operation
} catch (LibraryDataException e) {
    System.out.println(e.getMessage());
}
```

- try contains code that may fail.
- catch handles the exception.
- getMessage() returns the error message.
- throw raises an exception.
- throws declares that a method may pass an exception to its caller.
Why custom exceptions? They make errors more meaningful and easier to identify.

6. File handling and JSON persistence:
Persistence means saving information so it remains available after the program closes.

Your project uses JSON files:
- books.json
- members.json
- loans.json
Jackson
Jackson is a Java library used to read and write JSON.

Important classes:
- ObjectMapper: Reads and writes JSON.
- JsonNode: Reads JSON data.
- ObjectNode: Creates JSON objects.
- ArrayNode: Creates JSON arrays.
Example JSON:

```{
  "id": 101,
  "title": "Java Programming",
  "author": "James",
  "available": true
}
```


Saving data
The application converts Java objects into JSON and writes them to files.
Loading data
The application reads JSON and creates Java objects from the stored information.
IOException
This exception is used when file operations fail, such as when the application cannot read or write a JSON file.
Important lesson from your error
Your application encountered an empty date string:
LocalDate.parse("");


This fails because an empty string is not a valid date.

Valid example:
LocalDate.parse("2026-10-29");


An unreturned book can have returnDate: null, but issueDate and dueDate must contain valid dates.
7. Date and time handling
Java's java.time package provides date and time classes.
LocalDate
Represents a date without a time.
LocalDate today = LocalDate.now();


plusDays()
Adds days to a date.
LocalDate dueDate = today.plusDays(14);


ChronoUnit.DAYS.between()
Calculates the number of days between two dates.
long days = ChronoUnit.DAYS.between(
    dueDate, returnDate);


Late fine calculation
Your project uses a fine of ₹5 per late day.
double fine = lateDays * 5.0;


If a book is returned 3 days late, the fine is ₹15.
8. Composition and relationships
Composition means one object works with or owns other objects as part of a larger design.

In your project, IssueService uses BookService, MemberService, and LibraryDataStore to perform its work.
private final BookService bookService;
private final MemberService memberService;


This is a form of object collaboration and dependency injection through the constructor.
9. Business logic and validation
Business logic defines the rules of the library.

Examples:
- A book ID must be positive.
- A member must be active to borrow books.
- An unavailable book cannot be issued again.
- A returned book becomes available.
- An issued book cannot be deleted.
- A fine is charged only for late returns.
Validation prevents invalid data from entering the system.
10. Reports
LibraryReportService displays information about library activity.
- Available books: Books ready to be borrowed.
- Borrowed books: Books with active loans.
- Top borrowers: Members ranked by borrowing activity.
- Fine report: Loans with fines to pay.
Collections, streams, filtering, sorting, and loops can be used to generate these reports.