package com.yessine_nebli.Employee.Management.System.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yessine_nebli.Employee.Management.System.abstracts.LeaveRequestService;
import com.yessine_nebli.Employee.Management.System.dtos.LeaveRequestCreate;
import com.yessine_nebli.Employee.Management.System.entities.Employee;
import com.yessine_nebli.Employee.Management.System.entities.LeaveRequest;
import com.yessine_nebli.Employee.Management.System.repositories.EmployeeRepo;
import com.yessine_nebli.Employee.Management.System.repositories.LeaveRequestRepo;
import com.yessine_nebli.Employee.Management.System.shared.CustomResponseException;

@Service
public class LeaveRequestImpl implements LeaveRequestService {

    @Autowired 
    private EmployeeRepo employeeRepo;

    @Autowired 
    private LeaveRequestRepo leaveRequestRepo;

    @Override
    public LeaveRequest createOne(LeaveRequestCreate leaveRequestCreate, UUID employeeId) {

        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> CustomResponseException.ResourceNotFound(
                        "Employee with id : " + employeeId + " not found"));

        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setStatus("Pending");
        leaveRequest.setReason(leaveRequestCreate.reason());
        leaveRequest.setStartDate(leaveRequestCreate.startDate());
        leaveRequest.setEndDate(leaveRequestCreate.endDate());
        leaveRequest.setEmployee(employee);

        leaveRequestRepo.save(leaveRequest);
        return leaveRequest;
    }

    @Override
    public List<LeaveRequest> findAllByEmployeeId(UUID employeeId) {
        
        return leaveRequestRepo.findAllByEmployeeId(employeeId);
    }

}
