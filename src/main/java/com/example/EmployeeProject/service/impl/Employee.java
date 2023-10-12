package com.example.EmployeeProject.service.impl;

public class Employee {
    private static int counter = 1;
    private String surname;
    private String name;
    private int id;

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

    @Override
    public String toString() {
        return "{" +
                "surname: '" + surname +
                ", name: '" + name +
                ", id: " + id +
                '}';
    }
}
