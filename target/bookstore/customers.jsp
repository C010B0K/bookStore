<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.example.bookstore.domain.Customer" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Покупатели</title>
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
            <h2>Покупатели</h2>
            <a href="index.jsp" class="btn btn-outline-secondary btn-sm">На главную</a>
        </div>

        <div class="row">
            <div class="col-lg-8">
                <div class="card border-0 shadow-sm">
                    <div class="card-header bg-white">
                        <h6 class="mb-0">Список покупателей</h6>
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
                                        <th>ФИО</th>
                                        <th>Телефон</th>
                                        <th>Email</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                    List<Customer> customers = (List<Customer>) request.getAttribute("customers");
                                    if (customers != null) {
                                        for (Customer customer : customers) {
                                    %>
                                    <tr>
                                        <td><%= customer.getId() %></td>
                                        <td><%= customer.getLastName() %> <%= customer.getFirstName() %></td>
                                        <td><%= customer.getPhone() %></td>
                                        <td><%= customer.getEmail() %></td>
                                        <td>
                                            <a href="editcustomer?id=<%= customer.getId() %>" class="btn btn-sm btn-link p-0">изменить</a>
                                            <span class="text-muted mx-1">|</span>
                                            <a href="deletecustomer?id=<%= customer.getId() %>" class="btn btn-sm btn-link p-0 text-danger"
                                               onclick="return confirm('Удалить покупателя?')">удалить</a>
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
                        <h6 class="mb-0">Новый покупатель</h6>
                    </div>
                    <div class="card-body">
                        <form method="POST" action="customers">
                            <div class="row">
                                <div class="col-6 mb-2">
                                    <label class="form-label small">Имя</label>
                                    <input type="text" name="inputFirstName" class="form-control form-control-sm" required>
                                </div>
                                <div class="col-6 mb-2">
                                    <label class="form-label small">Фамилия</label>
                                    <input type="text" name="inputLastName" class="form-control form-control-sm" required>
                                </div>
                            </div>
                            <div class="mb-2">
                                <label class="form-label small">Телефон</label>
                                <input type="text" name="inputPhone" class="form-control form-control-sm" required>
                            </div>
                            <div class="mb-3">
                                <label class="form-label small">Email</label>
                                <input type="email" name="inputEmail" class="form-control form-control-sm" required>
                            </div>
                            <button type="submit" class="btn btn-primary btn-sm w-100">Добавить</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
