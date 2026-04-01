package com.example.bookstore.dao;

import com.example.bookstore.domain.Customer;
import com.example.bookstore.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDbDAO implements DAO<Customer> {
    private final ConnectionProperty prop;

    private static final String SELECT_ALL_CUSTOMERS = "SELECT id, firstname, lastname, email, phone FROM customers ORDER BY id";
    private static final String SELECT_CUSTOMER_BY_ID = "SELECT id, firstname, lastname, email, phone FROM customers WHERE id = ?";
    private static final String INSERT_CUSTOMER = "INSERT INTO customers (firstname, lastname, email, phone) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_CUSTOMER = "UPDATE customers SET firstname = ?, lastname = ?, email = ?, phone = ? WHERE id = ?";
    private static final String DELETE_CUSTOMER = "DELETE FROM customers WHERE id = ?";

    public CustomerDbDAO() throws Exception {
        this.prop = new ConnectionProperty();
    }

    @Override
    public Long insert(Customer customer) throws DAOException {
        try (Connection con = DriverManager.getConnection(prop.getUrl(), prop.getLogin(), prop.getPassword());
             PreparedStatement pst = con.prepareStatement(INSERT_CUSTOMER, new String[]{"id"})) {
            pst.setString(1, customer.getFirstName());
            pst.setString(2, customer.getLastName());
            pst.setString(3, customer.getEmail());
            pst.setString(4, customer.getPhone());
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
    public List<Customer> findAll() throws DAOException {
        List<Customer> customers = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(prop.getUrl(), prop.getLogin(), prop.getPassword());
             PreparedStatement pst = con.prepareStatement(SELECT_ALL_CUSTOMERS);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                customers.add(new Customer(
                    rs.getLong("id"),
                    rs.getString("firstname"),
                    rs.getString("lastname"),
                    rs.getString("email"),
                    rs.getString("phone")
                ));
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return customers;
    }

    @Override
    public Customer findById(Long id) throws DAOException {
        try (Connection con = DriverManager.getConnection(prop.getUrl(), prop.getLogin(), prop.getPassword());
             PreparedStatement pst = con.prepareStatement(SELECT_CUSTOMER_BY_ID)) {
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return new Customer(
                        rs.getLong("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("email"),
                        rs.getString("phone")
                    );
                }
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return null;
    }

    @Override
    public void update(Customer customer) throws DAOException {
        try (Connection con = DriverManager.getConnection(prop.getUrl(), prop.getLogin(), prop.getPassword());
             PreparedStatement pst = con.prepareStatement(UPDATE_CUSTOMER)) {
            pst.setString(1, customer.getFirstName());
            pst.setString(2, customer.getLastName());
            pst.setString(3, customer.getEmail());
            pst.setString(4, customer.getPhone());
            pst.setLong(5, customer.getId());
            pst.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(Long id) throws DAOException {
        try (Connection con = DriverManager.getConnection(prop.getUrl(), prop.getLogin(), prop.getPassword());
             PreparedStatement pst = con.prepareStatement(DELETE_CUSTOMER)) {
            pst.setLong(1, id);
            pst.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }
}
