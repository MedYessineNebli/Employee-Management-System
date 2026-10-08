package com.yessine_nebli.Employee.Management.System.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yessine_nebli.Employee.Management.System.entities.Employee;

//annotation to let spring boot that this class is controller
@RestController 

//annotation to map the requests to this controller
@RequestMapping ("/employees")

public class EmployeeController {

    ArrayList<Employee> employees = new ArrayList<>();

    @GetMapping
    public ArrayList<Employee> findAll() {
        return employees;
    }

    @GetMapping("/{employeeId}")
    public Optional<Employee> findOne(@PathVariable UUID employeeId) {
        Optional<Employee> employee = employees.stream()
            .filter(emp -> emp.getId().equals(employeeId))
            .findFirst();

        return employee;
    }

    @PostMapping 
    public Employee createOne(@RequestBody Employee employee) {
        employee.setId(UUID.randomUUID());
        employee.setDepartmentId(UUID.randomUUID());

        employees.add(employee);
        return employee;
    }

    @DeleteMapping ("/{employeeId}")
    public void deleteOne(@PathVariable UUID employeeId) {
        Optional<Employee> employee = employees.stream()
            .filter(emp -> emp.getId().equals(employeeId))
            .findFirst();

        if (employee.isPresent()) {
            employees.remove(employee.get());
        }
    }

    @PutMapping ("/{employeeId}")
    public Employee updateOne(@PathVariable UUID employeeId, @RequestBody Employee updatedEmployee) {
        Optional<Employee> existingEmployee = employees.stream()
            .filter(emp -> emp.getId().equals(employeeId))
            .findFirst();

        if (existingEmployee.isPresent()) {
            existingEmployee.get().setFirstName(updatedEmployee.getFirstName());
            existingEmployee.get().setLastName(updatedEmployee.getLastName());  
            existingEmployee.get().setEmail(updatedEmployee.getEmail());  
            existingEmployee.get().setPhoneNumber(updatedEmployee.getPhoneNumber());  
            existingEmployee.get().setHireDate(updatedEmployee.getHireDate());  
            existingEmployee.get().setDepartmentId(updatedEmployee.getDepartmentId());  


            return updatedEmployee;
        } else {
            return null;
        }
    }
}
