package com.yessine_nebli.Employee.Management.System.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yessine_nebli.Employee.Management.System.entities.Department;

@Repository 
public interface DepartmentRepo extends JpaRepository<Department, UUID> {

}
