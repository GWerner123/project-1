package com.revature.data;

import com.revature.entity.Employee;

import java.sql.*;
import java.util.List;


public class EmployeeDaoImpl implements EmployeeDao {

    Connection connection;

    public EmployeeDaoImpl(){connection = ConnectionFactory.getConnection();}


    @Override
    public Employee insert(Employee employee) {

        String sql = "insert into employee (id,username,password) values (default,?,?);";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,employee.getUsername());
            preparedStatement.setString(2,employee.getPassword());

            int count = preparedStatement.executeUpdate();
            if(count == 1) {
                System.out.println("The account was successfully created.");

                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                resultSet.next();
                int generatedId = resultSet.getInt(1);
                employee.setId(generatedId);
            } else {
                System.out.println("Something went wrong with the account creation.");
            }
        } catch(SQLException e) {
            System.out.println("Sorry,that username is already taken.");
            return null;
        }
        return employee;
    }


    public Employee insertToEmployee(Employee employee){
        String sql = "insert into employee (id,username,password) values (?,?,?);";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,employee.getId());
            preparedStatement.setString(2,employee.getUsername());
            preparedStatement.setString(3,employee.getPassword());

            int count = preparedStatement.executeUpdate();
            if(count == 1) {
                System.out.println("Manager role change to employee was successful.");

            } else {
                System.out.println("Something went wrong with the role change.");
            }
        } catch(SQLException e) {
            System.out.println("Sorry,that username is already taken.");
            return null;
        }
        return employee;
    }

    @Override
    public Employee getByUsername(String username) {
        String sql = "select * from employee where username = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int id = resultSet.getInt("id");
                String usernameDb = resultSet.getString("username");
                String password = resultSet.getString("password");

                Employee employee = new Employee(id, usernameDb, password);
                return employee;
            }


        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong trying to retrieve the data.");
        }
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return null;
    }

    @Override
    public Employee update(Employee employee) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        String sql = "delete from employee where id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            int count = preparedStatement.executeUpdate();
            if(count == 1) {
                System.out.println("successfully deleted the employee record");
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
