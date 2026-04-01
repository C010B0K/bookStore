package com.example.bookstore.servlet;

import com.example.bookstore.dao.OrderDbDAO;
import com.example.bookstore.exception.DAOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deleteorder")
public class DeleteOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrderDbDAO dao;

    @Override
    public void init() throws ServletException {
        try {
            dao = new OrderDbDAO();
        } catch (Exception e) {
            throw new ServletException("Failed to initialize OrderDbDAO", e);
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

        response.sendRedirect("orders");
    }
}
