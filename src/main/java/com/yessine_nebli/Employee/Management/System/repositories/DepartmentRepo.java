package com.yessine_nebli.Employee.Management.System.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yessine_nebli.Employee.Management.System.entities.Department;

public interface DepartmentRepo extends JpaRepository<Department, UUID> {

}
