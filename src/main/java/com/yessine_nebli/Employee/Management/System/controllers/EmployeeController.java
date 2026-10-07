package com.yessine_nebli.Employee.Management.System.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yessine_nebli.Employee.Management.System.entities.Employee;

//annotation to let spring boot that this class is controller
@RestController 

//annotation to map the requests to this controller
@RequestMapping ("/employees")

public class EmployeeController {

    ArrayList<Employee> employees = new ArrayList<>(
        List.of(
            new Employee(
                UUID.randomUUID(),
                "John",
                "Doe",
                "john.doe@example.com",
                "123-456-7890",
                LocalDate.of(2020, 1, 15),
                "Software Engineer",
                UUID.randomUUID()
            ),
             new Employee(
                UUID.randomUUID(),
                "Jane",
                "Smith",
                "jane.smith@example.com",
                "987-654-3210",
                LocalDate.of(2026, 1, 15),
                "Product Manager",
                UUID.randomUUID()
            )
        )
    );

    @GetMapping
    public ArrayList<Employee> findAll() {
        return employees;
    }

    @GetMapping("/{employeeId}")
    public Employee findOne(@PathVariable UUID employeeId) {
        Employee employee = employees.stream()
            .filter(e -> e.getId().equals(employeeId))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));

        return employee;
    }
}
