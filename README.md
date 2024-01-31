# Employee Management System

## EmployeeController
Base URL: /api

POST / : Create a new employee. It takes an EmployeeRequest as input and returns a EmployeeResponse.

GET /{id} : Get an employee by its ID. It takes an ID as a path variable and returns an employee.

GET /{department} : Get employee by its department. It takes an ID as a path variable and returns a list of employees.
