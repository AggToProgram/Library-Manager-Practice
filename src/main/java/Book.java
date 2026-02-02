
public class Book {
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
