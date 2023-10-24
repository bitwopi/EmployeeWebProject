package com.example.EmployeeProject.controller;

import com.example.EmployeeProject.service.DepartmentService;
import com.example.EmployeeProject.service.impl.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/min-salary")
    public Employee minSalary(@RequestParam("department") Integer department) {
        return departmentService.getEmployeeWithMinSalary(department);
    }

    @GetMapping("/max-salary")
    public Employee maxSalary(@RequestParam("department") Integer department) {
        return departmentService.getEmployeeWithMaxSalary(department);
    }

    @RequestMapping(value = "/all", params = "department")
    public List<Employee> allInDepartment(@RequestParam(value = "department") Integer department) {
        return departmentService.getEmployeesInDepartment(department);
    }


    @RequestMapping("/all")
    public Map<Integer, List<Employee>> allInDepartment() {
        return departmentService.getAllEmployees();
    }
}
