package com.example.EmployeeProject.service.impl;

import com.example.EmployeeProject.service.EmployeeService;
import com.example.EmployeeProject.service.exceptions.BadRequestException;
import com.example.EmployeeProject.service.exceptions.EmployeeAlreadyAddedException;
import com.example.EmployeeProject.service.exceptions.EmployeeNotFoundException;
import com.example.EmployeeProject.service.exceptions.EmployeeStorageIsFullException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employeeList;
    private int maxSize;

    public EmployeeServiceImpl() {
        this.employeeList = new HashMap<>(
                Map.of("UsimMatvey", new Employee("Usim", "Matvey", 100.0, 2),
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
            List<String> names = getCapitalizedEmployeeNames(surname, name);
            this.employeeList.put(names.get(0) + names.get(1),
                    new Employee(names.get(0), names.get(1)));
            return this.employeeList.get(names.get(0) + names.get(1));
        }
        throw new EmployeeAlreadyAddedException("This Employee is already exist");
    }

    @Override
    public Employee addEmployee(String surname, String name, Double salary, Integer department) {
        Employee employee = this.addEmployee(surname, name);
        if (salary != null) {
            employee.setSalary(salary);
        }
        if (department != null)
            employee.setDepartment(department);
        return employee;
    }


    @Override
    public Employee removeEmployee(String surname, String name) {
        Employee employee = findEmployee(surname, name);
        return this.employeeList.remove(employee.getSurname() + employee.getName());
    }

    @Override
    public Employee findEmployee(String surname, String name) {
        List<String> names = getCapitalizedEmployeeNames(surname, name);
        Employee result = this.employeeList.get(names.get(0) + names.get(1));
        if (result == null)
            throw new EmployeeNotFoundException("This employee is not exist");
        return result;
    }

    private List<String> getCapitalizedEmployeeNames(String surname, String name) {
        checkEmployeeData(surname, name);
        return new ArrayList<String>(List.of(
                StringUtils.capitalize(StringUtils.lowerCase(surname)),
                StringUtils.capitalize(StringUtils.lowerCase(name))));
    }

    private void checkEmployeeData(String surname, String name) {
        if (StringUtils.isAllBlank(name)
                || StringUtils.isAllBlank(surname)
                || !StringUtils.isAlpha(surname + name)) {
            throw new BadRequestException("Incorrect name or surname");
        }
    }
}

