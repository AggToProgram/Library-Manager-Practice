import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static Path p = Paths.get("data");
    public static Path file = p.resolve("library.txt");
    public static Path file2 = p.resolve("clients.txt");
    public static Path file3 = p.resolve("loans.txt");

    public static String managerUser = "LibraryManager123";
    public static String managerPass = "Library123";

    private static final ArrayList<Book> newBooks = new ArrayList<>();
    private static final ArrayList<Book> books = new ArrayList<>();
    private static final ArrayList<Client> clients = new ArrayList<>();
    private static final ArrayList<Loan> loans = new ArrayList<>();

    private static final Scanner sc = new Scanner(System.in);

    public static boolean running = true;

    public static void main(String[] args) {
        System.out.println("Hello and welcome to the Megalovania Library!");

        try (BufferedReader br = Files.newBufferedReader(file, Charset.defaultCharset())){
            String line;
            while ((line = br.readLine()) != null){
                String[] parts = line.split(";");
                int id = Integer.parseInt(parts[0]);
                String title = parts[1];
                String author = parts[2];
                String publishDate = parts[3];
                int stock = Integer.parseInt(parts[4]);
                boolean loaned = Boolean.parseBoolean(parts[5]);

                books.add(new Book(id, title, author, publishDate, stock));
            }
        } catch (IOException ioe){
            System.out.println(ioe.getMessage());
        }

        try (BufferedReader br = Files.newBufferedReader(file2, Charset.defaultCharset())){
            String line;
            while ((line = br.readLine()) != null){
                String[] parts = line.split(";");
                String name = parts[0];
                String email = parts[1];
                String password = parts[2];
                int id = Integer.parseInt(parts[3]);

                clients.add(new Client(name, email, password, id));
            }
        } catch (IOException ioe){
            System.out.println(ioe.getMessage());
        }

        try (BufferedReader br = Files.newBufferedReader(file3, Charset.defaultCharset())){
            String line;
            while ((line = br.readLine()) != null){
                String[] parts = line.split(";");
                String bookTitle = parts[0];
                String client = parts[1];
                int amount = Integer.parseInt(parts[2]);

                loans.add(new Loan(bookTitle, client, amount));
            }
        } catch (IOException ioe){
            System.out.println(ioe.getMessage());
        }


        while (running) {
            System.out.println("What do you wish to do?");
            System.out.println("1: Access as Library Manager");
            System.out.println("2: Access as Client");
            System.out.println("3: Exit");
            int input = sc.nextInt();

            switch (input) {
                case 1:
                    accessAsManager();
                    break;
                case 2:
                    accessAsClient();
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    public static void accessAsClient() {
        boolean run = true;
        while (run) {
            System.out.println("1: Create account");
            System.out.println("2: Log in");
            System.out.println("3: Back");
            sc.nextLine();
            int input = sc.nextInt();

            if (input == 1) {
                clients.add(new Client());
                try (BufferedWriter br = Files.newBufferedWriter(file2, StandardOpenOption.APPEND)) {
                    for (Client client : clients) {
                        br.newLine();
                        String line = client.toString();
                        br.write(line);
                    }
                    clients.clear();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            } else if (input == 2) {
                System.out.println("Input email/username: ");
                sc.nextLine();
                String name = sc.nextLine();

                for (Client client : clients) {
                    if (client.getEmail().equals(name) || client.getName().equals(name)) {
                        System.out.println("Input password: ");
                        String pass = sc.nextLine();

                        if (client.getPassword().equals(pass)) {
                            System.out.println("Access granted!");
                            System.out.println("What do you wish to do now?");

                            postAccessClient();
                        } else {
                            System.out.println("Wrong password");
                        }
                    }
                }
            } else if (input == 3) {
                run = false;
            } else {
                System.out.println("Invalid option");
            }
        }
    }

    public static void postAccessClient() {
        System.out.println("1: Rent a book");
        System.out.println("2: Check rented books");
        System.out.println("3: Return rented book");
        System.out.println("4: Back");
        int input = sc.nextInt();
    }

    public static void accessAsManager() {
        boolean run = true;
        System.out.print("Input user: ");
        sc.nextLine();
        String user = sc.nextLine();

        if (user.equals(managerUser)) {
            System.out.print("Input password: ");
            String pass = sc.nextLine();

            if (pass.equals(managerPass)) {
                System.out.println("Access granted!");

                while (run) {
                    System.out.println("What do you wish to do now?");
                    System.out.println("1: Check book info");
                    System.out.println("2: Check available book stock");
                    System.out.println("3: Check loaned books");
                    System.out.println("4: Add a new book");
                    System.out.println("5: Back");
                    ;
                    int input = sc.nextInt();

                    if (input == 1) {
                        System.out.println("Input title of book to check info: ");
                        sc.nextLine();
                        String title = sc.nextLine();
                        getBookInfo(title);
                    } else if (input == 2) {
                        getBookStock();
                    } else if (input == 3){
                        getLoanedBooks();
                    } else if (input == 4) {
                        addBook();
                    } else if (input == 5) {
                        run = false;
                    }
                }
            }
        }
    }

    public static void changePass() {
        System.out.println("Type your new password: ");
        sc.nextLine();
        managerPass = sc.nextLine();
    }

    public static Book findBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public static void getBookStock() {
        for (Book book : books){
            System.out.println(book.getTitle() + " has " + book.getStock() + " available copies");
        }
    }

    public static void getLoanedBooks(){
        for (Loan loan : loans){
            System.out.println(loan.toString());
        }
    }

    public static void addBook() {
        boolean run = true;
        while (run) {
            System.out.println("Type in ID: ");
            sc.nextLine();
            int id = sc.nextInt();
            System.out.println("Type in book title: ");
            sc.nextLine();
            String title = sc.nextLine();
            System.out.println("Type in author: ");
            String author = sc.nextLine();
            System.out.println("Type in publishing date: ");
            String publishDate = sc.nextLine();
            System.out.println("Input stock: ");
            int stock = sc.nextInt();

            newBooks.add(new Book(id, title, author, publishDate, stock));
            books.add(new Book(id, title, author, publishDate, stock));

            System.out.println("Do you wish to add any other book? Y/N");
            sc.nextLine();
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("N")) {
                try (BufferedWriter br = Files.newBufferedWriter(file, StandardOpenOption.APPEND)) {
                    for (Book book : newBooks) {
                        br.newLine();
                        String line = book.toString();
                        br.write(line);
                        System.out.println(book.getTitle() + " added to the library");
                    }
                    newBooks.clear();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
                run = false;
            }
        }
    }

    public static String getBookInfo(String title) {
        Book book = findBook(title);
        if (book == null){
            System.out.println("Book not found");
        } else {
            return book.toString();
        }
        return null;
    }
}
