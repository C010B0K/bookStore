package com.example.bookstore.servlet;

import com.example.bookstore.dao.SaleDbDAO;
import com.example.bookstore.domain.Sale;
import com.example.bookstore.exception.DAOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/sales")
public class SalesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SaleDbDAO dao;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void init() throws ServletException {
        try {
            dao = new SaleDbDAO();
        } catch (Exception e) {
            throw new ServletException("Failed to initialize SaleDbDAO", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try {
            request.setAttribute("sales", dao.findAll());
        } catch (DAOException e) {
            e.printStackTrace();
            request.setAttribute("error", "Ошибка при загрузке списка продавцов: " + e.getMessage());
        }

        request.getRequestDispatcher("/sales.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String firstName = request.getParameter("inputFirstName");
        String lastName = request.getParameter("inputLastName");
        String position = request.getParameter("inputPosition");
        String employmentDateStr = request.getParameter("inputEmploymentDate");
        String dateBirthStr = request.getParameter("inputDateBirth");
        String email = request.getParameter("inputEmail");

        LocalDate employmentDate = null;
        LocalDate dateBirth = null;

        try {
            if (employmentDateStr != null && !employmentDateStr.isEmpty()) {
                employmentDate = LocalDate.parse(employmentDateStr, DATE_FORMATTER);
            }
            if (dateBirthStr != null && !dateBirthStr.isEmpty()) {
                dateBirth = LocalDate.parse(dateBirthStr, DATE_FORMATTER);
            }
        } catch (Exception e) {
            request.setAttribute("error", "Неверный формат даты");
            doGet(request, response);
            return;
        }

        Sale newSale = new Sale(firstName, lastName, position, employmentDate, dateBirth, email);

        try {
            Long id = dao.insert(newSale);
            System.out.println("Adding sale result: " + id);
        } catch (DAOException e) {
            e.printStackTrace();
            request.setAttribute("error", "Ошибка при добавлении продавца: " + e.getMessage());
        }

        doGet(request, response);
    }
}
