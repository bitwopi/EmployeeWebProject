package com.example.EmployeeProject.service.impl;

import java.util.Objects;

public class Employee {
    private static int counter = 1;
    private String surname;
    private String name;
    private final int id;

    public Employee(String surname, String name) {
        this.surname = surname;
        this.name = name;
        this.id = Employee.counter;
        Employee.counter++;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setSurname(String surname) {
         this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "{" +
                "surname: '" + surname +
                ", name: '" + name +
                ", id: " + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        return getId() == employee.getId() && Objects.equals(getSurname(), employee.getSurname()) && Objects.equals(getName(), employee.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSurname(), getName(), getId());
    }
}
