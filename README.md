# Информационная подсистема Магазина книг

Курсовой проект по разработке веб-приложения для управления книжным магазином с использованием Java EE технологий.

## Описание проекта

Веб-приложение предназначено для автоматизации учёта заказов на книги в книжном магазине. Система позволяет управлять следующими сущностями:
- **Books** — книги
- **Sales** — продавцы
- **Customers** — покупатели
- **Orders** — заказы

## Технологии

| Технология | Версия |
|------------|--------|
| Java | 21 |
| Jakarta Servlet | 5.0 |
| JSP | 3.0 |
| PostgreSQL | 18 |
| Apache Tomcat | 10.1.52 |
| Bootstrap | 5.3 |
| Maven | 3.x |

## Структура проекта

```
курсач/
├── database/
│   └── create_bookstore.sql        # SQL-скрипт создания БД
├── src/
│   ├── main/
│   │   ├── java/com/example/bookstore/
│   │   │   ├── dao/                # DAO слой
│   │   │   │   ├── DAO.java
│   │   │   │   ├── BookDbDAO.java
│   │   │   │   ├── SaleDbDAO.java
│   │   │   │   ├── CustomerDbDAO.java
│   │   │   │   ├── OrderDbDAO.java
│   │   │   │   └── ConnectionProperty.java
│   │   │   ├── domain/             # Доменные модели
│   │   │   │   ├── Book.java
│   │   │   │   ├── Sale.java
│   │   │   │   ├── Customer.java
│   │   │   │   └── Order.java
│   │   │   ├── exception/          # Исключения
│   │   │   │   └── DAOException.java
│   │   │   ├── filter/             # Фильтры
│   │   │   │   └── AuthenticationFilter.java
│   │   │   └── servlet/            # Сервлеты
│   │   │       ├── LoginServlet.java
│   │   │       ├── LogoutServlet.java
│   │   │       ├── BooksServlet.java
│   │   │       ├── EditBookServlet.java
│   │   │       ├── DeleteBookServlet.java
│   │   │       ├── SalesServlet.java
│   │   │       ├── EditSaleServlet.java
│   │   │       ├── DeleteSaleServlet.java
│   │   │       ├── CustomersServlet.java
│   │   │       ├── EditCustomerServlet.java
│   │   │       ├── DeleteCustomerServlet.java
│   │   │       ├── OrdersServlet.java
│   │   │       ├── EditOrderServlet.java
│   │   │       └── DeleteOrderServlet.java
│   │   ├── resources/
│   │   │   └── config/
│   │   │       └── config.properties # Конфигурация БД
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       │   └── web.xml
│   │       ├── index.jsp           # Главная страница
│   │       ├── login.jsp           # Страница входа
│   │       ├── books.jsp           # Список книг
│   │       ├── editBook.jsp        # Редактирование книги
│   │       ├── sales.jsp           # Список продавцов
│   │       ├── editSale.jsp        # Редактирование продавца
│   │       ├── customers.jsp       # Список покупателей
│   │       ├── editCustomer.jsp    # Редактирование покупателя
│   │       ├── orders.jsp          # Список заказов
│   │       └── editOrder.jsp       # Редактирование заказа
└── pom.xml                         # Maven конфигурация
```

## Установка и запуск

### 1. Создание базы данных

```bash
cd database
psql -U postgres -f create_bookstore.sql
```

Или вручную выполните SQL-скрипт `create_bookstore.sql` в PostgreSQL.

### 2. Сборка проекта

```bash
mvn clean package
```

WAR-файл будет автоматически развёрнут в директорию Tomcat:
`c:/Tomcat10/apache-tomcat-10.1.52/webapps/bookstore/`

### 3. Запуск Tomcat

Запустите Tomcat и перейдите по адресу:
```
http://localhost:8080/bookstore/
```

### 4. Вход в систему

**Учётные данные по умолчанию:**
- Имя пользователя: `admin`
- Пароль: `admin123`

## Модель данных

### Book — Книга

| Поле | Тип | Описание |
|------|-----|----------|
| id | serial | Суррогатный ключ |
| author | varchar(200) | Автор |
| title | varchar(300) | Название |
| publishing | varchar(200) | Издательство |
| seriya | varchar(200) | Серия |
| year | integer | Год издания |
| price | numeric(10,2) | Цена |

### Sale — Продавец

| Поле | Тип | Описание |
|------|-----|----------|
| id | serial | Суррогатный ключ |
| firstname | varchar(100) | Имя |
| lastname | varchar(100) | Фамилия |
| position | varchar(100) | Должность |
| employment_date | date | Дата приёма на работу |
| date_birth | date | Дата рождения |
| email | varchar(150) | Электронная почта |

### Customer — Покупатель

| Поле | Тип | Описание |
|------|-----|----------|
| id | serial | Суррогатный ключ |
| firstname | varchar(100) | Имя |
| lastname | varchar(100) | Фамилия |
| email | varchar(150) | Электронная почта |
| phone | varchar(20) | Телефон |

### Order — Заказ

| Поле | Тип | Описание |
|------|-----|----------|
| id | serial | Суррогатный ключ |
| id_book | integer | Ссылка на книгу |
| id_customer | integer | Ссылка на покупателя |
| id_sale | integer | Ссылка на продавца |
| date_input | date | Дата заказа |
| date_output | date | Дата отгрузки |
| quantity | integer | Количество книг |
| total_sum | numeric(10,2) | Общая сумма |

## Безопасность

- **Аутентификация пользователей** — фильтр `AuthenticationFilter` проверяет наличие авторизованного пользователя в сессии
- **Защита веб-запросов** — все защищённые страницы требуют аутентификации
- **UTF-8 кодировка** — корректная обработка кириллицы

## CRUD операции

Для каждой сущности реализованы полноценные CRUD операции:

| Операция | Метод | Описание |
|----------|-------|----------|
| Create | POST | Добавление новой записи |
| Read | GET | Просмотр списка записей |
| Update | POST | Редактирование записи |
| Delete | GET | Удаление записи с подтверждением |

## Авторы

Разработано в рамках курсового проекта по дисциплине "Сопротивление материалов и программирование систем".
