package com.example.bookstore.servlet;

import com.example.bookstore.dao.CustomerDbDAO;
import com.example.bookstore.domain.Customer;
import com.example.bookstore.exception.DAOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/customers")
public class CustomersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerDbDAO dao;

    @Override
    public void init() throws ServletException {
        try {
            dao = new CustomerDbDAO();
        } catch (Exception e) {
            throw new ServletException("Failed to initialize CustomerDbDAO", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try {
            request.setAttribute("customers", dao.findAll());
        } catch (DAOException e) {
            e.printStackTrace();
            request.setAttribute("error", "Ошибка при загрузке списка покупателей: " + e.getMessage());
        }

        request.getRequestDispatcher("/customers.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String firstName = request.getParameter("inputFirstName");
        String lastName = request.getParameter("inputLastName");
        String email = request.getParameter("inputEmail");
        String phone = request.getParameter("inputPhone");

        Customer newCustomer = new Customer(firstName, lastName, email, phone);

        try {
            Long id = dao.insert(newCustomer);
            System.out.println("Adding customer result: " + id);
        } catch (DAOException e) {
            e.printStackTrace();
            request.setAttribute("error", "Ошибка при добавлении покупателя: " + e.getMessage());
        }

        doGet(request, response);
    }
}
