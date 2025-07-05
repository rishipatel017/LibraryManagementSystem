package in.ac.adit.pwj.miniproject.library;

import java.io.*;
import java.util.*;

public class Library {
    private Map<String, Book> books; // A map to store books using bookId as the key

    public Library() {
        books = new HashMap<>();
    }

    // Add a book to the library
    public void addBook(Book book) {
        books.put(book.getBookId(), book);
    }

    // Remove a book from the library by bookId
    public void removeBook(String bookId) {
        if (books.containsKey(bookId)) {
            books.remove(bookId);
            System.out.println("Book with ID " + bookId + " has been removed.");
        } else {
            System.out.println("No book found with ID: " + bookId);
        }
    }

    // Update the quantity of a book
    public void updateBookQuantity(String bookId, int quantity) {
        Book book = books.get(bookId);
        if (book != null) {
            book.setQuantity(quantity);
            System.out.println("Updated quantity for book " + book.getTitle() + " to " + quantity);
        } else {
            System.out.println("No book found with ID: " + bookId);
        }
    }

    // List all books in the library
    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
            return;
        }
        System.out.println("\n--- Available Books ---");
        System.out.printf("%-10s %-30s %-20s %-10s\n", "Book ID", "Title", "Author", "Quantity");
        System.out.println("---------------------------------------------------------");
        for (Book book : books.values()) {
            System.out.printf("%-10s %-30s %-20s %-10d\n", book.getBookId(), book.getTitle(), book.getAuthor(), book.getQuantity());
        }
        System.out.println("---------------------------------------------------------");
    }

    // Search books by title, author, or bookId
    public void searchBook(String keyword, int searchOption) {
        boolean found = false;
        switch (searchOption) {
            case 1: // Search by title
                for (Book book : books.values()) {
                    if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                        found = true;
                        displayBookDetails(book);
                    }
                }
                break;
            case 2: // Search by author
                for (Book book : books.values()) {
                    if (book.getAuthor().toLowerCase().contains(keyword.toLowerCase())) {
                        found = true;
                        displayBookDetails(book);
                    }
                }
                break;
            case 3: // Search by bookId
                Book book = books.get(keyword);
                if (book != null) {
                    found = true;
                    displayBookDetails(book);
                }
                break;
            default:
                System.out.println("Invalid search option.");
        }

        if (!found) {
            System.out.println("No books found matching the search criteria.");
        }
    }

    // Display book details
    private void displayBookDetails(Book book) {
        System.out.printf("Book ID: %s\nTitle: %s\nAuthor: %s\nQuantity: %d\n", book.getBookId(), book.getTitle(), book.getAuthor(), book.getQuantity());
        System.out.println("----------------------------------------");
    }

    // Borrow a book
    public void borrowBook(String bookId, User user) {
        Book book = books.get(bookId);
        if (book != null) {
            if (book.getQuantity() > 0) {
                book.decreaseQuantity();
                user.borrowedBooks.add(book);
                System.out.println(user.getName() + " has borrowed the book: " + book.getTitle());
            } else {
                System.out.println("Sorry, the book is currently unavailable.");
            }
        } else {
            System.out.println("No book found with ID: " + bookId);
        }
    }

    // Return a book
    public void returnBook(String bookId, User user) {
        Book book = books.get(bookId);
        if (book != null) {
            if (user.borrowedBooks.contains(book)) {
                book.increaseQuantity();
                user.borrowedBooks.remove(book);
                System.out.println(user.getName() + " has returned the book: " + book.getTitle());
            } else {
                System.out.println("This book was not borrowed by the user.");
            }
        } else {
            System.out.println("No book found with ID: " + bookId);
        }
    }

    // Load books from a file (Deserialize)
    @SuppressWarnings("unchecked")
    public void loadBooksFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            books = (Map<String, Book>) ois.readObject();
            System.out.println("Books loaded from file successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading books from file: " + e.getMessage());
        }
    }

    // Save books to a file (Serialize)
    public void saveBooksToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(books);
            System.out.println("Books saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error saving books to file: " + e.getMessage());
        }
    }
}
