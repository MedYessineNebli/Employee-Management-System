package com.yessine_nebli.Employee.Management.System.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yessine_nebli.Employee.Management.System.abstracts.DepartmentService;
import com.yessine_nebli.Employee.Management.System.dtos.DepartmentCreate;
import com.yessine_nebli.Employee.Management.System.entities.Department;
import com.yessine_nebli.Employee.Management.System.repositories.DepartmentRepo;
import com.yessine_nebli.Employee.Management.System.shared.CustomResponseException;

@Service 
public class DepartmentServiceImpl implements  DepartmentService{

    @Autowired
    private DepartmentRepo departmentRepo;

    @Override
    public Department createOne(DepartmentCreate departmentCreate) {
        
        Department department = new Department();
        department.setName(departmentCreate.name());
        departmentRepo.save(department);
        return department;
    }

    @Override
    public void deleteOne(UUID departmentId) {
       
        Optional<Department> department = departmentRepo.findById(departmentId);
        department.ifPresent(value -> departmentRepo.deleteById(value.getId()));
    }

    @Override
    public List<Department> findAll() {

        return departmentRepo.findAll();
    }

    @Override
    public Department findOne(UUID departmentId) {
        
        Department department = departmentRepo.findById(departmentId)
                .orElseThrow(() -> CustomResponseException.ResourceNotFound(
                        "Department with id : " + departmentId + " not found"));

        return department;
    }

}
