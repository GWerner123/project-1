package com.revature.service;

import com.revature.data.DaoFactory;
import com.revature.entity.Ticket;
import com.revature.data.TicketDao;
import com.revature.data.TicketDaoImpl;
import com.revature.entity.Employee;

import java.util.List;

public class TicketService {

    //insert
    public Ticket insert(Ticket ticket) {
        TicketDao ticketDao = DaoFactory.getTicketDao();
        return ticketDao.insert(ticket);
    }



    public List<Ticket> getTicketsByStatusAndType(String status, int employeeId, String type) {
        TicketDao ticketDao = DaoFactory.getTicketDao();
        return ticketDao.getTicketsByStatusAndType(status, employeeId, type);
    }

    public List<Ticket> getTicketsByStatus(String status, int employeeId) {
        TicketDao ticketDao = DaoFactory.getTicketDao();
        return ticketDao.getTicketsByStatus(status, employeeId);
    }


    //get all pending (manager view)
    public List<Ticket> getAllPendingTicketsManager() {
        TicketDao ticketDao = DaoFactory.getTicketDao();
        return ticketDao.getAllPendingTicketsManager();
    }

    //update
    public boolean updateStatus(int id, String status) {
        TicketDao ticketDao = DaoFactory.getTicketDao();
        return ticketDao.updateStatus(id, status);
    }

    //delete
    public boolean delete(int id) {
        TicketDao ticketDao = DaoFactory.getTicketDao();
        return ticketDao.delete(id);
    }
}
