package com.yessine_nebli.Employee.Management.System.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EmployeeCreate(
    @NotNull (message = "First name is required")
    @Size (min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    String firstName,

    @NotNull (message = "Last name is required")
    @Size (min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    String lastName,

    @Email (message = "Email should be valid")
    @NotNull (message = "Email is required")
    String email,

    @Pattern(regexp = "^\\+?[0-9. ()-]{10,15}$", message = "Phone number is invalid")
    @NotNull (message = "Phone number is required")
    String phoneNumber,

    @NotNull (message = "Hire date is required")
    @PastOrPresent (message = "Hire date cannot be in the future")
    LocalDate hireDate,
    
    @NotNull (message = "Position is required")
    String position
) {

}
