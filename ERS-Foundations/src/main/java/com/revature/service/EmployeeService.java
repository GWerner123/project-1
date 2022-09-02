package com.revature.service;

import com.revature.data.DaoFactory;
import com.revature.entity.Employee;
import com.revature.data.EmployeeDao;
import com.revature.data.EmployeeDaoImpl;
import com.revature.entity.Ticket;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {

    //insert
    public Employee insert(Employee employee) {
        EmployeeDao employeeDao = DaoFactory.getEmployeeDao();
        return employeeDao.insert(employee);
    }


    public Employee insertToEmployee(Employee employee){
        EmployeeDao employeeDao = DaoFactory.getEmployeeDao();
        return employeeDao.insertToEmployee(employee);
    }

    public Employee getEmployeeByUsername(String username) {
        EmployeeDao employeeDao = DaoFactory.getEmployeeDao();
        Employee employee = employeeDao.getByUsername(username);
        return employee;
    }


    //this will allow us to check that the username and password match what is in the database
    public Employee login(String username, String password) {
        EmployeeDao employeeDao = DaoFactory.getEmployeeDao();
        Employee employee = employeeDao.getByUsername(username);
        if (employee == null) {
            return null;
        }
        else if(password.equals(employee.getPassword())){
            return employee;
        }
        return null;
    }

    public List<Ticket> getEmployeeTickets(int employeeId) {
        return new ArrayList<>();
    }


    //getAllEmployees
    public List<Employee> getAllEmployees() {
        EmployeeDao employeeDao = DaoFactory.getEmployeeDao();
        return employeeDao.getAllEmployees();
    }

    //update
    public Employee update(Employee employee) {
        EmployeeDao employeeDao = DaoFactory.getEmployeeDao();
        return employeeDao.update(employee);
    }

    //delete
    public boolean delete(int id) {
        EmployeeDao employeeDao = DaoFactory.getEmployeeDao();
        return employeeDao.delete(id);
    }
}
