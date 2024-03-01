package LMS.dao;

import LMS.dto.Book;

import java.sql.*;
import java.util.*;

public class BookDao {
    public Book getBooksByarno(Connection conn, int srNo) throws SQLException {
        String query = "select * from books where ar_no = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, srNo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Book book = new Book();
                    book.setAuthorName(rs.getString("author_name"));
                    book.setBookName(rs.getString("name"));
                    book.setBookQty(rs.getInt("qty"));
                    book.setId(rs.getInt("id"));
                    book.setarNo(rs.getInt("ar_no"));

                    return book;
                }
            }
        }

        return null;
    }

    public Book getBooksByAuthorName(Connection conn, String authorName) throws SQLException {
        String query = "select * from books where author_name = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, authorName);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Book book = new Book();
                    book.setAuthorName(rs.getString("author_name"));
                    book.setBookName(rs.getString("name"));
                    book.setBookQty(rs.getInt("qty"));
                    book.setId(rs.getInt("id"));
                    book.setarNo(rs.getInt("ar_no"));

                    return book;
                }
            }
        }

        return null;
    }

    public Book getBooksBySnoOrBookName(Connection conn, String authorName, int srNo) throws SQLException {
        String query = "select * from books where author_name = ? or ar_no = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, authorName);
            ps.setInt(2, srNo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Book book = new Book();
                    book.setAuthorName(rs.getString("author_name"));
                    book.setBookName(rs.getString("name"));
                    book.setBookQty(rs.getInt("qty"));
                    book.setId(rs.getInt("id"));
                    book.setarNo(rs.getInt("ar_no"));
                    return book;
                }
            }
        }

        return null;
    }

    public void saveBook(Connection conn, Book book) throws SQLException {
        String query = "insert into books (ar_no, name, author_name, qty) values (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, book.getarNo());
            ps.setString(2, book.getBookName());
            ps.setString(3, book.getAuthorName());
            ps.setInt(4, book.getBookQty());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Book added successfully.");
            } else {
                System.out.println("Failed to add book.");
            }
        }
    }

    public List<Book> getAllBooks(Connection conn) throws SQLException {
        String query = "select * from books";

        List<Book> books = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Book book = new Book();
                    book.setAuthorName(rs.getString("author_name"));
                    book.setBookName(rs.getString("name"));
                    book.setBookQty(rs.getInt("qty"));
                    book.setId(rs.getInt("id"));
                    book.setarNo(rs.getInt("sr_no"));

                    books.add(book);
                }
            }
        }

        return books;
    }

    public void updateBookQty(Connection conn, Book book) throws SQLException {
        String query = "update books set qty = ? where sr_no = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, book.getBookQty());
            ps.setInt(2, book.getarNo());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Book updated successfully.");
            } else {
                System.out.println("Failed to update book.");
            }
        }

    }
}
