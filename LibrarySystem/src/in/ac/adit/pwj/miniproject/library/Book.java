package in.ac.adit.pwj.miniproject.library;

import java.io.Serializable;

public class Book implements Serializable {
    private String bookId;
    private String title;
    private String author;
    private int quantity;

    // Constructor to initialize the book details
    public Book(String bookId, String title, String author, int quantity) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }

    // Getters and Setters for the fields
    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Decrease the quantity of the book when it is borrowed
    public void decreaseQuantity() {
        if (quantity > 0) {
            quantity--;
        } else {
            System.out.println("No more copies available to borrow.");
        }
    }

    // Increase the quantity of the book when it is returned
    public void increaseQuantity() {
        quantity++;
    }

    // To display book details in a user-friendly way
    @Override
    public String toString() {
        return "Book ID: " + bookId + ", Title: " + title + ", Author: " + author + ", Quantity: " + quantity;
    }
}
