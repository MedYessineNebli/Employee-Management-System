package com.yessine_nebli.Employee.Management.System.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.yessine_nebli.Employee.Management.System.abstracts.LeaveRequestService;
import com.yessine_nebli.Employee.Management.System.dtos.EmployeeCreate;
import com.yessine_nebli.Employee.Management.System.dtos.EmployeeUpdate;
import com.yessine_nebli.Employee.Management.System.dtos.LeaveRequestCreate;
import com.yessine_nebli.Employee.Management.System.entities.Employee;
import com.yessine_nebli.Employee.Management.System.entities.LeaveRequest;
import com.yessine_nebli.Employee.Management.System.shared.GlobalResponse;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;


//annotation to let spring boot that this class is controller
@RestController

// annotation to map the requests to this controller
@RequestMapping("/employees")

public class EmployeeController {

    @Autowired 
    private EmployeeService employeeService;

    @Autowired 
    private LeaveRequestService leaveRequestService;

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


    @PostMapping("/{employeeId}/leave-request")
    public ResponseEntity<GlobalResponse<LeaveRequest>> leaveRequest(
            @PathVariable UUID employeeId,
            @RequestBody @Valid LeaveRequestCreate leaveRequest) {

        LeaveRequest newLeaveRequest = leaveRequestService.createOne(leaveRequest, employeeId);
        return new ResponseEntity<>(new GlobalResponse<>(newLeaveRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{employeeId}/leave-requests")
    public ResponseEntity<GlobalResponse<List<LeaveRequest>>> leaveRequestsByEmployeeId(@PathVariable UUID employeeId) {
        List<LeaveRequest> leaveRequests = leaveRequestService.findAllByEmployeeId(employeeId);
        return new ResponseEntity<>(new GlobalResponse<>(leaveRequests), HttpStatus.OK);
    }
    
}
