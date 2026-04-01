<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.example.bookstore.domain.Sale" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Редактирование продавца</title>
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
                        <h6 class="mb-0">Редактирование продавца</h6>
                    </div>
                    <div class="card-body">
                        <% Sale saleEdit = (Sale) request.getAttribute("saleEdit"); %>
                        <% if (saleEdit != null) { %>
                        <form method="POST" action="">
                            <div class="row mb-3">
                                <label class="col-sm-3 col-form-label small">ID</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control form-control-sm" readonly
                                           value="<%= saleEdit.getId() %>" />
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-6 mb-3">
                                    <label class="form-label small">Имя</label>
                                    <input type="text" name="inputFirstName" class="form-control form-control-sm"
                                           value="<%= saleEdit.getFirstName() %>" required />
                                </div>
                                <div class="col-6 mb-3">
                                    <label class="form-label small">Фамилия</label>
                                    <input type="text" name="inputLastName" class="form-control form-control-sm"
                                           value="<%= saleEdit.getLastName() %>" required />
                                </div>
                            </div>
                            <div class="mb-3">
                                <label class="form-label small">Должность</label>
                                <input type="text" name="inputPosition" class="form-control form-control-sm"
                                       value="<%= saleEdit.getPosition() %>" required />
                            </div>
                            <div class="row">
                                <div class="col-6 mb-3">
                                    <label class="form-label small">Дата приёма</label>
                                    <input type="date" name="inputEmploymentDate" class="form-control form-control-sm"
                                           value="<%= saleEdit.getEmploymentDate() != null ? saleEdit.getEmploymentDate() : "" %>" required />
                                </div>
                                <div class="col-6 mb-3">
                                    <label class="form-label small">Дата рождения</label>
                                    <input type="date" name="inputDateBirth" class="form-control form-control-sm"
                                           value="<%= saleEdit.getDateBirth() != null ? saleEdit.getDateBirth() : "" %>" required />
                                </div>
                            </div>
                            <div class="mb-3">
                                <label class="form-label small">Email</label>
                                <input type="email" name="inputEmail" class="form-control form-control-sm"
                                       value="<%= saleEdit.getEmail() %>" required />
                            </div>
                            <div class="d-flex gap-2">
                                <button type="submit" class="btn btn-success btn-sm">Сохранить</button>
                                <a href="sales" class="btn btn-outline-secondary btn-sm">Отмена</a>
                            </div>
                        </form>
                        <% } else { %>
                        <div class="alert alert-warning">Продавец не найден</div>
                        <a href="sales" class="btn btn-outline-secondary btn-sm">Вернуться</a>
                        <% } %>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
