package com.revature.data;

import com.revature.entity.Employee;

import java.util.List;

public interface EmployeeDao {

    //here we define methods that will allow us to interact with the database
    //CRUD - create read update delete

    //create or insert
    public Employee insert(Employee employee);

    public Employee insertToEmployee(Employee employee);

    //read or get a single object by id
    public Employee getByUsername(String username);

    //read all or get all objects
    public List<Employee> getAllEmployees();

    //update an object
    public Employee update(Employee employee);

    //delete an object
    public boolean delete(int id);

}
