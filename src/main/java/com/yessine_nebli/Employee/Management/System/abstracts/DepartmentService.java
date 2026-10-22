package com.yessine_nebli.Employee.Management.System.abstracts;

import java.util.List;
import java.util.UUID;

import com.yessine_nebli.Employee.Management.System.dtos.DepartmentCreate;
import com.yessine_nebli.Employee.Management.System.entities.Department;

public interface DepartmentService {

    Department findOne(UUID departmentId);

    List<Department> findAll();

    Department createOne(DepartmentCreate departmentCreate);

    void deleteOne(UUID departmentId);
}
