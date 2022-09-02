package com.revature.service;

import com.revature.data.DaoFactory;
import com.revature.data.EmployeeDao;
import com.revature.entity.Employee;
import com.revature.entity.Manager;
import com.revature.data.ManagerDao;
import com.revature.data.ManagerDaoImpl;
import com.revature.service.EmployeeService;

import java.util.List;

public class ManagerService {

    //role change
    public Manager changeToManager(String username) {

        EmployeeService employeeService = new EmployeeService();
        Employee employee = employeeService.getEmployeeByUsername(username);

        Manager manager = new Manager(employee.getId(), employee.getUsername(), employee.getPassword());

        if((manager = insert(manager)) != null){

            employeeService.delete(employee.getId());

            return manager;
        }

        return null;

    }


    public Employee changeToEmployee(String username) {

        EmployeeService employeeService = new EmployeeService();
        Manager manager = getByUsername(username);

        Employee employee = new Employee(manager.getId(), manager.getUsername(), manager.getPassword());

        if((employee = employeeService.insertToEmployee(employee)) != null){

            delete(manager.getId());

            return employee;
        }

        return null;

    }


    //insert
    public Manager insert(Manager manager) {
        ManagerDao managerDao = DaoFactory.getManagerDao();
        return managerDao.insert(manager);
    }

    //this will allow us to check that the username and password match what is in the database
    public Manager login(String username, String password) {
        ManagerDao managerDao = DaoFactory.getManagerDao();
        Manager manager = managerDao.getByUsername(username);
        if (manager == null) {
            return null;
        }
        else if(password.equals(manager.getPassword())){
            return manager;
        }
        return null;
    }

    //getByUsername
    public Manager getByUsername(String username) {
        ManagerDao managerDao = DaoFactory.getManagerDao();
        return managerDao.getByUsername(username);
    }

    //getAllManagers
    public List<Manager> getAllManagers() {
        ManagerDao managerDao = DaoFactory.getManagerDao();
        return managerDao.getAllManagers();
    }

    //update
    public Manager update(Manager manager) {
        ManagerDao managerDao = DaoFactory.getManagerDao();
        return managerDao.update(manager);
    }

    //delete
    public boolean delete(int id) {
        ManagerDao managerDao = DaoFactory.getManagerDao();
        return managerDao.delete(id);
    }
}
