package com.edstem.employeemanagementsystem.contract.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmployeeRequest {
    @NotBlank(message = "Employee name should not be blank")
    private String name;

    @NotBlank(message = "Email should not be blank")
    private String email;

    private String department;
}
