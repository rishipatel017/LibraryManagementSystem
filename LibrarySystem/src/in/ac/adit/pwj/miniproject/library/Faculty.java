package in.ac.adit.pwj.miniproject.library;

class Faculty extends User {
    public Faculty(String userId, String name) {
        super(userId, name);
    }

    @Override
    public void borrowBook(Library library, String bookTitle) {
        library.borrowBook(bookTitle, this);
    }

    @Override
    public void returnBook(Library library, String bookTitle) {
        library.returnBook(bookTitle, this);
    }
}
