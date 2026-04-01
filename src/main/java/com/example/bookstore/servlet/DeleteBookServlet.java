package com.example.bookstore.servlet;

import com.example.bookstore.dao.BookDbDAO;
import com.example.bookstore.exception.DAOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deletebook")
public class DeleteBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookDbDAO dao;

    @Override
    public void init() throws ServletException {
        try {
            dao = new BookDbDAO();
        } catch (Exception e) {
            throw new ServletException("Failed to initialize BookDbDAO", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String strId = request.getParameter("id");
        Long deleteId = null;
        if (strId != null) {
            deleteId = Long.parseLong(strId);
        }

        try {
            dao.delete(deleteId);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        response.sendRedirect("books");
    }
}
