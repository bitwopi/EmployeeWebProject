package com.example.EmployeeProject.service.impl;

import com.example.EmployeeProject.service.DepartmentService;
import com.example.EmployeeProject.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.*;
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
    public Map<Integer, List<Employee>> getAllEmployees() {
        Integer[] departments = new Integer[]{1, 2, 3, 4, 5};
        return Arrays.stream(departments)
                .collect(Collectors
                        .toMap(i->i, i->this.employeeService.getEmployeeList()
                                .values().stream().filter(employee -> employee.getDepartment() == i)
                                .collect(Collectors.toList())));
    }
}
