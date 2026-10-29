package com.yessine_nebli.Employee.Management.System.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yessine_nebli.Employee.Management.System.dtos.SignupRequest;
import com.yessine_nebli.Employee.Management.System.entities.Employee;
import com.yessine_nebli.Employee.Management.System.entities.UserAccount;
import com.yessine_nebli.Employee.Management.System.repositories.EmployeeRepo;
import com.yessine_nebli.Employee.Management.System.repositories.UserAccountRepo;
import com.yessine_nebli.Employee.Management.System.shared.CustomResponseException;

@Service
public class AuthServiceImpl {

    @Autowired
    private UserAccountRepo userAccountRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void signup(SignupRequest signupRequest) {

        Employee employee = employeeRepo.findById(signupRequest.employeeId())
                .orElseThrow(() -> CustomResponseException.ResourceNotFound(
                        "Employee with id : " + signupRequest.employeeId() + " not found"));

        UserAccount account = new UserAccount();
        account.setUsername(signupRequest.username());
        account.setPassword(passwordEncoder.encode(signupRequest.password()));
        account.setEmployee(employee);

        userAccountRepo.save(account);
    }

}
