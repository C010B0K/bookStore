package com.example.bookstore.servlet;

import com.example.bookstore.dao.BookDbDAO;
import com.example.bookstore.dao.CustomerDbDAO;
import com.example.bookstore.dao.OrderDbDAO;
import com.example.bookstore.dao.SaleDbDAO;
import com.example.bookstore.domain.Order;
import com.example.bookstore.exception.DAOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/editorder")
public class EditOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrderDbDAO orderDao;
    private BookDbDAO bookDao;
    private CustomerDbDAO customerDao;
    private SaleDbDAO saleDao;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void init() throws ServletException {
        try {
            orderDao = new OrderDbDAO();
            bookDao = new BookDbDAO();
            customerDao = new CustomerDbDAO();
            saleDao = new SaleDbDAO();
        } catch (Exception e) {
            throw new ServletException("Failed to initialize DAOs", e);
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

        try {
            request.setAttribute("books", bookDao.findAll());
            request.setAttribute("customers", customerDao.findAll());
            request.setAttribute("sales", saleDao.findAll());

            Order editOrder = orderDao.findById(id);
            request.setAttribute("orderEdit", editOrder);
        } catch (DAOException e) {
            e.printStackTrace();
            request.setAttribute("error", "Заказ не найден: " + e.getMessage());
        }

        request.getRequestDispatcher("/editOrder.jsp").forward(request, response);
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

        String bookIdStr = request.getParameter("inputBook");
        String customerIdStr = request.getParameter("inputCustomer");
        String saleIdStr = request.getParameter("inputSale");
        String dateInputStr = request.getParameter("inputDateInput");
        String dateOutputStr = request.getParameter("inputDateOutput");
        String quantityStr = request.getParameter("inputQuantity");
        String totalSumStr = request.getParameter("inputTotalSum");

        Long bookId = null;
        Long customerId = null;
        Long saleId = null;
        LocalDate dateInput = null;
        LocalDate dateOutput = null;
        Integer quantity = null;
        Double totalSum = null;

        try {
            if (bookIdStr != null && !bookIdStr.isEmpty()) {
                bookId = Long.parseLong(bookIdStr);
            }
            if (customerIdStr != null && !customerIdStr.isEmpty()) {
                customerId = Long.parseLong(customerIdStr);
            }
            if (saleIdStr != null && !saleIdStr.isEmpty()) {
                saleId = Long.parseLong(saleIdStr);
            }
            if (dateInputStr != null && !dateInputStr.isEmpty()) {
                dateInput = LocalDate.parse(dateInputStr, DATE_FORMATTER);
            }
            if (dateOutputStr != null && !dateOutputStr.isEmpty()) {
                dateOutput = LocalDate.parse(dateOutputStr, DATE_FORMATTER);
            }
            if (quantityStr != null && !quantityStr.isEmpty()) {
                quantity = Integer.parseInt(quantityStr);
            }
            if (totalSumStr != null && !totalSumStr.isEmpty()) {
                totalSum = Double.parseDouble(totalSumStr);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Неверный формат числовых данных");
            doGet(request, response);
            return;
        } catch (Exception e) {
            request.setAttribute("error", "Неверный формат даты");
            doGet(request, response);
            return;
        }

        Order editOrder = new Order(id, bookId, customerId, saleId, dateInput, dateOutput, quantity, totalSum);

        try {
            orderDao.update(editOrder);
        } catch (DAOException e) {
            e.printStackTrace();
            request.setAttribute("error", "Ошибка при обновлении заказа: " + e.getMessage());
        }

        response.sendRedirect("orders");
    }
}
