package com.revature.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.entity.Employee;
import com.revature.entity.Manager;
import com.revature.entity.Ticket;
import com.revature.service.EmployeeService;
import com.revature.service.ManagerService;
import com.revature.service.TicketService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ManagerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        ManagerService managerService = new ManagerService();
        EmployeeService employeeService = new EmployeeService();


        HttpSession session = req.getSession();
        String username1 = (String)session.getAttribute("username");
        Manager manager1 = managerService.getByUsername(username1);
        if(manager1 != null) {


            String role = req.getParameter("role");


            if (role.equals("manager")) {

                try {
                    ObjectMapper mapper = new ObjectMapper();
                    Employee employee;

                    employee = mapper.readValue(req.getReader(), Employee.class);

                    Manager manager = managerService.changeToManager(employee.getUsername());

                    out.println(manager);
                } catch (Exception e) {
                    e.printStackTrace();
                    out.println("That person is already a manager.");
                }

            } else if (role.equals("employee")) {
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    Manager manager;

                    manager = mapper.readValue(req.getReader(), Manager.class);

                    Employee employee = managerService.changeToEmployee(manager.getUsername());

                    out.println(employee);
                } catch (Exception e) {
                    e.printStackTrace();
                    out.println("That person is already an employee.");
                }
            }
        }
        else {
            out.println("You must be a manager to change a user's role.");
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        TicketService ticketService = new TicketService();
        ManagerService managerService = new ManagerService();

        HttpSession session = req.getSession();
        String username = (String)session.getAttribute("username");
        Manager manager = managerService.getByUsername(username);
        if(manager != null) {

            ObjectMapper mapper = new ObjectMapper();
            Ticket ticket;

            ticket = mapper.readValue(req.getReader(), Ticket.class);

            if (ticketService.updateStatus(ticket.getId(), ticket.getStatus())) {
                if (ticket.getStatus().equals("approved")) {
                    out.println("Ticket Approved: " + "#" + ticket.getId());
                } else if (ticket.getStatus().equals("denied")) {
                    out.println("Ticket Denied: " + "#" + ticket.getId());
                }
            }

            List<Ticket> tickets = ticketService.getAllPendingTicketsManager();

            out.println("Remaining tickets to process: " + tickets.size());
            for (Ticket ticket2 : tickets) {
                out.println(ticket2);
            }
        }
        else {
            out.println("Sorry, you must be a manager to process tickets.");
        }
    }
}
