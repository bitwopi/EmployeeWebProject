package com.example.EmployeeProject.service;

import com.example.EmployeeProject.service.impl.Employee;

import java.util.List;

public interface EmployeeService {
    public Employee addEmployee(String surname, String name);
    public Employee removeEmployee(String surname, String name);
    public Employee findEmployee(String surname, String name);
    public List<Employee> getEmployeeList();
}
