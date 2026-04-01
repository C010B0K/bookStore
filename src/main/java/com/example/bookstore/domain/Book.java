package com.example.bookstore.domain;

import java.util.Objects;

public class Book {
    private Long id;
    private String author;
    private String title;
    private String publishing;
    private String seriya;
    private Integer year;
    private Double price;

    public Book() {
    }

    public Book(String author, String title, String publishing, String seriya, Integer year, Double price) {
        this.author = author;
        this.title = title;
        this.publishing = publishing;
        this.seriya = seriya;
        this.year = year;
        this.price = price;
    }

    public Book(Long id, String author, String title, String publishing, String seriya, Integer year, Double price) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.publishing = publishing;
        this.seriya = seriya;
        this.year = year;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishing() {
        return publishing;
    }

    public void setPublishing(String publishing) {
        this.publishing = publishing;
    }

    public String getSeriya() {
        return seriya;
    }

    public void setSeriya(String seriya) {
        this.seriya = seriya;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", publishing='" + publishing + '\'' +
                ", seriya='" + seriya + '\'' +
                ", year=" + year +
                ", price=" + price +
                '}';
    }
}
