package com.yessine_nebli.Employee.Management.System.abstracts;

import java.util.List;
import java.util.UUID;

import com.yessine_nebli.Employee.Management.System.dtos.EmployeeCreate;
import com.yessine_nebli.Employee.Management.System.dtos.EmployeeUpdate;
import com.yessine_nebli.Employee.Management.System.entities.Employee;

public interface EmployeeService {

    Employee findOne(UUID employeeId);

    List<Employee> findAll();

    void deleteOne(UUID employeeId);

    Employee updateOne(UUID employeeId, EmployeeUpdate updatedEmployee);

    Employee createOne(EmployeeCreate employee);
}
