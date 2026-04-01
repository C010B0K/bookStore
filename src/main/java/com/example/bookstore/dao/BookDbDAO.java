package com.example.bookstore.dao;

import com.example.bookstore.domain.Book;
import com.example.bookstore.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDbDAO implements DAO<Book> {
    private final ConnectionProperty prop;

    private static final String SELECT_ALL_BOOKS = "SELECT id, author, title, publishing, seriya, year, price FROM books ORDER BY id";
    private static final String SELECT_BOOK_BY_ID = "SELECT id, author, title, publishing, seriya, year, price FROM books WHERE id = ?";
    private static final String INSERT_BOOK = "INSERT INTO books (author, title, publishing, seriya, year, price) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_BOOK = "UPDATE books SET author = ?, title = ?, publishing = ?, seriya = ?, year = ?, price = ? WHERE id = ?";
    private static final String DELETE_BOOK = "DELETE FROM books WHERE id = ?";

    public BookDbDAO() throws Exception {
        this.prop = new ConnectionProperty();
    }

    @Override
    public Long insert(Book book) throws DAOException {
        try (Connection con = DriverManager.getConnection(prop.getUrl(), prop.getLogin(), prop.getPassword());
             PreparedStatement pst = con.prepareStatement(INSERT_BOOK, new String[]{"id"})) {
            pst.setString(1, book.getAuthor());
            pst.setString(2, book.getTitle());
            pst.setString(3, book.getPublishing());
            pst.setString(4, book.getSeriya());
            pst.setInt(5, book.getYear());
            pst.setDouble(6, book.getPrice());
            pst.executeUpdate();

            try (ResultSet gk = pst.getGeneratedKeys()) {
                if (gk.next()) {
                    return gk.getLong("id");
                }
            }
            return -1L;
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Book> findAll() throws DAOException {
        List<Book> books = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(prop.getUrl(), prop.getLogin(), prop.getPassword());
             PreparedStatement pst = con.prepareStatement(SELECT_ALL_BOOKS);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                books.add(new Book(
                    rs.getLong("id"),
                    rs.getString("author"),
                    rs.getString("title"),
                    rs.getString("publishing"),
                    rs.getString("seriya"),
                    rs.getInt("year"),
                    rs.getDouble("price")
                ));
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return books;
    }

    @Override
    public Book findById(Long id) throws DAOException {
        try (Connection con = DriverManager.getConnection(prop.getUrl(), prop.getLogin(), prop.getPassword());
             PreparedStatement pst = con.prepareStatement(SELECT_BOOK_BY_ID)) {
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return new Book(
                        rs.getLong("id"),
                        rs.getString("author"),
                        rs.getString("title"),
                        rs.getString("publishing"),
                        rs.getString("seriya"),
                        rs.getInt("year"),
                        rs.getDouble("price")
                    );
                }
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return null;
    }

    @Override
    public void update(Book book) throws DAOException {
        try (Connection con = DriverManager.getConnection(prop.getUrl(), prop.getLogin(), prop.getPassword());
             PreparedStatement pst = con.prepareStatement(UPDATE_BOOK)) {
            pst.setString(1, book.getAuthor());
            pst.setString(2, book.getTitle());
            pst.setString(3, book.getPublishing());
            pst.setString(4, book.getSeriya());
            pst.setInt(5, book.getYear());
            pst.setDouble(6, book.getPrice());
            pst.setLong(7, book.getId());
            pst.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(Long id) throws DAOException {
        try (Connection con = DriverManager.getConnection(prop.getUrl(), prop.getLogin(), prop.getPassword());
             PreparedStatement pst = con.prepareStatement(DELETE_BOOK)) {
            pst.setLong(1, id);
            pst.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(prop.getUrl(), prop.getLogin(), prop.getPassword());
    }
}
