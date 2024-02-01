package com.edstem.employeemanagementsystem.controller;

import com.edstem.employeemanagementsystem.contract.request.EmployeeRequest;
import com.edstem.employeemanagementsystem.contract.response.EmployeeResponse;
import com.edstem.employeemanagementsystem.model.Employee;
import com.edstem.employeemanagementsystem.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {
    @Autowired private MockMvc mockMvc;
    @MockBean
    private EmployeeService employeeService;

    @Test
    void TestAddEmployee() throws Exception {
        EmployeeRequest request = new EmployeeRequest("Sharok","sharok@gmail.com","Development");
        EmployeeResponse expectedResponse = new EmployeeResponse(1L,"Sharok","sharok@gmail.com","Development");
        when(employeeService.addEmployee(any(EmployeeRequest.class))).thenReturn(expectedResponse);

        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(expectedResponse)));
    }

    @Test
    void TestviewEmployeeById() throws Exception {
        Long id =  1L;
        EmployeeResponse expectedResponse = new EmployeeResponse(1L,"Sharok","sharok@gmail.com","Development");
        when(employeeService.viewEmployeeById(id)).thenReturn(expectedResponse);

        mockMvc.perform(get("/employees/"+id))
                        .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(expectedResponse)));
    }

    @Test
    void TestViewEmployeeByDepartment() throws Exception {
        String department = "Development";
        List<EmployeeResponse> employeeResponse = new ArrayList<>();
        employeeResponse.add(new EmployeeResponse(
                1L,"Sharok","sharok@gmail.com",department
        ));
        when(employeeService.viewEmployeeByDepartment(department)).thenReturn(employeeResponse);
        mockMvc.perform(get("/employees/"+department))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(employeeResponse)));
    }

}
