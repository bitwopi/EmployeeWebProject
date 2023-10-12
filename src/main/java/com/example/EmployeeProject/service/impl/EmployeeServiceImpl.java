package com.example.EmployeeProject.service.impl;

import com.example.EmployeeProject.service.EmployeeService;
import com.example.EmployeeProject.service.exceptions.EmployeeAlreadyAddedException;
import com.example.EmployeeProject.service.exceptions.EmployeeNotFoundException;
import com.example.EmployeeProject.service.exceptions.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private List<Employee> employeeList;
    private int max_size;

    public EmployeeServiceImpl() {
        this.employeeList = new ArrayList<>();
        max_size = 10;
    }

    public EmployeeServiceImpl(List<Employee> employeeList, int max_size) {
        if (employeeList != null) {
            this.employeeList = employeeList;
            this.max_size = max_size;
        } else
            this.employeeList = new ArrayList<>();
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    @Override
    public Employee addEmployee(String surname, String name) {
        if (this.employeeList.size() == this.max_size) {
            throw new EmployeeStorageIsFullException("You've reached the maximum storage size");
        }
        try{
            this.findEmployee(surname, name);
        }catch (EmployeeNotFoundException e){
            this.employeeList.add(new Employee(surname, name));
            return employeeList.get(employeeList.size() - 1);
        }
        throw new EmployeeAlreadyAddedException("This Employee is already exist");
    }

    @Override
    public Employee removeEmployee(String surname, String name) {
        Employee employee = findEmployee(surname, name);
        this.employeeList.remove(employee);
        return employee;
    }

    @Override
    public Employee findEmployee(String surname, String name) {
        for (Employee employee : this.employeeList) {
            if (employee.getSurname().equals(surname) & employee.getName().equals(name))
                return employee;
        }
        throw new EmployeeNotFoundException("This employee is not exist");
    }
}
