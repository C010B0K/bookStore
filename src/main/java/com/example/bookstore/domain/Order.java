package com.example.bookstore.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Order {
    private Long id;
    private Long idBook;
    private Long idCustomer;
    private Long idSale;
    private LocalDate dateInput;
    private LocalDate dateOutput;
    private Integer quantity;
    private Double totalSum;

    private Book book;
    private Customer customer;
    private Sale sale;

    public Order() {
    }

    public Order(Long idBook, Long idCustomer, Long idSale, LocalDate dateInput, LocalDate dateOutput, Integer quantity, Double totalSum) {
        this.idBook = idBook;
        this.idCustomer = idCustomer;
        this.idSale = idSale;
        this.dateInput = dateInput;
        this.dateOutput = dateOutput;
        this.quantity = quantity;
        this.totalSum = totalSum;
    }

    public Order(Long id, Long idBook, Long idCustomer, Long idSale, LocalDate dateInput, LocalDate dateOutput, Integer quantity, Double totalSum) {
        this.id = id;
        this.idBook = idBook;
        this.idCustomer = idCustomer;
        this.idSale = idSale;
        this.dateInput = dateInput;
        this.dateOutput = dateOutput;
        this.quantity = quantity;
        this.totalSum = totalSum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdBook() {
        return idBook;
    }

    public void setIdBook(Long idBook) {
        this.idBook = idBook;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Long getIdSale() {
        return idSale;
    }

    public void setIdSale(Long idSale) {
        this.idSale = idSale;
    }

    public LocalDate getDateInput() {
        return dateInput;
    }

    public void setDateInput(LocalDate dateInput) {
        this.dateInput = dateInput;
    }

    public LocalDate getDateOutput() {
        return dateOutput;
    }

    public void setDateOutput(LocalDate dateOutput) {
        this.dateOutput = dateOutput;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(Double totalSum) {
        this.totalSum = totalSum;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", idBook=" + idBook +
                ", idCustomer=" + idCustomer +
                ", idSale=" + idSale +
                ", dateInput=" + dateInput +
                ", dateOutput=" + dateOutput +
                ", quantity=" + quantity +
                ", totalSum=" + totalSum +
                '}';
    }
}
