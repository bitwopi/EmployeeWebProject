package com.example.EmployeeProject.service;

import com.example.EmployeeProject.service.impl.Employee;

import java.util.Map;

public interface EmployeeService {
    Employee addEmployee(String surname, String name);

    Employee removeEmployee(String surname, String name);

    Employee findEmployee(String surname, String name);

    Map<String, Employee> getEmployeeList();
}
