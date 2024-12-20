package src;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    public static List<Book> readBooksFromFile(String filePath) {
        List<Book> books = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    // 기본 도서 정보 생성
                    Book book = new Book(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]));
                    if (parts.length >= 7) { // 대출 정보가 있는 경우
                        book.loan(parts[4]); // 대출자 설정
                        try {
                            book.setLoanDate(LocalDate.parse(parts[5])); // 대출일 설정
                            book.setReturnDate(LocalDate.parse(parts[6])); // 반납일 설정
                        } catch (Exception e) {
                            System.out.println("날짜 파싱 오류: " + e.getMessage());
                        }
                    }
                    books.add(book);
                }
            }
        } catch (IOException e) {
            System.out.println("파일을 읽는 중 오류가 발생했습니다: " + e.getMessage());
        }
        return books;
    }

    public static void writeBooksToFile(String filePath, List<Book> books) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Book book : books) {
                // 기본 도서 정보
                StringBuilder sb = new StringBuilder();
                sb.append(book.getId()).append(",")
                        .append(book.getTitle()).append(",")
                        .append(book.getAuthor()).append(",")
                        .append(book.getYear());
                // 대출 정보 추가
                if (book.isLoaned()) {
                    sb.append(",").append(book.getLoanedBy())
                            .append(",").append(book.getLoanDate())
                            .append(",").append(book.getReturnDate());
                }
                bw.write(sb.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("파일에 쓰는 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}
