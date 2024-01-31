package com.edstem.employeemanagementsystem.repository;

import com.edstem.employeemanagementsystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    boolean existsByName(String name);

    List<Employee> findByDepartment(String department);
}
