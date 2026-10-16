package com.yessine_nebli.Employee.Management.System.abstracts;

import java.util.ArrayList;
import java.util.UUID;

import com.yessine_nebli.Employee.Management.System.entities.Employee;

public interface EmployeeService {

    Employee findOne(UUID employeeId);

    ArrayList<Employee> findAll();

    void deleteOne(UUID employeeId);

    Employee updateOne(UUID employeeId, Employee updatedEmployee);

    Employee createOne(Employee employee);
}
