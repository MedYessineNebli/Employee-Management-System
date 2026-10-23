package com.yessine_nebli.Employee.Management.System.abstracts;

import java.util.List;
import java.util.UUID;

import com.yessine_nebli.Employee.Management.System.dtos.LeaveRequestCreate;
import com.yessine_nebli.Employee.Management.System.entities.LeaveRequest;

public interface LeaveRequestService {

    LeaveRequest createOne(LeaveRequestCreate leaveRequest , UUID employeeId);

    List<LeaveRequest> findAllByEmployeeId(UUID employeeId);

}
