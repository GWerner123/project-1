package com.revature.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.entity.Ticket;
import com.revature.service.TicketService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class TicketServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        //set up the PrintWriter
        PrintWriter out = resp.getWriter();

        try {

            String status = req.getParameter("status");
            String type = req.getParameter("type");
            HttpSession session = req.getSession();
            int employeeId = (int) session.getAttribute("id");


            //create an instance of the ticket service, so we can use those methods
            TicketService ticketService = new TicketService();

            if (type == null) {
                List<Ticket> tickets = ticketService.getTicketsByStatus(status, employeeId);
                for (Ticket ticket : tickets) {
                    out.println(ticket);
                }
            } else {
                List<Ticket> tickets = ticketService.getTicketsByStatusAndType(status, employeeId, type);
                for (Ticket ticket : tickets) {
                    out.println(ticket);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            out.println("You must be logged in to view past tickets.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        TicketService ticketService = new TicketService();

        ObjectMapper mapper = new ObjectMapper();
        Ticket ticket;

        try{
            ticket = mapper.readValue(req.getReader(), Ticket.class);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(400, "invalid format");
            return;
        }

        try {
            HttpSession session = req.getSession();
            int employeeId = (int) session.getAttribute("id");
            ticket.setEmployeeId(employeeId);
            ticket = ticketService.insert(ticket);
            out.println("Ticket submitted successfully.");
            out.println(ticket);
        } catch (Exception e) {
            out.println("You must be logged in to submit a ticket.");
        }


    }
}
