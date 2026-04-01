<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Учёт заказов bookstore</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; }
        .navbar-brand { font-weight: 500; }
        .main-card { transition: transform 0.2s; }
        .main-card:hover { transform: translateY(-3px); }
        .section-title { color: #495057; font-weight: 400; }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="index.jsp">Учёт заказов bookstore</a>
            <div class="d-flex align-items-center">
                <span class="text-light me-3">
                    <%= session.getAttribute("user") != null ? session.getAttribute("user") : "Гость" %>
                </span>
                <a href="logout" class="btn btn-outline-light btn-sm">Выйти</a>
            </div>
        </div>
    </nav>

    <div class="container py-5">
        <div class="row mb-4">
            <div class="col-12">
                <h2 class="section-title">Главная</h2>
                <p class="text-muted">Выберите раздел для работы</p>
            </div>
        </div>

        <div class="row g-4">
            <div class="col-md-3">
                <div class="card main-card h-100 border-0 shadow-sm">
                    <div class="card-body text-center py-4">
                        <h5 class="card-title mb-3">Книги</h5>
                        <p class="card-text text-muted small">Каталог книг магазина</p>
                        <a href="books" class="btn btn-outline-primary">Открыть</a>
                    </div>
                </div>
            </div>

            <div class="col-md-3">
                <div class="card main-card h-100 border-0 shadow-sm">
                    <div class="card-body text-center py-4">
                        <h5 class="card-title mb-3">Продавцы</h5>
                        <p class="card-text text-muted small">Сотрудники отдела продаж</p>
                        <a href="sales" class="btn btn-outline-success">Открыть</a>
                    </div>
                </div>
            </div>

            <div class="col-md-3">
                <div class="card main-card h-100 border-0 shadow-sm">
                    <div class="card-body text-center py-4">
                        <h5 class="card-title mb-3">Покупатели</h5>
                        <p class="card-text text-muted small">Клиенты магазина</p>
                        <a href="customers" class="btn btn-outline-info">Открыть</a>
                    </div>
                </div>
            </div>

            <div class="col-md-3">
                <div class="card main-card h-100 border-0 shadow-sm">
                    <div class="card-body text-center py-4">
                        <h5 class="card-title mb-3">Заказы</h5>
                        <p class="card-text text-muted small">Заказы на книги</p>
                        <a href="orders" class="btn btn-outline-warning">Открыть</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
