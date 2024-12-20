package src;

public class Book {
    private String id;
    private String title;
    private String author;
    private int year;
    private boolean isLoaned;
    private String loanedBy;

    public Book(String id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.isLoaned = false;
        this.loanedBy = null;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public boolean isLoaned() {
        return isLoaned;
    }

    public String getLoanedBy() {
        return loanedBy;
    }

    public void loan(String userName) {
        this.isLoaned = true;
        this.loanedBy = userName;
    }

    public void returnBook() {
        this.isLoaned = false;
        this.loanedBy = null;
    }

    @Override
    public String toString() {
        return id + ", " + title + ", " + author + ", " + year + (isLoaned ? " (대출 중: " + loanedBy + ")" : "");
    }
}
