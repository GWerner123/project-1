package com.revature.data;

public class DaoFactory {

    //variables
    private static EmployeeDao employeeDao = null;
    private static ManagerDao managerDao = null;
    private static TicketDao ticketDao = null;

    //DaoFactory constructor
    private DaoFactory() {

    }

    //methods to get the entity DAO's
    public static EmployeeDao getEmployeeDao() {
        if(employeeDao == null) {
            employeeDao = new EmployeeDaoImpl();
        }
        return employeeDao;
    }

    public static ManagerDao getManagerDao() {
        if(managerDao == null) {
            managerDao = new ManagerDaoImpl();
        }
        return managerDao;
    }

    public static TicketDao getTicketDao() {
        if(ticketDao == null) {
            ticketDao = new TicketDaoImpl();
        }
        return ticketDao;
    }
}
