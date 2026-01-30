
public class Book {
    private int id;
    private String title;
    private String author;
    private String publishDate;
    private int stock;

    public Book(int id, String title, String author, String publishDate, int stock){
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishDate = publishDate;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public int getStock(){
        return stock;
    }

    @Override
    public String toString() {
        return this.id + ";" + this.title + ";" + this.author + ";" + this.publishDate + ";" + this.stock;
    }
}
