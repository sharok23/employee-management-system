package com.edstem.employeemanagementsystem.contract.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class EmployeeResponse {
    private Long id;
    private String name;
    private String email;
    private String department;
}
