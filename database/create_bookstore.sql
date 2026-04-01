-- Создание базы данных bookstore
DROP DATABASE IF EXISTS bookstore;
CREATE DATABASE bookstore
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Russia.1251'
    LC_CTYPE = 'Russian_Russia.1251'
    TEMPLATE = template0
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

-- Подключение к базе данных bookstore
\c bookstore

-- Создание пользователя для приложения
DROP USER IF EXISTS bookstore_admin;
CREATE USER bookstore_admin WITH PASSWORD 'admin123';
GRANT ALL PRIVILEGES ON DATABASE bookstore TO bookstore_admin;

-- Таблица Book – книга
CREATE TABLE IF NOT EXISTS books
(
    id serial NOT NULL,
    author character varying(200) NOT NULL,
    title character varying(300) NOT NULL,
    publishing character varying(200) NOT NULL,
    seriya character varying(200),
    year integer NOT NULL,
    price numeric(10, 2) NOT NULL,
    CONSTRAINT books_pkey PRIMARY KEY (id)
);

-- Таблица Sale – продавец
CREATE TABLE IF NOT EXISTS sales
(
    id serial NOT NULL,
    firstname character varying(100) NOT NULL,
    lastname character varying(100) NOT NULL,
    position character varying(100) NOT NULL,
    employment_date date NOT NULL,
    date_birth date NOT NULL,
    email character varying(150) NOT NULL,
    CONSTRAINT sales_pkey PRIMARY KEY (id),
    CONSTRAINT sales_email_key UNIQUE (email)
);

-- Таблица Customer – покупатель
CREATE TABLE IF NOT EXISTS customers
(
    id serial NOT NULL,
    firstname character varying(100) NOT NULL,
    lastname character varying(100) NOT NULL,
    email character varying(150) NOT NULL,
    phone character varying(20) NOT NULL,
    CONSTRAINT customers_pkey PRIMARY KEY (id),
    CONSTRAINT customers_email_key UNIQUE (email)
);

-- Таблица Order – заказ
CREATE TABLE IF NOT EXISTS orders
(
    id serial NOT NULL,
    id_book integer NOT NULL,
    id_customer integer NOT NULL,
    id_sale integer NOT NULL,
    date_input date NOT NULL,
    date_output date,
    quantity integer NOT NULL,
    total_sum numeric(10, 2) NOT NULL,
    CONSTRAINT orders_pkey PRIMARY KEY (id),
    CONSTRAINT orders_id_book_fkey FOREIGN KEY (id_book)
        REFERENCES public.books (id) ON DELETE RESTRICT,
    CONSTRAINT orders_id_customer_fkey FOREIGN KEY (id_customer)
        REFERENCES public.customers (id) ON DELETE RESTRICT,
    CONSTRAINT orders_id_sale_fkey FOREIGN KEY (id_sale)
        REFERENCES public.sales (id) ON DELETE RESTRICT
);

-- Вставка тестовых данных для Books
INSERT INTO books (author, title, publishing, seriya, year, price) VALUES
('Лев Толстой', 'Война и мир', 'Эксмо', 'Всемирная классика', 2022, 599.90),
('Фёдор Достоевский', 'Преступление и наказание', 'АСТ', 'Школьная программа', 2021, 349.50),
('Александр Пушкин', 'Евгений Онегин', 'Росмэн', 'Золотая библиотека', 2023, 299.00),
('Стивен Кинг', 'Сияние', 'Азбука', 'Мастера ужасов', 2020, 459.90),
('Джоан Роулинг', 'Гарри Поттер и философский камень', 'Росмэн', 'Гарри Поттер', 2022, 399.00),
('Габриэль Гарсиа Маркес', 'Сто лет одиночества', 'Эксмо', 'Литературный бестселлер', 2021, 549.00),
('Эрих Мария Ремарк', 'Три товарища', 'АСТ', 'Военная проза', 2019, 289.90),
('Джордж Оруэлл', '1984', 'Эксмо', 'Антиутопия', 2023, 349.00);

-- Вставка тестовых данных для Sales (продавцы)
INSERT INTO sales (firstname, lastname, position, employment_date, date_birth, email) VALUES
('Иван', 'Иванов', 'Старший продавец', '2020-03-15', '1990-05-12', 'ivan.ivanov@bookstore.ru'),
('Мария', 'Петрова', 'Продавец-консультант', '2021-06-01', '1995-08-23', 'maria.petrova@bookstore.ru'),
('Александр', 'Сидоров', 'Менеджер отдела продаж', '2019-02-10', '1988-11-30', 'alex.sidorov@bookstore.ru'),
('Елена', 'Козлова', 'Продавец-консультант', '2022-09-15', '1997-03-18', 'elena.kozlova@bookstore.ru');

-- Вставка тестовых данных для Customers (покупатели)
INSERT INTO customers (firstname, lastname, email, phone) VALUES
('Анна', 'Смирнова', 'anna.smirnova@mail.ru', '+7(916)123-45-67'),
('Дмитрий', 'Кузнецов', 'dmitry.kuznetsov@gmail.com', '+7(926)234-56-78'),
('Ольга', 'Попова', 'olga.popova@yandex.ru', '+7(903)345-67-89'),
('Пётр', 'Васильев', 'peter.vasiliev@mail.ru', '+7(915)456-78-90'),
('Наталья', 'Морозова', 'natalya.morozova@gmail.com', '+7(925)567-89-01'),
('Сергей', 'Новиков', 'sergey.novikov@yandex.ru', '+7(917)678-90-12');

-- Вставка тестовых данных для Orders (заказы)
INSERT INTO orders (id_book, id_customer, id_sale, date_input, date_output, quantity, total_sum) VALUES
(1, 1, 1, '2024-01-15', '2024-01-15', 1, 599.90),
(2, 2, 2, '2024-01-20', '2024-01-21', 2, 699.00),
(3, 3, 1, '2024-02-05', '2024-02-05', 1, 299.00),
(4, 4, 3, '2024-02-10', '2024-02-12', 1, 459.90),
(5, 5, 2, '2024-03-01', '2024-03-02', 3, 1197.00),
(1, 6, 1, '2024-03-15', '2024-03-16', 2, 1199.80),
(6, 1, 4, '2024-03-20', '2024-03-21', 1, 549.00),
(8, 2, 3, '2024-03-25', NULL, 1, 349.00);

-- Предоставление прав пользователю bookstore_admin
GRANT ALL ON TABLE books TO bookstore_admin;
GRANT ALL ON TABLE sales TO bookstore_admin;
GRANT ALL ON TABLE customers TO bookstore_admin;
GRANT ALL ON TABLE orders TO bookstore_admin;
GRANT USAGE, SELECT ON SEQUENCE books_id_seq TO bookstore_admin;
GRANT USAGE, SELECT ON SEQUENCE sales_id_seq TO bookstore_admin;
GRANT USAGE, SELECT ON SEQUENCE customers_id_seq TO bookstore_admin;
GRANT USAGE, SELECT ON SEQUENCE orders_id_seq TO bookstore_admin;
