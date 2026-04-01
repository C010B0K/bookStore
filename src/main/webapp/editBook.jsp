<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.example.bookstore.domain.Book" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Редактирование книги</title>
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
                        <h6 class="mb-0">Редактирование книги</h6>
                    </div>
                    <div class="card-body">
                        <% Book bookEdit = (Book) request.getAttribute("bookEdit"); %>
                        <% if (bookEdit != null) { %>
                        <form method="POST" action="">
                            <div class="row mb-3">
                                <label class="col-sm-3 col-form-label small">ID</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control form-control-sm" readonly
                                           value="<%= bookEdit.getId() %>" />
                                </div>
                            </div>
                            <div class="mb-3">
                                <label class="form-label small">Автор</label>
                                <input type="text" name="inputAuthor" class="form-control form-control-sm"
                                       value="<%= bookEdit.getAuthor() %>" required />
                            </div>
                            <div class="mb-3">
                                <label class="form-label small">Название</label>
                                <input type="text" name="inputTitle" class="form-control form-control-sm"
                                       value="<%= bookEdit.getTitle() %>" required />
                            </div>
                            <div class="mb-3">
                                <label class="form-label small">Издательство</label>
                                <input type="text" name="inputPublishing" class="form-control form-control-sm"
                                       value="<%= bookEdit.getPublishing() %>" required />
                            </div>
                            <div class="mb-3">
                                <label class="form-label small">Серия</label>
                                <input type="text" name="inputSeriya" class="form-control form-control-sm"
                                       value="<%= bookEdit.getSeriya() != null ? bookEdit.getSeriya() : "" %>" />
                            </div>
                            <div class="row">
                                <div class="col-6 mb-3">
                                    <label class="form-label small">Год</label>
                                    <input type="number" name="inputYear" class="form-control form-control-sm"
                                           value="<%= bookEdit.getYear() %>" required />
                                </div>
                                <div class="col-6 mb-3">
                                    <label class="form-label small">Цена</label>
                                    <input type="number" step="0.01" name="inputPrice" class="form-control form-control-sm"
                                           value="<%= bookEdit.getPrice() %>" required />
                                </div>
                            </div>
                            <div class="d-flex gap-2">
                                <button type="submit" class="btn btn-primary btn-sm">Сохранить</button>
                                <a href="books" class="btn btn-outline-secondary btn-sm">Отмена</a>
                            </div>
                        </form>
                        <% } else { %>
                        <div class="alert alert-warning">Книга не найдена</div>
                        <a href="books" class="btn btn-outline-secondary btn-sm">Вернуться</a>
                        <% } %>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
