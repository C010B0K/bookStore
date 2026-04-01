package com.example.bookstore.dao;

import com.example.bookstore.exception.DAOException;
import java.util.List;

public interface DAO<T> {
    Long insert(T entity) throws DAOException;
    List<T> findAll() throws DAOException;
    T findById(Long id) throws DAOException;
    void update(T entity) throws DAOException;
    void delete(Long id) throws DAOException;
}
