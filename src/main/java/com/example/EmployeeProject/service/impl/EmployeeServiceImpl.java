package com.example.EmployeeProject.service.impl;

import com.example.EmployeeProject.service.EmployeeService;
import com.example.EmployeeProject.service.exceptions.EmployeeAlreadyAddedException;
import com.example.EmployeeProject.service.exceptions.EmployeeNotFoundException;
import com.example.EmployeeProject.service.exceptions.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employeeList;
    private int maxSize;

    public EmployeeServiceImpl() {
        this.employeeList = new HashMap<>(Map.of("UsimMatvey", new Employee("Usim", "Matvey", 100.0, 2),
                "GramoteevEvgeniy", new Employee("Gramoteev", "Evgeniy", 1000.0, 2),
                "BelogayDima", new Employee("Belogay", "Dima", 200.0, 1)));
        maxSize = 10;
    }

    public EmployeeServiceImpl(Map<String, Employee> employeeList, int max_size) {
        if (employeeList != null) {
            this.employeeList = employeeList;
            this.maxSize = max_size;
        } else
            this.employeeList = new HashMap<>();
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public Map<String, Employee> getEmployeeList() {
        return employeeList;
    }

    @Override
    public Employee addEmployee(String surname, String name) {
        if (this.employeeList.size() == this.maxSize) {
            throw new EmployeeStorageIsFullException("You've reached the maximum storage size");
        }
        try {
            this.findEmployee(surname, name);
        } catch (EmployeeNotFoundException e) {
            this.employeeList.put(surname + name, new Employee(surname, name));
            return employeeList.get(surname + name);
        }
        throw new EmployeeAlreadyAddedException("This Employee is already exist");
    }

    @Override
    public Employee addEmployee(String surname, String name, Double salary, Integer department) {
        Employee employee = this.addEmployee(surname, name);
        employee.setSalary(salary);
        employee.setDepartment(department);
        return employee;
    }


    @Override
    public Employee removeEmployee(String surname, String name) {
        Employee employee = findEmployee(surname, name);
        this.employeeList.remove(surname + name);
        return employee;
    }

    @Override
    public Employee findEmployee(String surname, String name) {
        Employee result = this.employeeList.get(surname + name);
        if (result == null)
            throw new EmployeeNotFoundException("This employee is not exist");
        return result;
    }
}

