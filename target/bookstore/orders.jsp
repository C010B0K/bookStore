<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.example.bookstore.domain.Order" %>
<%@ page import="com.example.bookstore.domain.Book" %>
<%@ page import="com.example.bookstore.domain.Customer" %>
<%@ page import="com.example.bookstore.domain.Sale" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Заказы</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="index.jsp">Учёт заказов</a>
            <div class="d-flex align-items-center">
                <span class="text-light me-3"><%= session.getAttribute("user") %></span>
                <a href="logout" class="btn btn-outline-light btn-sm">Выйти</a>
            </div>
        </div>
    </nav>

    <div class="container py-4">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2>Заказы</h2>
            <a href="index.jsp" class="btn btn-outline-secondary btn-sm">На главную</a>
        </div>

        <div class="row">
            <div class="col-lg-8">
                <div class="card border-0 shadow-sm">
                    <div class="card-header bg-white">
                        <h6 class="mb-0">Список заказов</h6>
                    </div>
                    <div class="card-body">
                        <% if (request.getAttribute("error") != null) { %>
                        <div class="alert alert-danger"><%= request.getAttribute("error") %></div>
                        <% } %>

                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead class="table-light">
                                    <tr>
                                        <th>ID</th>
                                        <th>Книга</th>
                                        <th>Покупатель</th>
                                        <th>Дата заказа</th>
                                        <th>Кол-во</th>
                                        <th>Сумма</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                    List<Order> orders = (List<Order>) request.getAttribute("orders");
                                    if (orders != null) {
                                        for (Order order : orders) {
                                    %>
                                    <tr>
                                        <td><%= order.getId() %></td>
                                        <td><%= order.getBook() != null ? order.getBook().getTitle() : "-" %></td>
                                        <td><%= order.getCustomer() != null ? order.getCustomer().getLastName() : "-" %></td>
                                        <td><%= order.getDateInput() != null ? order.getDateInput() : "-" %></td>
                                        <td><%= order.getQuantity() %></td>
                                        <td><%= order.getTotalSum() %></td>
                                        <td>
                                            <a href="editorder?id=<%= order.getId() %>" class="btn btn-sm btn-link p-0">изменить</a>
                                            <span class="text-muted mx-1">|</span>
                                            <a href="deleteorder?id=<%= order.getId() %>" class="btn btn-sm btn-link p-0 text-danger"
                                               onclick="return confirm('Удалить заказ?')">удалить</a>
                                        </td>
                                    </tr>
                                    <%
                                        }
                                    }
                                    %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-lg-4">
                <div class="card border-0 shadow-sm">
                    <div class="card-header bg-white">
                        <h6 class="mb-0">Новый заказ</h6>
                    </div>
                    <div class="card-body">
                        <%
                        List<Book> books = (List<Book>) request.getAttribute("books");
                        List<Customer> customers = (List<Customer>) request.getAttribute("customers");
                        List<Sale> sales = (List<Sale>) request.getAttribute("sales");
                        %>
                        <form method="POST" action="orders">
                            <div class="mb-2">
                                <label class="form-label small">Книга</label>
                                <select name="inputBook" class="form-select form-select-sm" required>
                                    <option value="">выберите книгу</option>
                                    <% if (books != null) {
                                        for (Book book : books) { %>
                                    <option value="<%= book.getId() %>"><%= book.getAuthor() %>. <%= book.getTitle() %></option>
                                    <%    }
                                    } %>
                                </select>
                            </div>
                            <div class="mb-2">
                                <label class="form-label small">Покупатель</label>
                                <select name="inputCustomer" class="form-select form-select-sm" required>
                                    <option value="">выберите покупателя</option>
                                    <% if (customers != null) {
                                        for (Customer customer : customers) { %>
                                    <option value="<%= customer.getId() %>"><%= customer.getLastName() %> <%= customer.getFirstName() %></option>
                                    <%    }
                                    } %>
                                </select>
                            </div>
                            <div class="mb-2">
                                <label class="form-label small">Продавец</label>
                                <select name="inputSale" class="form-select form-select-sm" required>
                                    <option value="">выберите продавца</option>
                                    <% if (sales != null) {
                                        for (Sale sale : sales) { %>
                                    <option value="<%= sale.getId() %>"><%= sale.getLastName() %> <%= sale.getFirstName() %></option>
                                    <%    }
                                    } %>
                                </select>
                            </div>
                            <div class="row">
                                <div class="col-6 mb-2">
                                    <label class="form-label small">Дата заказа</label>
                                    <input type="date" name="inputDateInput" class="form-control form-control-sm" required>
                                </div>
                                <div class="col-6 mb-2">
                                    <label class="form-label small">Дата отгрузки</label>
                                    <input type="date" name="inputDateOutput" class="form-control form-control-sm">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-6 mb-3">
                                    <label class="form-label small">Количество</label>
                                    <input type="number" name="inputQuantity" class="form-control form-control-sm" required min="1">
                                </div>
                                <div class="col-6 mb-3">
                                    <label class="form-label small">Сумма</label>
                                    <input type="number" step="0.01" name="inputTotalSum" class="form-control form-control-sm" required>
                                </div>
                            </div>
                            <button type="submit" class="btn btn-warning btn-sm w-100">Добавить</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
