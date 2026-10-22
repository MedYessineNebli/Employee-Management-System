package com.yessine_nebli.Employee.Management.System.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DepartmentCreate(
    @NotNull (message = "Department name is required")
    @Size (min = 2, max = 50, message = "Department name must be between 2 and 50 characters")
    String name
) {
    
}
