package com.yessine_nebli.Employee.Management.System.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yessine_nebli.Employee.Management.System.abstracts.DepartmentService;
import com.yessine_nebli.Employee.Management.System.dtos.DepartmentCreate;
import com.yessine_nebli.Employee.Management.System.entities.Department;
import com.yessine_nebli.Employee.Management.System.shared.GlobalResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<GlobalResponse<List<Department>>> findAll() {

        List<Department> departments = departmentService.findAll();
        return new ResponseEntity<>(new GlobalResponse<>(departments), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GlobalResponse<Department>> createOne(@RequestBody @Valid DepartmentCreate departmentCreate) {

        Department newDepartment = departmentService.createOne(departmentCreate);
        return new ResponseEntity<>(new GlobalResponse<>(newDepartment), HttpStatus.CREATED);
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<GlobalResponse<Department>> findOne(@PathVariable UUID departmentId) {

        Department department = departmentService.findOne(departmentId);
        return new ResponseEntity<>(new GlobalResponse<>(department), HttpStatus.OK);
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<Void> deleteOne(@PathVariable UUID departmentId) {

        departmentService.deleteOne(departmentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
