package com.example.bookstore.dao;

import com.example.bookstore.domain.Sale;
import com.example.bookstore.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SaleDbDAO implements DAO<Sale> {
    private final ConnectionProperty prop;

    private static final String SELECT_ALL_SALES = "SELECT id, firstname, lastname, position, employment_date, date_birth, email FROM sales ORDER BY id";
    private static final String SELECT_SALE_BY_ID = "SELECT id, firstname, lastname, position, employment_date, date_birth, email FROM sales WHERE id = ?";
    private static final String INSERT_SALE = "INSERT INTO sales (firstname, lastname, position, employment_date, date_birth, email) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_SALE = "UPDATE sales SET firstname = ?, lastname = ?, position = ?, employment_date = ?, date_birth = ?, email = ? WHERE id = ?";
    private static final String DELETE_SALE = "DELETE FROM sales WHERE id = ?";

    public SaleDbDAO() throws Exception {
        this.prop = new ConnectionProperty();
    }

    @Override
    public Long insert(Sale sale) throws DAOException {
        try (Connection con = DriverManager.getConnection(prop.getUrl(), prop.getLogin(), prop.getPassword());
             PreparedStatement pst = con.prepareStatement(INSERT_SALE, new String[]{"id"})) {
            pst.setString(1, sale.getFirstName());
            pst.setString(2, sale.getLastName());
            pst.setString(3, sale.getPosition());
            pst.setDate(4, sale.getEmploymentDate() != null ? Date.valueOf(sale.getEmploymentDate()) : null);
            pst.setDate(5, sale.getDateBirth() != null ? Date.valueOf(sale.getDateBirth()) : null);
            pst.setString(6, sale.getEmail());
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
    public List<Sale> findAll() throws DAOException {
        List<Sale> sales = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(prop.getUrl(), prop.getLogin(), prop.getPassword());
             PreparedStatement pst = con.prepareStatement(SELECT_ALL_SALES);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                sales.add(new Sale(
                    rs.getLong("id"),
                    rs.getString("firstname"),
                    rs.getString("lastname"),
                    rs.getString("position"),
                    rs.getDate("employment_date") != null ? rs.getDate("employment_date").toLocalDate() : null,
                    rs.getDate("date_birth") != null ? rs.getDate("date_birth").toLocalDate() : null,
                    rs.getString("email")
                ));
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return sales;
    }

    @Override
    public Sale findById(Long id) throws DAOException {
        try (Connection con = DriverManager.getConnection(prop.getUrl(), prop.getLogin(), prop.getPassword());
             PreparedStatement pst = con.prepareStatement(SELECT_SALE_BY_ID)) {
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return new Sale(
                        rs.getLong("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("position"),
                        rs.getDate("employment_date") != null ? rs.getDate("employment_date").toLocalDate() : null,
                        rs.getDate("date_birth") != null ? rs.getDate("date_birth").toLocalDate() : null,
                        rs.getString("email")
                    );
                }
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return null;
    }

    @Override
    public void update(Sale sale) throws DAOException {
        try (Connection con = DriverManager.getConnection(prop.getUrl(), prop.getLogin(), prop.getPassword());
             PreparedStatement pst = con.prepareStatement(UPDATE_SALE)) {
            pst.setString(1, sale.getFirstName());
            pst.setString(2, sale.getLastName());
            pst.setString(3, sale.getPosition());
            pst.setDate(4, sale.getEmploymentDate() != null ? Date.valueOf(sale.getEmploymentDate()) : null);
            pst.setDate(5, sale.getDateBirth() != null ? Date.valueOf(sale.getDateBirth()) : null);
            pst.setString(6, sale.getEmail());
            pst.setLong(7, sale.getId());
            pst.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(Long id) throws DAOException {
        try (Connection con = DriverManager.getConnection(prop.getUrl(), prop.getLogin(), prop.getPassword());
             PreparedStatement pst = con.prepareStatement(DELETE_SALE)) {
            pst.setLong(1, id);
            pst.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }
}
