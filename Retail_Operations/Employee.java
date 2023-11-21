package Retail_Operations;

import Helpers.JsonIdentifiable;
//TODO: Start working on the Management menu for Employees.
//BODy: This will be a menu that allows staff members to add, remove, and edit employee information.
public class Employee implements JsonIdentifiable {
    private String name;
    private String employeeID;
    private String position;
    private String username;
    private String password;

    public Employee(String name, String employeeID, String position, String username, String password) {
        this.name = name;
        this.employeeID = employeeID;
        this.position = position;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    

    private String getEmployeeID() {
        return employeeID;
    }
    @Override
    public String getJsonId() {
        return "Employee_" + this.getEmployeeID();
    }

}

