<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.example.bookstore.domain.Customer" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Редактирование покупателя</title>
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
                        <h6 class="mb-0">Редактирование покупателя</h6>
                    </div>
                    <div class="card-body">
                        <% Customer customerEdit = (Customer) request.getAttribute("customerEdit"); %>
                        <% if (customerEdit != null) { %>
                        <form method="POST" action="">
                            <div class="row mb-3">
                                <label class="col-sm-3 col-form-label small">ID</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control form-control-sm" readonly
                                           value="<%= customerEdit.getId() %>" />
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-6 mb-3">
                                    <label class="form-label small">Имя</label>
                                    <input type="text" name="inputFirstName" class="form-control form-control-sm"
                                           value="<%= customerEdit.getFirstName() %>" required />
                                </div>
                                <div class="col-6 mb-3">
                                    <label class="form-label small">Фамилия</label>
                                    <input type="text" name="inputLastName" class="form-control form-control-sm"
                                           value="<%= customerEdit.getLastName() %>" required />
                                </div>
                            </div>
                            <div class="mb-3">
                                <label class="form-label small">Телефон</label>
                                <input type="text" name="inputPhone" class="form-control form-control-sm"
                                       value="<%= customerEdit.getPhone() %>" required />
                            </div>
                            <div class="mb-3">
                                <label class="form-label small">Email</label>
                                <input type="email" name="inputEmail" class="form-control form-control-sm"
                                       value="<%= customerEdit.getEmail() %>" required />
                            </div>
                            <div class="d-flex gap-2">
                                <button type="submit" class="btn btn-primary btn-sm">Сохранить</button>
                                <a href="customers" class="btn btn-outline-secondary btn-sm">Отмена</a>
                            </div>
                        </form>
                        <% } else { %>
                        <div class="alert alert-warning">Покупатель не найден</div>
                        <a href="customers" class="btn btn-outline-secondary btn-sm">Вернуться</a>
                        <% } %>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
