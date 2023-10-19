package com.example.EmployeeProject.service.impl;

import java.util.Objects;

public class Employee {
    private static int counter = 1;
    private String surname;
    private String name;
    private Double salary;
    private Integer department;
    private final int id;

    public Employee(String surname, String name) {
        this.surname = surname;
        this.name = name;
        this.salary = 0.0;
        this.department = 1;
        this.id = Employee.counter;
        Employee.counter++;
    }

    public Employee(String surname, String name, Double salary, Integer department) {
        this(surname, name);
        if (salary != null)
            this.salary = salary;
        if (department != null)
            this.department = department;
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
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
