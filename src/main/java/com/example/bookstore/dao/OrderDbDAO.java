package com.example.bookstore.dao;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.Customer;
import com.example.bookstore.domain.Order;
import com.example.bookstore.domain.Sale;
import com.example.bookstore.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDbDAO implements DAO<Order> {
    private final ConnectionProperty prop;
    private final BookDbDAO bookDao;
    private final CustomerDbDAO customerDao;
    private final SaleDbDAO saleDao;

    private static final String SELECT_ALL_ORDERS = "SELECT id, id_book, id_customer, id_sale, date_input, date_output, quantity, total_sum FROM orders ORDER BY id";
    private static final String SELECT_ORDER_BY_ID = "SELECT id, id_book, id_customer, id_sale, date_input, date_output, quantity, total_sum FROM orders WHERE id = ?";
    private static final String INSERT_ORDER = "INSERT INTO orders (id_book, id_customer, id_sale, date_input, date_output, quantity, total_sum) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_ORDER = "UPDATE orders SET id_book = ?, id_customer = ?, id_sale = ?, date_input = ?, date_output = ?, quantity = ?, total_sum = ? WHERE id = ?";
    private static final String DELETE_ORDER = "DELETE FROM orders WHERE id = ?";

    public OrderDbDAO() throws Exception {
        this.prop = new ConnectionProperty();
        this.bookDao = new BookDbDAO();
        this.customerDao = new CustomerDbDAO();
        this.saleDao = new SaleDbDAO();
    }

    @Override
    public Long insert(Order order) throws DAOException {
        try (Connection con = DriverManager.getConnection(prop.getUrl(), prop.getLogin(), prop.getPassword());
             PreparedStatement pst = con.prepareStatement(INSERT_ORDER, new String[]{"id"})) {
            pst.setLong(1, order.getIdBook());
            pst.setLong(2, order.getIdCustomer());
            pst.setLong(3, order.getIdSale());
            pst.setDate(4, order.getDateInput() != null ? Date.valueOf(order.getDateInput()) : null);
            pst.setDate(5, order.getDateOutput() != null ? Date.valueOf(order.getDateOutput()) : null);
            pst.setInt(6, order.getQuantity());
            pst.setDouble(7, order.getTotalSum());
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
    public List<Order> findAll() throws DAOException {
        List<Order> orders = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(prop.getUrl(), prop.getLogin(), prop.getPassword());
             PreparedStatement pst = con.prepareStatement(SELECT_ALL_ORDERS);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Order order = new Order(
                    rs.getLong("id"),
                    rs.getLong("id_book"),
                    rs.getLong("id_customer"),
                    rs.getLong("id_sale"),
                    rs.getDate("date_input") != null ? rs.getDate("date_input").toLocalDate() : null,
                    rs.getDate("date_output") != null ? rs.getDate("date_output").toLocalDate() : null,
                    rs.getInt("quantity"),
                    rs.getDouble("total_sum")
                );
                // Загружаем связанные объекты
                order.setBook(bookDao.findById(order.getIdBook()));
                order.setCustomer(customerDao.findById(order.getIdCustomer()));
                order.setSale(saleDao.findById(order.getIdSale()));
                orders.add(order);
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return orders;
    }

    @Override
    public Order findById(Long id) throws DAOException {
        try (Connection con = DriverManager.getConnection(prop.getUrl(), prop.getLogin(), prop.getPassword());
             PreparedStatement pst = con.prepareStatement(SELECT_ORDER_BY_ID)) {
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    Order order = new Order(
                        rs.getLong("id"),
                        rs.getLong("id_book"),
                        rs.getLong("id_customer"),
                        rs.getLong("id_sale"),
                        rs.getDate("date_input") != null ? rs.getDate("date_input").toLocalDate() : null,
                        rs.getDate("date_output") != null ? rs.getDate("date_output").toLocalDate() : null,
                        rs.getInt("quantity"),
                        rs.getDouble("total_sum")
                    );
                    // Загружаем связанные объекты
                    order.setBook(bookDao.findById(order.getIdBook()));
                    order.setCustomer(customerDao.findById(order.getIdCustomer()));
                    order.setSale(saleDao.findById(order.getIdSale()));
                    return order;
                }
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return null;
    }

    @Override
    public void update(Order order) throws DAOException {
        try (Connection con = DriverManager.getConnection(prop.getUrl(), prop.getLogin(), prop.getPassword());
             PreparedStatement pst = con.prepareStatement(UPDATE_ORDER)) {
            pst.setLong(1, order.getIdBook());
            pst.setLong(2, order.getIdCustomer());
            pst.setLong(3, order.getIdSale());
            pst.setDate(4, order.getDateInput() != null ? Date.valueOf(order.getDateInput()) : null);
            pst.setDate(5, order.getDateOutput() != null ? Date.valueOf(order.getDateOutput()) : null);
            pst.setInt(6, order.getQuantity());
            pst.setDouble(7, order.getTotalSum());
            pst.setLong(8, order.getId());
            pst.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(Long id) throws DAOException {
        try (Connection con = DriverManager.getConnection(prop.getUrl(), prop.getLogin(), prop.getPassword());
             PreparedStatement pst = con.prepareStatement(DELETE_ORDER)) {
            pst.setLong(1, id);
            pst.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }
}
