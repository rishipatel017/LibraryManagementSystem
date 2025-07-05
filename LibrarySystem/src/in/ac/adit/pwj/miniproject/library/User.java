package in.ac.adit.pwj.miniproject.library;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

abstract class User implements Serializable {
    protected String userId;
    protected String name;
    protected List<Book> borrowedBooks;  // To store the books borrowed by the user

    // Constructor
    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    // Abstract methods for borrowing and returning books
    public abstract void borrowBook(Library library, String bookTitle);

    public abstract void returnBook(Library library, String bookTitle);

    // Method to borrow a book and add it to borrowedBooks
    public void addBorrowedBook(Book book) {
        borrowedBooks.add(book);
    }

    // Method to remove a book from borrowedBooks when returning it
    public void removeBorrowedBook(Book book) {
        borrowedBooks.remove(book);
    }

    // Getters and Setters for userId and name
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Override toString to return a user-friendly string representation of the user
    @Override
    public String toString() {
        return "User ID: " + userId + ", Name: " + name;
    }
}
