package com.yessine_nebli.Employee.Management.System.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yessine_nebli.Employee.Management.System.entities.Employee;

import jakarta.validation.Valid;

//annotation to let spring boot that this class is controller
@RestController 

//annotation to map the requests to this controller
@RequestMapping ("/employees")

public class EmployeeController {

    ArrayList<Employee> employees = new ArrayList<>();

    @GetMapping
    public ResponseEntity<ArrayList<Employee>> findAll() {
        return new ResponseEntity<ArrayList<Employee>>(employees, HttpStatus.OK);
    }

    @GetMapping("/{employeeId}")
    public  ResponseEntity<Employee> findOne(@PathVariable UUID employeeId) {
        Optional<Employee> employee = employees.stream()
            .filter(emp -> emp.getId().equals(employeeId))
            .findFirst();

        if(employee.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Employee>(employee.get(), HttpStatus.OK);
    }

    @PostMapping 
    public ResponseEntity<Employee> createOne(@RequestBody @Valid Employee employee) {
        employee.setId(UUID.randomUUID());
        employee.setDepartmentId(UUID.randomUUID());

        employees.add(employee);
        return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
    }

    @DeleteMapping ("/{employeeId}")
    public ResponseEntity<Void> deleteOne(@PathVariable UUID employeeId) {
        Optional<Employee> employee = employees.stream()
            .filter(emp -> emp.getId().equals(employeeId))
            .findFirst();

        if (employee.isPresent()) {
            employees.remove(employee.get());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping ("/{employeeId}")
    public ResponseEntity<Employee> updateOne(@PathVariable UUID employeeId, @RequestBody @Valid Employee updatedEmployee) {
        Optional<Employee> existingEmployee = employees.stream()
            .filter(emp -> emp.getId().equals(employeeId))
            .findFirst();


        if (existingEmployee.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Employee updatEmployee = existingEmployee.get();
        updatEmployee.setFirstName(updatedEmployee.getFirstName());
        updatEmployee.setLastName(updatedEmployee.getLastName());  
        updatEmployee.setEmail(updatedEmployee.getEmail());  
        updatEmployee.setPhoneNumber(updatedEmployee.getPhoneNumber());  
        updatEmployee.setHireDate(updatedEmployee.getHireDate());  
        updatEmployee.setDepartmentId(updatedEmployee.getDepartmentId()); 

        return new ResponseEntity<Employee>(updatedEmployee, HttpStatus.OK);
    }
}
