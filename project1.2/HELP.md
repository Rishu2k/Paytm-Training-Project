# Getting Started

## About

This is an Employee/Department Management Module which supports basic CRUD operations.
* logs have been put in the project (msgs in console)
* @transational has been included in the project
* used hiberbnate relationship (manytoone)
* Redis has been used as cache for department table
* Tests have been written - JUNITs using Mockito 
* All errors page get redirected to error.html inside src/main/resources/templates folder

## Steps To Run Project
1) Run EmplDeptMgmtModApplication inside (com.spring.project12) package as a Java Application

# Department

* It has 4 columns mainly id(primary key), name, description and fund

* Get all departments
    * Send a GET request to http://localhost:8080/department
* Add a new department
     * Send a POST request to http://localhost:8080/department/{"Enter department ID here"} along with a department(JSON) in body
* Update a department
     * Send a PUT request to http://localhost:8080/department/{"Enter department ID here"} along with a department(JSON) in body
* Delete a department
     * Send a DELETE request to http://localhost:8080/department/{"Enter department ID here"}
	
# Employee

* It has 4 columns mainly id(primary key), name, description and amount

* Get all employees
     * Send a GET request to http://localhost:8080/department/{"Enter department ID here"}/employee
* Add a new employee
     * Send a POST request to http://localhost:8080/department/{"Enter department ID here"}/employee along with an employee(JSON) in body
* Update an employee
     * Send a PUT request to http://localhost:8080/department/{"Enter department ID here"}/employee/{"Enter employee ID here"} along with an employee(JSON) in body
* Delete an employee
     * Send a DELETE request to http://localhost:8080/department/{"Enter department ID here"}/employee/{"Enter employee ID here"}	
     
# Payment - Transactional

* A department has fund and it can pay to its employees(amount)
    * If sucessfull the fund of department and amount of employee changes else it all rolls back

* Make a payment
     * Send a POST request to http://localhost:8080/payment along with a paymentInfo(JSON) in body
    * PaymentInfo includes departmentId, employeeId and the amount to be transferred from department's fund to employee 
    
# Hibernate relationship

* Department and employee have many to one relationship

# Redis cache

* Redis has been used to cache departments table with time to live = 10 mins

# JUNITs using Mockito

* Testing can be done by running EmplDeptMgmtModApplicationTests or HttpRequestTest as JavaApplication
* It tests get and post requests on department
     
## Database
* DB used is H2
* H2 console can be accessed at http://localhost:8080/h2-console
     * Username - username
     * Password - password