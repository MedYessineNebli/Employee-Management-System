package com.yessine_nebli.Employee.Management.System.controllers;

import java.util.ArrayList;
import java.util.List;
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

import com.yessine_nebli.Employee.Management.System.abstracts.EmployeeService;
import com.yessine_nebli.Employee.Management.System.dtos.EmployeeCreate;
import com.yessine_nebli.Employee.Management.System.dtos.EmployeeUpdate;
import com.yessine_nebli.Employee.Management.System.entities.Employee;
import com.yessine_nebli.Employee.Management.System.shared.GlobalResponse;

import jakarta.validation.Valid;

//annotation to let spring boot that this class is controller
@RestController

// annotation to map the requests to this controller
@RequestMapping("/employees")

public class EmployeeController {

    private final EmployeeService employeeService;
    ArrayList<Employee> employees = new ArrayList<>();

    // constructor injection to inject the employeeService dependency
    EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<GlobalResponse<List<Employee>>> findAll() {

        List<Employee> employees = employeeService.findAll();
        return new ResponseEntity<>(new GlobalResponse<>(employees), HttpStatus.OK);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<GlobalResponse<Employee>> findOne(@PathVariable UUID employeeId) {

        Employee employee = employeeService.findOne(employeeId);
        return new ResponseEntity<>(new GlobalResponse<>(employee), HttpStatus.OK);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteOne(@PathVariable UUID employeeId) {

        employeeService.deleteOne(employeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<GlobalResponse<Employee>> updateOne(
            @PathVariable UUID employeeId,
            @RequestBody @Valid EmployeeUpdate employee) {

        Employee updatedEmployee = employeeService.updateOne(employeeId, employee);
        return new ResponseEntity<>(new GlobalResponse<>(updatedEmployee), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GlobalResponse<Employee>> createOne(@RequestBody @Valid EmployeeCreate employee) {

        Employee newEmployee = employeeService.createOne(employee);
        return new ResponseEntity<>(new GlobalResponse<>(newEmployee), HttpStatus.CREATED);
    }
}
