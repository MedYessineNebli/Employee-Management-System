package com.yessine_nebli.Employee.Management.System.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yessine_nebli.Employee.Management.System.dtos.SignupRequest;
import com.yessine_nebli.Employee.Management.System.services.AuthServiceImpl;
import com.yessine_nebli.Employee.Management.System.shared.GlobalResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthServiceImpl authService;

    @PostMapping("/signup")
    public ResponseEntity<GlobalResponse<String>> signup(@RequestBody SignupRequest signupRequest) {

        authService.signup(signupRequest);
        System.out.println("Signup request received: " + signupRequest.employeeId());
        return new ResponseEntity<>(new GlobalResponse<String>("Signed Up"), HttpStatus.CREATED);
    }

    // @PostMapping("/login")
    // public ResponseEntity<GlobalResponse<String>> signin(@RequestBody SignupRequest signupRequest) {

    //     authService.signin(signupRequest);
    //     System.out.println("Signin request received: " + signupRequest.employeeId());
    //     return new ResponseEntity<>(new GlobalResponse<String>("Signed In"), HttpStatus.CREATED);
    // }
}
