package com.yessine_nebli.Employee.Management.System.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SignupRequest(

    @NotNull (message = "username is required")
    @Size (min = 2, max = 50, message = "username must be between 2 and 50 characters")
    String username,

    @NotNull (message = "password is required")
    @Size (min = 2, max = 50, message = "password must be between 2 and 50 characters")
    String password,

    @NotNull (message = "employeeId is required")
    UUID employeeId
) {

}
