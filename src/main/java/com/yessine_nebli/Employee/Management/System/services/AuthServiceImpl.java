package com.yessine_nebli.Employee.Management.System.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yessine_nebli.Employee.Management.System.config.JwtHelper;
import com.yessine_nebli.Employee.Management.System.dtos.LoginRequest;
import com.yessine_nebli.Employee.Management.System.dtos.SignupRequest;
import com.yessine_nebli.Employee.Management.System.entities.Employee;
import com.yessine_nebli.Employee.Management.System.entities.UserAccount;
import com.yessine_nebli.Employee.Management.System.repositories.EmployeeRepo;
import com.yessine_nebli.Employee.Management.System.repositories.UserAccountRepo;
import com.yessine_nebli.Employee.Management.System.shared.CustomResponseException;

@Service
public class AuthServiceImpl {

    private final UserAccountRepo userAccountRepo;
    private final EmployeeRepo employeeRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtHelper jwtHelper;

    AuthServiceImpl(JwtHelper jwtHelper, UserAccountRepo userAccountRepo, EmployeeRepo employeeRepo, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.jwtHelper = jwtHelper;
        this.userAccountRepo = userAccountRepo;
        this.employeeRepo = employeeRepo;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

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

    public String login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.username(),
                        loginRequest.password()));

        UserAccount user = userAccountRepo.findOneByUsername(loginRequest.username())
                .orElseThrow(() -> CustomResponseException.BadCredentials());

        Map<String, Object> customClaims = new HashMap<>();
        customClaims.put("userId", user.getId());
        customClaims.put("role", user.getRole());
        return jwtHelper.generateToken(customClaims, user);
    }

}
