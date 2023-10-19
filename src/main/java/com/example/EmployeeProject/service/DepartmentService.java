package com.example.EmployeeProject.service;

import com.example.EmployeeProject.service.impl.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee getEmployeeWithMinSalary(Integer department);
    Employee getEmployeeWithMaxSalary(Integer department);
    List<Employee> getEmployeesInDepartment(Integer department);
    Map<Integer, Employee> getAllEmployees();
}
