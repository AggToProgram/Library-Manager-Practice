import java.util.Scanner;

public class Loan {
    private String bookTitle, client;
    private int amount;

    public Loan(String bookTitle, String client, int amount){
        this.bookTitle = bookTitle;
        this.client = client;
        this.amount = amount;
    }

    public void setBookTitle(String bookTitle) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input the book you wish to loan: ");

    }

    @Override
    public String toString() {
        return client + " has loaned " + amount + " copy/ies of " + bookTitle;
    }
}
