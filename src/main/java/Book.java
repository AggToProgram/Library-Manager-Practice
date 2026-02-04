import java.util.Scanner;

public class Book {
    private final Scanner sc = new Scanner(System.in);

    private final int id;
    private final String title;
    private final String author;
    private final String publishDate;
    private final int stock;

    public Book(int id, String title, String author, String publishDate, int stock){
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishDate = publishDate;
        this.stock = stock;
    }

    public Book(){
        this.id = setId();
        this.title = setTitle();
        this.author = setAuthor();
        this.publishDate = setPublishDate();
        this.stock = setStock();
    }

    public int setId(){return (int) (Math.random() * 100);}

    public String setTitle(){
        System.out.println("Type in title: ");
        return sc.nextLine();
    }

    public String setAuthor(){
        System.out.println("Type in author: ");
        return sc.nextLine();
    }

    public String setPublishDate(){
        System.out.println("Type in publish date: ");
        return sc.nextLine();
    }

    public int setStock(){
        System.out.println("Type in stock: ");
        return sc.nextInt();
    }

    public String getTitle() {
        return title;
    }
    public int getStock(){
        return stock;
    }

    public String toStr() {
        return this.id + ";" + this.title + ";" + this.author + ";" + this.publishDate + ";" + this.stock;
    }
}
