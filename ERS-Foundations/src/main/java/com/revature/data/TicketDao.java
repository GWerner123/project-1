package com.revature.data;

import com.revature.entity.Ticket;

import java.util.List;

public interface TicketDao {

    //CRUD
    //create
    public Ticket insert(Ticket ticket);


    public List<Ticket> getTicketsByStatusAndType(String status, int employeeId, String type);

    public List<Ticket> getTicketsByStatus(String status, int employeeId);

    public List<Ticket> getAllPendingTicketsManager();

    //update
    public boolean updateStatus(int id, String status);

    //delete
    public boolean delete(int id);

}
