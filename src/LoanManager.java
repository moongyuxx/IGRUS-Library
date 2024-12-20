package src;

import java.util.List;

public class LoanManager {
    public static void loanBook(List<Book> books, String bookId, String userName) {
        for (Book book : books) {
            if (book.getId().equals(bookId)) {
                if (book.isLoaned()) {
                    System.out.println("이미 대출된 도서입니다: " + book.getLoanedBy());
                } else {
                    book.loan(userName);
                    System.out.println("대출 완료: " + book.getTitle());
                }
                return;
            }
        }
        System.out.println("해당 ID의 도서를 찾을 수 없습니다.");
    }

    public static void returnBook(List<Book> books, String bookId, String userName) {
        for (Book book : books) {
            if (book.getId().equals(bookId)) {
                if (book.isLoaned() && book.getLoanedBy().equals(userName)) {
                    book.returnBook();
                    System.out.println("반납 완료: " + book.getTitle());
                } else {
                    System.out.println("반납할 권한이 없거나 이미 반납된 도서입니다.");
                }
                return;
            }
        }
        System.out.println("해당 ID의 도서를 찾을 수 없습니다.");
    }

    public static void printLoanedBooks(List<Book> books) {
        System.out.println("현재 대출 중인 도서 목록:");
        boolean hasLoans = false;
        for (Book book : books) {
            if (book.isLoaned()) {
                System.out.println(book.getId() + ", " + book.getTitle() + " - 대출자: " + book.getLoanedBy());
                hasLoans = true;
            }
        }
        if (!hasLoans) {
            System.out.println("대출 중인 도서가 없습니다.");
        }
    }
}
