package com.edstem.employeemanagementsystem.controller;

import com.edstem.employeemanagementsystem.contract.request.EmployeeRequest;
import com.edstem.employeemanagementsystem.contract.response.EmployeeResponse;
import com.edstem.employeemanagementsystem.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping()
    public EmployeeResponse addEmployee(@Valid @RequestBody EmployeeRequest request) {
        return employeeService.addEmployee(request);
    }

    @GetMapping("/{id}")
    public EmployeeResponse viewEmployeeById(@PathVariable Long id) {
        return employeeService.viewEmployeeById(id);
    }

    @GetMapping
    public List<EmployeeResponse> viewEmployeeByDepartment(@RequestParam String department) {
        return employeeService.viewEmployeeByDepartment(department);
    }

}
