package com.edstem.employeemanagementsystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.edstem.employeemanagementsystem.contract.request.EmployeeRequest;
import com.edstem.employeemanagementsystem.contract.response.EmployeeResponse;
import com.edstem.employeemanagementsystem.exception.EntityAlreadyExistsException;
import com.edstem.employeemanagementsystem.exception.EntityNotFoundException;
import com.edstem.employeemanagementsystem.model.Employee;
import com.edstem.employeemanagementsystem.repository.EmployeeRepository;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

@RequiredArgsConstructor
public class EmployeeServiceTest {
    private EmployeeService employeeService;
    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        employeeRepository = Mockito.mock(EmployeeRepository.class);
        modelMapper = new ModelMapper();
        employeeService = new EmployeeService(employeeRepository, modelMapper);
    }

    @Test
    void testAddEmployee() {
        EmployeeRequest request = new EmployeeRequest("Sharok", "sharok@gmail.com", "Development");
        Employee employee = modelMapper.map(request, Employee.class);
        EmployeeResponse expectedResponse = modelMapper.map(employee, EmployeeResponse.class);

        when(employeeRepository.existsByName(request.getName())).thenReturn(true);
        assertThrows(
                EntityAlreadyExistsException.class, () -> employeeService.addEmployee(request));
        when(employeeRepository.existsByName(request.getName())).thenReturn(false);
        when(employeeRepository.save(any())).thenReturn(employee);

        EmployeeResponse actualResponse = employeeService.addEmployee(request);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void viewEmployeeById() {
        Long id = 1L;
        Employee employee = new Employee(id, "Sharok", "sharok@gmail.com", "Development");
        EmployeeResponse expectedResponse = modelMapper.map(employee, EmployeeResponse.class);
        when(employeeRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> employeeService.viewEmployeeById(id));
        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));

        EmployeeResponse actualResponse = employeeService.viewEmployeeById(id);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testEmployeeByDepartment() {
        String department = "Development";
        Employee employeeOne = new Employee(1L, "Sharok", "sharok@gmail.com", department);
        Employee employeeTwo = new Employee(1L, "Midun", "Midun@gmail.com", department);

        List<Employee> employees = Arrays.asList(employeeOne, employeeTwo);
        List<EmployeeResponse> expectedResponse =
                employees.stream()
                        .map(employee -> modelMapper.map(employee, EmployeeResponse.class))
                        .collect(Collectors.toList());

        when(employeeRepository.findByDepartment(department)).thenReturn(Collections.emptyList());
        assertThrows(
                EntityNotFoundException.class,
                () -> employeeService.viewEmployeeByDepartment(department));
        when(employeeRepository.findByDepartment(department)).thenReturn(employees);
        List<EmployeeResponse> actualResponse =
                employeeService.viewEmployeeByDepartment(department);
        assertEquals(expectedResponse, actualResponse);
    }
}
