package com.revature.data;

import com.revature.entity.Employee;
import com.revature.entity.Manager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerDaoImpl implements ManagerDao {

    Connection connection;

    public ManagerDaoImpl(){connection = ConnectionFactory.getConnection();}
    @Override
    public Manager insert(Manager manager) {
        String sql = "insert into manager (id,username,password) values (?,?,?);";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,manager.getId());
            preparedStatement.setString(2,manager.getUsername());
            preparedStatement.setString(3,manager.getPassword());

            int count = preparedStatement.executeUpdate();
            if(count == 1) {
                System.out.println("Employee role change to manager was successful.");

            } else {
                System.out.println("Something went wrong with the role change.");
            }
        } catch(SQLException e) {
            System.out.println("Sorry,that username is already taken.");
            return null;
        }
        return manager;
    }


    @Override
    public Manager getByUsername(String username) {
        String sql = "select * from manager where username = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int id = resultSet.getInt("id");
                String usernameDb = resultSet.getString("username");
                String password = resultSet.getString("password");

                Manager manager = new Manager(id, usernameDb, password);
                return manager;
            }

        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong trying to retrieve the data.");
        }
        return null;
    }


    @Override
    public List<Manager> getAllManagers() {
        List<Manager> managers = new ArrayList();
        return managers;
    }

    @Override
    public Manager update(Manager manager) {
        System.out.println("Updating manager: " + manager.toString());
        return manager;
    }

    @Override
    public boolean delete(int id) {
        String sql = "delete from manager where id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            int count = preparedStatement.executeUpdate();
            if(count == 1) {
                System.out.println("successfully deleted the manager record");
                return true;
            }
            else {
                return false;
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Unable to delete.");
        }
        return false;
    }
}
