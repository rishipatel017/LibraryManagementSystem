package in.ac.adit.pwj.miniproject.library;

import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static Library library = new Library();
    static List<User> users = new ArrayList<>();

    public static void main(String[] args) {
        // Load data from files
        library.loadBooksFromFile("books.dat");
        loadUsersFromFile("users.dat");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = sc.nextInt();
            sc.nextLine(); // Clear buffer

            switch (choice) {
                case 1 -> addUser();
                case 2 -> listUsers();
                case 3 -> addBook();
                case 4 -> removeBook();
                case 5 -> updateBookQuantity();
                case 6 -> library.listBooks();
                case 7 -> searchBook();
                case 8 -> borrowBook();
                case 9 -> returnBook();
                case 10 -> {
                    // Save data to files before exiting
                    library.saveBooksToFile("books.dat");
                    saveUsersToFile("users.dat");
                    System.out.println("Data saved. Exiting...");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // Print the main menu for user interaction
    static void printMenu() {
        System.out.println("\n====== Library Management System ======");
        System.out.println("1. Add User");
        System.out.println("2. List All Users");
        System.out.println("-------------------------------");
        System.out.println("3. Add Book");
        System.out.println("4. Remove Book");
        System.out.println("5. Update Book Quantity");
        System.out.println("6. List All Books");
        System.out.println("7. Search Book");
        System.out.println("-------------------------------");
        System.out.println("8. Borrow Book");
        System.out.println("9. Return Book");
        System.out.println("-------------------------------");
        System.out.println("10. Save & Exit");
        System.out.print("Enter your choice: ");
    }

    // Search for books by title, author, or ID
    static void searchBook() {
        System.out.println("Search by: ");
        System.out.println("1. Book Title");
        System.out.println("2. Author Name");
        System.out.println("3. Book ID");
        System.out.print("Enter search option: ");
        int option = sc.nextInt();
        sc.nextLine(); // Clear buffer

        System.out.print("Enter search keyword: ");
        String keyword = sc.nextLine();

        library.searchBook(keyword, option);
    }

    // Add a new user (Student or Faculty)
    static void addUser() {
        System.out.print("Enter User ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Type (1=Student, 2=Faculty): ");
        int type = sc.nextInt();
        sc.nextLine(); // Clear buffer

        if (id.isEmpty() || name.isEmpty()) {
            System.out.println("Error: User ID and Name cannot be empty.");
            return;
        }

        if (findUserById(id) != null) {
            System.out.println("Error: A user with this ID already exists.");
            return;
        }

        User user = (type == 1) ? new Student(id, name) : new Faculty(id, name);
        users.add(user);
        System.out.println("User added successfully.");
    }

    // List all registered users
    static void listUsers() {
        if (users.isEmpty()) {
            System.out.println("No users registered.");
            return;
        }

        // Sort users by type (Student first, Faculty second) and then by name
        users.sort(Comparator.comparing((User u) -> u instanceof Student ? "Student" : "Faculty")
                             .thenComparing(u -> u.name));

        System.out.println("\n--- Registered Users ---");
        System.out.printf("%-10s %-20s %-10s\n", "ID", "Name", "Type");
        System.out.println("----------------------------------------");

        String currentType = null; // To track the current user type for grouping
        for (User u : users) {
            String userType = u instanceof Student ? "Student" : "Faculty";

            // Print a separator when switching between user types
            if (currentType == null || !currentType.equals(userType)) {
                if (currentType != null) {
                    System.out.println("----------------------------------------");
                }
                System.out.println("\n=== " + userType + "s ===");
                currentType = userType;
            }

            System.out.printf("%-10s %-20s %-10s\n", u.userId, u.name, userType);
        }
        System.out.println("----------------------------------------");
    }

    // Add a new book to the library
    static void addBook() {
        System.out.print("Enter Book id: ");
        String bookId = sc.nextLine();
        System.out.print("Enter Book Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Author Name: ");
        String author = sc.nextLine();
        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();
        sc.nextLine(); // Clear buffer

        if (title.isEmpty() || author.isEmpty() || qty <= 0) {
            System.out.println("Error: Title, author, and quantity must be provided.");
            return;
        }

        Book book = new Book(bookId, title, author, qty);
        library.addBook(book);
        System.out.println("Book added successfully.");
    }

    // Remove a book from the library by book ID
    static void removeBook() {
        System.out.print("Enter Book ID to Remove: ");
        String bookId = sc.nextLine();
        library.removeBook(bookId);
        System.out.println("Book removed if it existed.");
    }

    // Update the quantity of a book in the library
    static void updateBookQuantity() {
        System.out.print("Enter Book ID: ");
        String bookId = sc.nextLine();
        System.out.print("Enter New Quantity: ");
        int qty = sc.nextInt();
        sc.nextLine(); // Clear buffer

        library.updateBookQuantity(bookId, qty);
        System.out.println("Book quantity updated.");
    }

    // Borrow a book for a user
    static void borrowBook() {
        System.out.print("Enter User ID: ");
        String uid = sc.nextLine();
        User user = findUserById(uid);

        if (user == null) {
            System.out.println("Error: No user found with ID '" + uid + "'.");
            return;
        }

        System.out.print("Enter Book ID to Borrow: ");
        String bookId = sc.nextLine();
        try {
            user.borrowBook(library, bookId);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Return a book for a user
    static void returnBook() {
        System.out.print("Enter User ID: ");
        String uid = sc.nextLine();
        User user = findUserById(uid);

        if (user == null) {
            System.out.println("Error: No user found with ID '" + uid + "'.");
            return;
        }

        System.out.print("Enter Book ID to Return: ");
        String bookId = sc.nextLine();
        user.returnBook(library, bookId);
    }

    // Find a user by their user ID
    static User findUserById(String userId) {
        return users.stream()
                    .filter(u -> u.userId.equals(userId))
                    .findFirst()
                    .orElse(null);
    }

    // Load users from a file (this method assumes you have the code for file loading)
    static void loadUsersFromFile(String filename) {
        users = UserDataManager.loadUsers(filename);
    }

    // Save users to a file
    static void saveUsersToFile(String filename) {
        UserDataManager.saveUsers(users, filename);
    }
}
