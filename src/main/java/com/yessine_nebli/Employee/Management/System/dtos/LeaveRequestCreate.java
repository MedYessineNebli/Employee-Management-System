package com.yessine_nebli.Employee.Management.System.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LeaveRequestCreate(

    @NotNull (message = "Start date is required")
    @FutureOrPresent  (message = "Start date should be in the future")
    LocalDate startDate,
    
    @NotNull (message = "end date is required")
    @FutureOrPresent  (message = "end date should be in the future")
    LocalDate endDate,


    @NotNull (message = "reason is required")
    @Size (min = 2, max = 100, message = "reason must be between 2 and 100 characters")
    String reason
    
) {

}
