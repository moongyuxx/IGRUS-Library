package src;
import java.io.*;
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
                    Book book = new Book(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]));
                    if (parts.length == 5) { // 대출 정보가 있는 경우
                        book.loan(parts[4]);
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
                bw.write(book.getId() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.getYear() +
                        (book.isLoaned() ? "," + book.getLoanedBy() : ""));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("파일에 쓰는 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}
