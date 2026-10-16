package com.yessine_nebli.Employee.Management.System.entities;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//lambok will generate the getters 
@Getter

// lambok will generate the setters
@Setter

// lambok will generate the constructor with all the fields
@AllArgsConstructor

@NoArgsConstructor 
public class Employee {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate hireDate;
    private String position;
    private UUID departmentId;

}
