# Employee Management System

## EmployeeController
Base URL: /api

POST /create : Create a new employee. It takes an EmployeeRequest as input and returns a EmployeeResponse.

GET /{id} : Get an employee by its ID. It takes an id as a path variable and returns an employee.

GET /{department} : Get employees by its department. It takes a String as a request param and returns a list of employees.
