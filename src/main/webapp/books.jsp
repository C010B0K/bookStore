<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.example.bookstore.domain.Book" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Книги</title>
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
            <h2>Книги</h2>
            <a href="index.jsp" class="btn btn-outline-secondary btn-sm">На главную</a>
        </div>

        <div class="row">
            <div class="col-lg-8">
                <div class="card border-0 shadow-sm">
                    <div class="card-header bg-white">
                        <h6 class="mb-0">Список книг</h6>
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
                                        <th>Автор</th>
                                        <th>Название</th>
                                        <th>Издательство</th>
                                        <th>Год</th>
                                        <th>Цена</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                    List<Book> books = (List<Book>) request.getAttribute("books");
                                    if (books != null) {
                                        for (Book book : books) {
                                    %>
                                    <tr>
                                        <td><%= book.getId() %></td>
                                        <td><%= book.getAuthor() %></td>
                                        <td><%= book.getTitle() %></td>
                                        <td><%= book.getPublishing() %></td>
                                        <td><%= book.getYear() %></td>
                                        <td><%= book.getPrice() %></td>
                                        <td>
                                            <a href="editbook?id=<%= book.getId() %>" class="btn btn-sm btn-link p-0">изменить</a>
                                            <span class="text-muted mx-1">|</span>
                                            <a href="deletebook?id=<%= book.getId() %>" class="btn btn-sm btn-link p-0 text-danger"
                                               onclick="return confirm('Удалить книгу?')">удалить</a>
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
                        <h6 class="mb-0">Новая книга</h6>
                    </div>
                    <div class="card-body">
                        <form method="POST" action="books">
                            <div class="mb-2">
                                <label class="form-label small">Автор</label>
                                <input type="text" name="inputAuthor" class="form-control form-control-sm" required>
                            </div>
                            <div class="mb-2">
                                <label class="form-label small">Название</label>
                                <input type="text" name="inputTitle" class="form-control form-control-sm" required>
                            </div>
                            <div class="mb-2">
                                <label class="form-label small">Издательство</label>
                                <input type="text" name="inputPublishing" class="form-control form-control-sm" required>
                            </div>
                            <div class="mb-2">
                                <label class="form-label small">Серия</label>
                                <input type="text" name="inputSeriya" class="form-control form-control-sm">
                            </div>
                            <div class="row">
                                <div class="col-6 mb-2">
                                    <label class="form-label small">Год</label>
                                    <input type="number" name="inputYear" class="form-control form-control-sm" required>
                                </div>
                                <div class="col-6 mb-2">
                                    <label class="form-label small">Цена</label>
                                    <input type="number" step="0.01" name="inputPrice" class="form-control form-control-sm" required>
                                </div>
                            </div>
                            <button type="submit" class="btn btn-primary btn-sm w-100 mt-2">Добавить</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
