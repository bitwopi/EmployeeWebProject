package com.example.EmployeeProject.service.impl;

import com.example.EmployeeProject.service.DepartmentService;
import com.example.EmployeeProject.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee getEmployeeWithMinSalary(Integer department) {
        return this.employeeService.getEmployeeList().values().stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparingDouble(Employee::getSalary)).get();
    }

    @Override
    public Employee getEmployeeWithMaxSalary(Integer department) {
        return this.employeeService.getEmployeeList().values().stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparingDouble(Employee::getSalary)).get();
    }

    @Override
    public List<Employee> getEmployeesInDepartment(Integer department) {
        return this.employeeService.getEmployeeList().values().stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, Employee> getAllEmployees() {

        return this.employeeService.getEmployeeList().values().stream()
                .collect(Collectors.toMap(Employee::getDepartment, employee -> employee));
    }
}
