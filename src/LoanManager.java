package src;

import java.util.List;

public class LoanManager {

    /**
     * 도서를 대출 처리하는 메서드 (도서 제목으로 대출).
     * @param books 도서 리스트
     * @param title 대출할 도서 제목
     * @param userName 대출자 이름
     */
    public static void loanBookByTitle(List<Book> books, String title, String userName) {
        Book foundBook = null;
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                foundBook = book;
                break;
            }
        }

        if (foundBook == null) {
            System.out.println("일치하는 도서를 찾을 수 없습니다.");
            return;
        }

        if (foundBook.isLoaned()) {
            System.out.println("이미 대출된 도서입니다: " + foundBook.getLoanedBy() +
                    ", 대출일: " + foundBook.getLoanDate() +
                    ", 반납일: " + foundBook.getReturnDate());
        } else {
            foundBook.loan(userName); // Book 클래스의 loan 메서드 호출
            System.out.println("대출 완료: " + foundBook.getTitle() +
                    ", 대출일: " + foundBook.getLoanDate() +
                    ", 반납일: " + foundBook.getReturnDate());
        }
    }

    /**
     * 도서를 반납 처리하는 메서드 (도서 제목으로 반납).
     * @param books 도서 리스트
     * @param title 반납할 도서 제목
     * @param userName 반납자 이름
     */
    public static void returnBookByTitle(List<Book> books, String title, String userName) {
        Book foundBook = null;
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                foundBook = book;
                break;
            }
        }

        if (foundBook == null) {
            System.out.println("일치하는 도서를 찾을 수 없습니다.");
            return;
        }

        if (foundBook.isLoaned() && foundBook.getLoanedBy().equals(userName)) {
            foundBook.returnBook();
            System.out.println("반납 완료: " + foundBook.getTitle());
        } else {
            System.out.println("반납할 권한이 없거나 이미 반납된 도서입니다.");
        }
    }

    /**
     * 현재 대출 중인 도서와 대출자 목록을 출력하는 메서드.
     * @param books 도서 리스트
     */
    public static void printLoanedBooks(List<Book> books) {
        System.out.println("현재 대출 중인 도서 목록:");
        boolean hasLoans = false;
        for (Book book : books) {
            if (book.isLoaned()) {
                System.out.println(book.getId() + ", " + book.getTitle() +
                        " - 대출자: " + book.getLoanedBy() +
                        ", 대출일: " + book.getLoanDate() +
                        ", 반납일: " + book.getReturnDate());
                hasLoans = true;
            }
        }
        if (!hasLoans) {
            System.out.println("대출 중인 도서가 없습니다.");
        }
    }
}
