package com.edstem.employeemanagementsystem.service;

import com.edstem.employeemanagementsystem.contract.request.EmployeeRequest;
import com.edstem.employeemanagementsystem.contract.response.EmployeeResponse;
import com.edstem.employeemanagementsystem.model.Employee;
import com.edstem.employeemanagementsystem.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RequiredArgsConstructor
public class EmployeeServiceTest {
    private  EmployeeService employeeService;
    private  EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        employeeRepository = Mockito.mock(EmployeeRepository.class);
        modelMapper = new ModelMapper();
        employeeService = new EmployeeService(employeeRepository,modelMapper);
    }

    @Test
    void testAddEmployee(){
        EmployeeRequest request = new EmployeeRequest("Sharok","sharok@gmail.com","Development");
        Employee employee = modelMapper.map(request, Employee.class);
        EmployeeResponse expectedResponse = modelMapper.map(employee, EmployeeResponse.class);

        when(employeeRepository.existsByName(request.getName())).thenReturn(false);
        when(employeeRepository.save(any())).thenReturn(employee);

        EmployeeResponse actualResponse = employeeService.addEmployee(request);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void viewEmployeeById(){
        Long id = 1L;
        Employee employee = new Employee(id,"Sharok","sharok@gmail.com","Development");
        EmployeeResponse expectedResponse = modelMapper.map(employee, EmployeeResponse.class);
        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));

        EmployeeResponse actualResponse = employeeService.viewEmployeeById(id);
        assertEquals(expectedResponse, actualResponse);
    }


}
