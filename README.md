# Employee Management System
This project provides a RESTful API for an Employee Management System application. Employees can be added, retrieved by id and retrieved by department.

## EmployeeController
Base URL: /employees

POST / : Create a new employee. It takes an EmployeeRequest as input and returns a EmployeeResponse.

GET /{id} : Get an employee by its ID. It takes an id as a path variable and returns an employee.

GET /employees?department={department} : Get employees by its department. It takes a String as a request param and returns a list of employees.
