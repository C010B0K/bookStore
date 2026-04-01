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
    <title>Редактирование заказа</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="index.jsp">Учёт заказов</a>
        </div>
    </nav>

    <div class="container py-4">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card border-0 shadow-sm">
                    <div class="card-header bg-white">
                        <h6 class="mb-0">Редактирование заказа</h6>
                    </div>
                    <div class="card-body">
                        <% Order orderEdit = (Order) request.getAttribute("orderEdit"); %>
                        <% List<Book> books = (List<Book>) request.getAttribute("books"); %>
                        <% List<Customer> customers = (List<Customer>) request.getAttribute("customers"); %>
                        <% List<Sale> sales = (List<Sale>) request.getAttribute("sales"); %>

                        <% if (orderEdit != null) { %>
                        <form method="POST" action="">
                            <div class="row mb-3">
                                <label class="col-sm-3 col-form-label small">ID</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control form-control-sm" readonly
                                           value="<%= orderEdit.getId() %>" />
                                </div>
                            </div>
                            <div class="mb-2">
                                <label class="form-label small">Книга</label>
                                <select name="inputBook" class="form-select form-select-sm" required>
                                    <option value="">выберите книгу</option>
                                    <% if (books != null) {
                                        for (Book book : books) {
                                            boolean selected = (orderEdit.getIdBook() != null && orderEdit.getIdBook().equals(book.getId())); %>
                                    <option value="<%= book.getId() %>" <%= selected ? "selected" : "" %>><%= book.getAuthor() %>. <%= book.getTitle() %></option>
                                    <%    }
                                        } %>
                                </select>
                            </div>
                            <div class="mb-2">
                                <label class="form-label small">Покупатель</label>
                                <select name="inputCustomer" class="form-select form-select-sm" required>
                                    <option value="">выберите покупателя</option>
                                    <% if (customers != null) {
                                        for (Customer customer : customers) {
                                            boolean selected = (orderEdit.getIdCustomer() != null && orderEdit.getIdCustomer().equals(customer.getId())); %>
                                    <option value="<%= customer.getId() %>" <%= selected ? "selected" : "" %>><%= customer.getLastName() %> <%= customer.getFirstName() %></option>
                                    <%    }
                                        } %>
                                </select>
                            </div>
                            <div class="mb-2">
                                <label class="form-label small">Продавец</label>
                                <select name="inputSale" class="form-select form-select-sm" required>
                                    <option value="">выберите продавца</option>
                                    <% if (sales != null) {
                                        for (Sale sale : sales) {
                                            boolean selected = (orderEdit.getIdSale() != null && orderEdit.getIdSale().equals(sale.getId())); %>
                                    <option value="<%= sale.getId() %>" <%= selected ? "selected" : "" %>><%= sale.getLastName() %> <%= sale.getFirstName() %></option>
                                    <%    }
                                        } %>
                                </select>
                            </div>
                            <div class="row">
                                <div class="col-6 mb-2">
                                    <label class="form-label small">Дата заказа</label>
                                    <input type="date" name="inputDateInput" class="form-control form-control-sm"
                                           value="<%= orderEdit.getDateInput() != null ? orderEdit.getDateInput() : "" %>" required />
                                </div>
                                <div class="col-6 mb-2">
                                    <label class="form-label small">Дата отгрузки</label>
                                    <input type="date" name="inputDateOutput" class="form-control form-control-sm"
                                           value="<%= orderEdit.getDateOutput() != null ? orderEdit.getDateOutput() : "" %>" />
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-6 mb-3">
                                    <label class="form-label small">Количество</label>
                                    <input type="number" name="inputQuantity" class="form-control form-control-sm"
                                           value="<%= orderEdit.getQuantity() %>" required min="1" />
                                </div>
                                <div class="col-6 mb-3">
                                    <label class="form-label small">Сумма</label>
                                    <input type="number" step="0.01" name="inputTotalSum" class="form-control form-control-sm"
                                           value="<%= orderEdit.getTotalSum() %>" required />
                                </div>
                            </div>
                            <div class="d-flex gap-2">
                                <button type="submit" class="btn btn-warning btn-sm">Сохранить</button>
                                <a href="orders" class="btn btn-outline-secondary btn-sm">Отмена</a>
                            </div>
                        </form>
                        <% } else { %>
                        <div class="alert alert-warning">Заказ не найден</div>
                        <a href="orders" class="btn btn-outline-secondary btn-sm">Вернуться</a>
                        <% } %>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
