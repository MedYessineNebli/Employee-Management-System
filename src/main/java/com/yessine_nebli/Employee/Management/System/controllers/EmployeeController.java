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
import com.yessine_nebli.Employee.Management.System.shared.CustomResponseException;
import com.yessine_nebli.Employee.Management.System.shared.GlobalResponse;

import jakarta.validation.Valid;

//annotation to let spring boot that this class is controller
@RestController 

//annotation to map the requests to this controller
@RequestMapping ("/employees")

public class EmployeeController {

    ArrayList<Employee> employees = new ArrayList<>();

    @GetMapping
    public ResponseEntity<GlobalResponse<ArrayList<Employee>>> findAll() {
        return new ResponseEntity<>(new GlobalResponse<>(employees), HttpStatus.OK);
    }

    @GetMapping("/{employeeId}")
    public  ResponseEntity<GlobalResponse<Employee>> findOne(@PathVariable UUID employeeId) {
        Employee employee = employees.stream()
            .filter(emp -> emp.getId().equals(employeeId))
            .findFirst()
            .orElseThrow(()-> CustomResponseException.ResourceNotFound(
                "Employee with id : " + employeeId + " not found"
            ));

            return new ResponseEntity<>(new GlobalResponse<>(employee), HttpStatus.OK);
    }

    @PostMapping 
    public ResponseEntity<GlobalResponse<Employee>> createOne(@RequestBody @Valid Employee employee) {
        employee.setId(UUID.randomUUID());
        employee.setDepartmentId(UUID.randomUUID());

        employees.add(employee);
        return new ResponseEntity<>(new GlobalResponse<>(employee), HttpStatus.CREATED);
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
    public ResponseEntity<GlobalResponse<Employee>> updateOne(@PathVariable UUID employeeId, @RequestBody @Valid Employee updatedEmployee) {
        
        Employee existingEmployee = employees.stream()
            .filter(emp -> emp.getId().equals(employeeId))
            .findFirst() 
            .orElseThrow(()-> CustomResponseException.ResourceNotFound(
                "Employee with id : " + employeeId + " not found"
            ));

        existingEmployee.setFirstName(updatedEmployee.getFirstName());
        existingEmployee.setLastName(updatedEmployee.getLastName());  
        existingEmployee.setEmail(updatedEmployee.getEmail());  
        existingEmployee.setPhoneNumber(updatedEmployee.getPhoneNumber());  
        existingEmployee.setHireDate(updatedEmployee.getHireDate());  
        existingEmployee.setDepartmentId(existingEmployee.getDepartmentId()); 

       return new ResponseEntity<>(new GlobalResponse<>(existingEmployee), HttpStatus.OK);
    }
}
