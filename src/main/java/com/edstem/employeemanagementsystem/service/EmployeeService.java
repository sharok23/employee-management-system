package com.edstem.employeemanagementsystem.service;

import com.edstem.employeemanagementsystem.contract.request.EmployeeRequest;
import com.edstem.employeemanagementsystem.contract.response.EmployeeResponse;
import com.edstem.employeemanagementsystem.exception.EntityAlreadyExistsException;
import com.edstem.employeemanagementsystem.exception.EntityNotFoundException;
import com.edstem.employeemanagementsystem.model.Employee;
import com.edstem.employeemanagementsystem.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    public EmployeeResponse addEmployee(EmployeeRequest request) {
        if (employeeRepository.existsByName(request.getName())){
            throw new EntityAlreadyExistsException("Employee");
        }
        Employee newEmployee = modelMapper.map(request,Employee.class);
        Employee savedEmployee = employeeRepository.save(newEmployee);
        return modelMapper.map(savedEmployee,EmployeeResponse.class);
    }

    public EmployeeResponse viewEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Employee",id));
        return modelMapper.map(employee,EmployeeResponse.class);
    }

    public List<EmployeeResponse> viewEmployeeByDepartment(String department) {
        List<Employee> employees =  employeeRepository.findByDepartment(department);
        if (employees.isEmpty()) {
            throw new EntityNotFoundException("Employee",department);
        }
        return employees.stream().map(employee -> modelMapper.map(employee,EmployeeResponse.class)).collect(Collectors.toList());
    }
}
