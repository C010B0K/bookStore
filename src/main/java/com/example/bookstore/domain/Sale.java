package com.example.bookstore.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Sale {
    private Long id;
    private String firstName;
    private String lastName;
    private String position;
    private LocalDate employmentDate;
    private LocalDate dateBirth;
    private String email;

    public Sale() {
    }

    public Sale(String firstName, String lastName, String position, LocalDate employmentDate, LocalDate dateBirth, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.employmentDate = employmentDate;
        this.dateBirth = dateBirth;
        this.email = email;
    }

    public Sale(Long id, String firstName, String lastName, String position, LocalDate employmentDate, LocalDate dateBirth, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.employmentDate = employmentDate;
        this.dateBirth = dateBirth;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return Objects.equals(id, sale.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", position='" + position + '\'' +
                ", employmentDate=" + employmentDate +
                ", dateBirth=" + dateBirth +
                ", email='" + email + '\'' +
                '}';
    }
}
