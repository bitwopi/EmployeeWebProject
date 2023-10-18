package com.example.EmployeeProject.controller;

import com.example.EmployeeProject.service.EmployeeService;
import com.example.EmployeeProject.service.impl.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee add(
            @RequestParam("surname") String surname,
            @RequestParam("name") String name
    ) {
        return employeeService.addEmployee(surname, name);
    }

    @GetMapping("/remove")
    public Employee remove(
            @RequestParam("surname") String surname,
            @RequestParam("name") String name
    ) {
        return employeeService.removeEmployee(surname, name);
    }

    @GetMapping("/find")
    public Employee find(
            @RequestParam("surname") String surname,
            @RequestParam("name") String name
    ) {
        return employeeService.findEmployee(surname, name);
    }

    @GetMapping("/list")
    public Collection<Employee> list() {
        return employeeService.getEmployeeList().values();
    }
}
