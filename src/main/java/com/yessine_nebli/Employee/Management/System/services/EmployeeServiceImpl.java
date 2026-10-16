package com.yessine_nebli.Employee.Management.System.services;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.yessine_nebli.Employee.Management.System.abstracts.EmployeeService;
import com.yessine_nebli.Employee.Management.System.dtos.EmployeeCreate;
import com.yessine_nebli.Employee.Management.System.dtos.EmployeeUpdate;
import com.yessine_nebli.Employee.Management.System.entities.Employee;
import com.yessine_nebli.Employee.Management.System.shared.CustomResponseException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    ArrayList<Employee> employees = new ArrayList<>();

    @Override
    public Employee findOne(UUID employeeId) {

        Employee employee = employees.stream()
                .filter(emp -> emp.getId().equals(employeeId))
                .findFirst()
                .orElseThrow(() -> CustomResponseException.ResourceNotFound(
                        "Employee with id : " + employeeId + " not found"));

        return employee;
    }

    @Override
    public ArrayList<Employee> findAll() {
        
        return employees;
    }

    @Override
    public void deleteOne(UUID employeeId) {

        Optional<Employee> employee = employees.stream()
                .filter(emp -> emp.getId().equals(employeeId))
                .findFirst();

        if (employee.isPresent()) {
            employees.remove(employee.get());
        }

    }

    @Override
    public Employee updateOne(UUID employeeId, EmployeeUpdate updatedEmployee) {

        Employee existingEmployee = employees.stream()
                .filter(emp -> emp.getId().equals(employeeId))
                .findFirst()
                .orElseThrow(() -> CustomResponseException.ResourceNotFound(
                        "Employee with id : " + employeeId + " not found"));

        existingEmployee.setFirstName(updatedEmployee.firstName());
        existingEmployee.setLastName(updatedEmployee.lastName());
        existingEmployee.setPhoneNumber(updatedEmployee.phoneNumber());
        existingEmployee.setPosition(updatedEmployee.position());

        return existingEmployee;
    }

    @Override
    public Employee createOne(EmployeeCreate employeeCreate) {

        Employee employee = new Employee();

        employee.setId(UUID.randomUUID());
        employee.setDepartmentId(UUID.randomUUID());
        employee.setFirstName(employeeCreate.firstName());
        employee.setLastName(employeeCreate.lastName());
        employee.setEmail(employeeCreate.email());
        employee.setPhoneNumber(employeeCreate.phoneNumber());
        employee.setHireDate(employeeCreate.hireDate());
        employee.setPosition(employeeCreate.position());

        employees.add(employee);
        return employee;
    }

}
