package com.revature.data;

import com.revature.entity.Employee;
import com.revature.entity.Ticket;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;

public class TicketDaoImpl implements TicketDao {

    //create a connection variable
    Connection connection;

    public TicketDaoImpl() {
        connection = ConnectionFactory.getConnection();
    }

    //create a new ticket
    @Override
    public Ticket insert(Ticket ticket) {
        String sql = "insert into ticket (id,status,amount,description,employee_id,ticket_type) values (default, ?, ?, ?, ?, ?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, ticket.getStatus());
            preparedStatement.setBigDecimal(2, ticket.getAmount());
            preparedStatement.setString(3, ticket.getDescription());
            preparedStatement.setInt(4, ticket.getEmployeeId());
            preparedStatement.setString(5, ticket.getType());

            int count = preparedStatement.executeUpdate();
            if(count == 1) {
                System.out.println("Your ticket was successfully created.");

                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                resultSet.next();
                int generatedId = resultSet.getInt(1);
                ticket.setId(generatedId);

            } else {
                System.out.println("Unable to create ticket.");
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Unable to create ticket.");
        }
        return ticket;
    }












    @Override
    public List<Ticket> getTicketsByStatusAndType(String status, int employeeId, String type) {
        List<Ticket> tickets = new ArrayList<>();


        String sql = "select * from ticket where status = ? and employee_id = ? and ticket_type = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, employeeId);
            preparedStatement.setString(3, type);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String statusDb = resultSet.getString("status");
                BigDecimal amount = resultSet.getBigDecimal("amount");
                String description = resultSet.getString("description");
                int employeeIdDb = resultSet.getInt("employee_id");
                String typeDb = resultSet.getString("ticket_type");
                Ticket ticket = new Ticket(id, statusDb, amount, description, employeeIdDb, typeDb);

                tickets.add(ticket);
            }
        } catch(SQLException e){
            System.out.println("Unable to retrieve the tickets.");
            e.printStackTrace();
        }
        return tickets;
    }


    @Override
    public List<Ticket> getTicketsByStatus(String status, int employeeId) {
        List<Ticket> tickets = new ArrayList<>();

        String typeCondition = "";

        String sql = "select * from ticket where status = ? and employee_id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, employeeId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String statusDb = resultSet.getString("status");
                BigDecimal amount = resultSet.getBigDecimal("amount");
                String description = resultSet.getString("description");
                int employeeIdDb = resultSet.getInt("employee_id");
                String type = resultSet.getString("ticket_type");
                Ticket ticket = new Ticket(id, statusDb, amount, description, employeeIdDb, type);

                tickets.add(ticket);
            }
        } catch(SQLException e){
            System.out.println("Unable to retrieve the tickets.");
            e.printStackTrace();
        }
        return tickets;
    }



    @Override
    public List<Ticket> getAllPendingTicketsManager() {
        List<Ticket> pendingTickets = new ArrayList<>();

        String sql = "select * from ticket where status = 'pending';";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String status = resultSet.getString("status");
                BigDecimal amount = resultSet.getBigDecimal("amount");
                String description = resultSet.getString("description");
                int employeeId = resultSet.getInt("employee_id");
                String type = resultSet.getString("ticket_type");
                Ticket ticket = new Ticket(id, status, amount, description, employeeId, type);

                pendingTickets.add(ticket);
            }
        } catch(SQLException e){
            System.out.println("Unable to retrieve the tickets.");
            e.printStackTrace();
        }
        return pendingTickets;
    }

    @Override
    public boolean updateStatus(int id, String status) {
        String sql = "update ticket set status = ? where id = ? and status = 'pending';";

       try {
           PreparedStatement preparedStatement = connection.prepareStatement(sql);
           preparedStatement.setString(1, status);
           preparedStatement.setInt(2, id);

           int count = preparedStatement.executeUpdate();
           if(count == 1){
               return true;

           }

       } catch(SQLException e){
           System.out.println("Ticket update failed.");
           e.printStackTrace();
       }
        return false;
    }


    @Override
    public boolean delete(int id) {
        return true;
    }
}
