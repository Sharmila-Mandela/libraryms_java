import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private boolean isBorrowed;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void borrowBook() {
        if (!isBorrowed) {
            isBorrowed = true;
        }
    }

    public void returnBook() {
        if (isBorrowed) {
            isBorrowed = false;
        }
    }
}

class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void listBooks() {
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            System.out.println((i + 1) + ". " + book.getTitle() + " by " + book.getAuthor() + " ("
                    + (book.isBorrowed() ? "Borrowed" : "Available") + ")");
        }
    }

    public void borrowBook(int index) {
        if (index >= 0 && index < books.size()) {
            Book book = books.get(index);
            if (!book.isBorrowed()) {
                book.borrowBook();
                System.out.println("You borrowed: " + book.getTitle());
            } else {
                System.out.println("Sorry, the book is already borrowed.");
            }
        } else {
            System.out.println("Invalid book index.");
        }
    }

    public void returnBook(int index) {
        if (index >= 0 && index < books.size()) {
            Book book = books.get(index);
            if (book.isBorrowed()) {
                book.returnBook();
                System.out.println("You returned: " + book.getTitle());
            } else {
                System.out.println("The book is not borrowed.");
            }
        } else {
            System.out.println("Invalid book index.");
        }
    }
}

public class LMS {
    private static Scanner scanner = new Scanner(System.in);
    private static Library library = new Library();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nLibrary Management System");

            System.out.println("1. List all books");
            System.out.println("2. Add a new book");
            System.out.println("3. Borrow a book");
            System.out.println("4. Return a book");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    library.listBooks();
                    break;
                case 2:
                    addNewBook();
                    break;
                case 3:
                    borrowBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addNewBook() {
        System.out.print("Enter the title of the book: ");
        String title = scanner.nextLine();
        System.out.print("Enter the author of the book: ");
        String author = scanner.nextLine();
        library.addBook(new Book(title, author));
        System.out.println("Book added successfully!");
    }

    private static void borrowBook() {
        System.out.print("Enter the book index to borrow: ");
        int index = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        library.borrowBook(index - 1);
    }

    private static void returnBook() {
        System.out.print("Enter the book index to return: ");
        int index = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        library.returnBook(index - 1);
    }
}