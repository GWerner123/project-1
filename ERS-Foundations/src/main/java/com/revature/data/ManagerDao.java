package com.revature.data;

import com.revature.entity.Employee;
import com.revature.entity.Manager;

import java.util.List;

public interface ManagerDao {

    //CRUD

    //create
    public Manager insert(Manager manager);

    //read one by username and password
    public Manager getByUsername(String username);

    //read all
    public List<Manager> getAllManagers();

    //update
    public Manager update(Manager manager);

    //delete
    public boolean delete(int id);
}
