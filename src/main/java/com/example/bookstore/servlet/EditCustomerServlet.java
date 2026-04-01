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

@WebServlet("/editcustomer")
public class EditCustomerServlet extends HttpServlet {
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

        String strId = request.getParameter("id");
        Long id = null;
        if (strId != null) {
            id = Long.parseLong(strId);
        }

        Customer editCustomer = null;
        try {
            editCustomer = dao.findById(id);
        } catch (DAOException e) {
            e.printStackTrace();
            request.setAttribute("error", "Покупатель не найден: " + e.getMessage());
        }

        request.setAttribute("customerEdit", editCustomer);
        request.getRequestDispatcher("/editCustomer.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String strId = request.getParameter("id");
        Long id = null;
        if (strId != null) {
            id = Long.parseLong(strId);
        }

        String firstName = request.getParameter("inputFirstName");
        String lastName = request.getParameter("inputLastName");
        String email = request.getParameter("inputEmail");
        String phone = request.getParameter("inputPhone");

        Customer editCustomer = new Customer(id, firstName, lastName, email, phone);

        try {
            dao.update(editCustomer);
        } catch (DAOException e) {
            e.printStackTrace();
            request.setAttribute("error", "Ошибка при обновлении покупателя: " + e.getMessage());
        }

        response.sendRedirect("customers");
    }
}
