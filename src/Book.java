package src;

import java.time.LocalDate;

public class Book {
    private String id;
    private String title;
    private String author;
    private int year;
    private boolean isLoaned;
    private String loanedBy;
    private LocalDate loanDate;   // 대출일
    private LocalDate returnDate; // 반납일

    public Book(String id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.isLoaned = false;
        this.loanedBy = null;
        this.loanDate = null;   // 초기값은 null
        this.returnDate = null;
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

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    // 대출 처리
    public void loan(String userName) {
        this.isLoaned = true;
        this.loanedBy = userName;
        this.loanDate = LocalDate.now();           // 현재 날짜를 대출일로 설정
        this.returnDate = loanDate.plusWeeks(2);  // 대출일 + 2주를 반납일로 설정
    }

    // 반납 처리
    public void returnBook() {
        this.isLoaned = false;
        this.loanedBy = null;
        this.loanDate = null;
        this.returnDate = null;
    }

    // loanDate 설정 메서드
    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    // returnDate 설정 메서드
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return id + ", " + title + ", " + author + ", " + year +
                (isLoaned ? " (대출 중: " + loanedBy +
                        ", 대출일: " + loanDate +
                        ", 반납일: " + returnDate + ")" : "");
    }
}
