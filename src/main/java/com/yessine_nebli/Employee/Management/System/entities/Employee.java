package com.yessine_nebli.Employee.Management.System.entities;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//lambok will generate the getters 
@Getter 

//lambok will generate the setters
@Setter 

//lambok will generate the constructor with all the fields
@AllArgsConstructor

public class Employee {

    private UUID id;
    @NotNull (message = "First name is required")
    @Size (min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;
    @NotNull (message = "Last name is required")
    @Size (min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;
    @Email (message = "Email should be valid")
    @Null (message = "Email is required")
    private String email;
    @Pattern(regexp = "^\\+?[0-9. ()-]{10,15}$", message = "Phone number is invalid")
    @Null (message = "Phone number is required")
    private String phoneNumber;
    @NotNull (message = "Hire date is required")
    @PastOrPresent (message = "Hire date cannot be in the future")
    private LocalDate hireDate;
    @NotNull (message = "Position is required")
    private String position;
    private UUID departmentId;

}
