package com.revature.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.entity.Employee;
import com.revature.entity.Manager;
import com.revature.service.EmployeeService;
import com.revature.service.ManagerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class EmployeeServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //first create a new print writer that will let us print things out to the response to the client
        PrintWriter out = resp.getWriter();


        //then create a new instance of the employee service class for its login and register methods
        //then create an instance of the manager service class for its login method as well
        EmployeeService employeeService = new EmployeeService();
        ManagerService managerService = new ManagerService();

        //create the variables we need to get the client's values they input from the body of the request
        //this will include creating an object mapper that we will call "mapper"
        //and it will also include creating an Employee object as well as a Manager object
        ObjectMapper mapper = new ObjectMapper();
        Employee employee;

        //now we take the information that is stored in the mapper and put it into both the employee
        //object and the manager object (note: we must have default constructors for these entities)
        try {
            employee = mapper.readValue(req.getReader(), Employee.class);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(400, "Invalid format");
            return;
        }
        Manager manager = new Manager(employee.getUsername(), employee.getPassword());

        //now we determine if the person is trying to log in or register based on what value they
        //put in the url for "auth" (either register or login)
        String authType = req.getParameter("auth");

        //if auth = login then...
        if(authType.equals("login")){
            employee = employeeService.login(employee.getUsername(), employee.getPassword());
            manager = managerService.login(manager.getUsername(), manager.getPassword());
            //whichever object is not null after logging in will get a custom message
            if(employee != null) {
                out.println("Welcome, " + employee.getUsername() + "! Login was successful!");
                //store the id of the employee in the session with the value name "id"
                //this will let us be "logged in" so when we use other servlets we can have it just use this id
                req.getSession().setAttribute("id", employee.getId());
                req.getSession().setAttribute("username", employee.getUsername());
            }
            else if(manager != null){
                    out.println("Welcome, " + manager.getUsername() + "! Manager login was successful!");
                    req.getSession().setAttribute("username", manager.getUsername());
            }
            else {
                out.println("Sorry, that username or password does not exist.");
            }
        }

        //else if auth = register then...
        //registration method from the employeeService class.
        else if(authType.equals("register")){
            employee = employeeService.insert(employee);
            if(employee == null){
                out.println("Sorry, that username is already taken.");
            } else {
                out.println("Thanks for creating an account, " + employee.getUsername() + "!");
            }
        }

        //we then store the employee credentials from the session request, and we can use them
        //in our ticket filter request as well as our ticket creation request

    }
}
