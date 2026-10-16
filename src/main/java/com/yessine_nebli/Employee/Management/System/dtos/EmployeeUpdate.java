package com.yessine_nebli.Employee.Management.System.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EmployeeUpdate(
    @NotNull (message = "First name is required")
    @Size (min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    String firstName,

    @NotNull (message = "Last name is required")
    @Size (min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    String lastName,

    @Pattern(regexp = "^\\+?[0-9. ()-]{10,15}$", message = "Phone number is invalid")
    @NotNull (message = "Phone number is required")
    String phoneNumber,
    
    @NotNull (message = "Position is required")
    String position
) {

}
