package com.example.bookstore.servlet;

import com.example.bookstore.dao.BookDbDAO;
import com.example.bookstore.domain.Book;
import com.example.bookstore.exception.DAOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/books")
public class BooksServlet extends HttpServlet {
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

        response.setContentType("text/html;charset=UTF-8");

        try {
            request.setAttribute("books", dao.findAll());
        } catch (DAOException e) {
            e.printStackTrace();
            request.setAttribute("error", "Ошибка при загрузке списка книг: " + e.getMessage());
        }

        request.getRequestDispatcher("/books.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String author = request.getParameter("inputAuthor");
        String title = request.getParameter("inputTitle");
        String publishing = request.getParameter("inputPublishing");
        String seriya = request.getParameter("inputSeriya");
        String yearStr = request.getParameter("inputYear");
        String priceStr = request.getParameter("inputPrice");

        Integer year = null;
        Double price = null;

        try {
            if (yearStr != null && !yearStr.isEmpty()) {
                year = Integer.parseInt(yearStr);
            }
            if (priceStr != null && !priceStr.isEmpty()) {
                price = Double.parseDouble(priceStr);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Неверный формат числа для года или цены");
            doGet(request, response);
            return;
        }

        Book newBook = new Book(author, title, publishing, seriya, year, price);

        try {
            Long id = dao.insert(newBook);
            System.out.println("Adding book result: " + id);
        } catch (DAOException e) {
            e.printStackTrace();
            request.setAttribute("error", "Ошибка при добавлении книги: " + e.getMessage());
        }

        doGet(request, response);
    }
}
