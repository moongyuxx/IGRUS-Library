package src;

import java.util.List;
import java.util.Scanner;

public class main {
    private static final String FILE_PATH = "books.csv";

    public static void main(String[] args) {
        BookManager bookManager = new BookManager();
        bookManager.getBooks().addAll(FileManager.readBooksFromFile(FILE_PATH));

        Scanner scanner = new Scanner(System.in);
        System.out.print("사용자 이름을 입력하세요: ");
        String userName = scanner.nextLine();

        while (true) {
            System.out.println("\n1. 도서 검색\n2. 도서 목록 출력\n3. 도서 대출\n4. 도서 반납\n5. 도서 추가(관리자 전용)\n6. 도서 삭제(관리자 전용)\n7. 대출자 목록 보기(관리자 전용)\n8. 종료");
            System.out.print("선택: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("검색 키워드: ");
                    String keyword = scanner.nextLine();
                    List<Book> searchResult = bookManager.searchBooks(keyword);
                    for (Book book : searchResult) {
                        System.out.println(book);
                    }
                    break;
                case "2":
                    for (Book book : bookManager.getBooks()) {
                        System.out.println(book);
                    }
                    break;
                case "3":
                    System.out.print("대출할 도서 ID: ");
                    String loanId = scanner.nextLine();
                    LoanManager.loanBook(bookManager.getBooks(), loanId, userName);
                    break;
                case "4":
                    System.out.print("반납할 도서 ID: ");
                    String returnId = scanner.nextLine();
                    LoanManager.returnBook(bookManager.getBooks(), returnId, userName);
                    break;
                case "5":
                    if (!userName.equals("manager")) {
                        System.out.println("권한이 없습니다.");
                        break;
                    }
                    System.out.print("도서 ID: ");
                    String id = scanner.nextLine();
                    System.out.print("도서 제목: ");
                    String title = scanner.nextLine();
                    System.out.print("저자: ");
                    String author = scanner.nextLine();
                    System.out.print("출판 연도: ");
                    int year = Integer.parseInt(scanner.nextLine());
                    bookManager.addBook(new Book(id, title, author, year));
                    break;
                case "6":
                    if (!userName.equals("manager")) {
                        System.out.println("권한이 없습니다.");
                        break;
                    }
                    System.out.print("삭제할 도서 ID: ");
                    String deleteId = scanner.nextLine();
                    bookManager.deleteBook(deleteId);
                    break;
                case "7":
                    if (!userName.equals("manager")) {
                        System.out.println("권한이 없습니다.");
                        break;
                    }
                    LoanManager.printLoanedBooks(bookManager.getBooks());
                    break;
                case "8":
                    FileManager.writeBooksToFile(FILE_PATH, bookManager.getBooks());
                    System.out.println("프로그램 종료");
                    scanner.close();
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }
}
