package com.yessine_nebli.Employee.Management.System.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yessine_nebli.Employee.Management.System.entities.LeaveRequest;

public interface LeaveRequestRepo extends JpaRepository<LeaveRequest, UUID> {

    List<LeaveRequest> findAllByEmployeeId(UUID employeeId);

}
