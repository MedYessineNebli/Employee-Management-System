package com.yessine_nebli.Employee.Management.System.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yessine_nebli.Employee.Management.System.abstracts.EmployeeService;
import com.yessine_nebli.Employee.Management.System.dtos.EmployeeCreate;
import com.yessine_nebli.Employee.Management.System.dtos.EmployeeUpdate;
import com.yessine_nebli.Employee.Management.System.entities.Department;
import com.yessine_nebli.Employee.Management.System.entities.Employee;
import com.yessine_nebli.Employee.Management.System.repositories.DepartmentRepo;
import com.yessine_nebli.Employee.Management.System.repositories.EmployeeRepo;
import com.yessine_nebli.Employee.Management.System.shared.CustomResponseException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

    @Override
    public Employee findOne(UUID employeeId) {

        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> CustomResponseException.ResourceNotFound(
                        "Employee with id : " + employeeId + " not found"));

        return employee;
    }

    @Override
    public List<Employee> findAll() {

        return employeeRepo.findAll();
    }

    @Override
    public void deleteOne(UUID employeeId) {

        Optional<Employee> employee = employeeRepo.findById(employeeId);
        employee.ifPresent(value -> employeeRepo.deleteById(value.getId()));
    }

    @Override
    public Employee updateOne(UUID employeeId, EmployeeUpdate updatedEmployee) {

        Employee existingEmployee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> CustomResponseException.ResourceNotFound(
                        "Employee with id : " + employeeId + " not found"));

        existingEmployee.setFirstName(updatedEmployee.firstName());
        existingEmployee.setLastName(updatedEmployee.lastName());
        existingEmployee.setPhoneNumber(updatedEmployee.phoneNumber());
        existingEmployee.setPosition(updatedEmployee.position());

        employeeRepo.save(existingEmployee);
        return existingEmployee;
    }

    @Override
    public Employee createOne(EmployeeCreate employeeCreate) {

        Employee employee = new Employee();

        Department department = departmentRepo.findById(employeeCreate.departmentId())
                .orElseThrow(() -> CustomResponseException.ResourceNotFound(
                        "Department with id : " + employeeCreate.departmentId() + " not found"));

        employee.setFirstName(employeeCreate.firstName());
        employee.setLastName(employeeCreate.lastName());
        employee.setEmail(employeeCreate.email());
        employee.setPhoneNumber(employeeCreate.phoneNumber());
        employee.setHireDate(employeeCreate.hireDate());
        employee.setPosition(employeeCreate.position());
        employee.setDepartment(department);

        employeeRepo.save(employee);
        return employee;
    }

}
