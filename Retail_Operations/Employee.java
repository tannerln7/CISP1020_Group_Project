package Retail_Operations;

import Helpers.JsonIdentifiable;
//TODO: Start working on the Management menu for Employees.
//BODy: This will be a menu that allows staff members to add, remove, and edit employee information.
public class Employee implements JsonIdentifiable {
    private final String name;
    private final String employeeID;
    private final String position;
    private final String username;
    private final String password;

    /**
     * the constructor with five parameters
     * @param name the name of the employee
     * @param employeeID the ID of the employee
     * @param position the position of the employee
     * @param username the username of the employee
     * @param password the password of the employee
     */
    public Employee(String name, String employeeID, String position, String username, String password) {
        this.name = name;
        this.employeeID = employeeID;
        this.position = position;
        this.username = username;
        this.password = password;
    }

    /**
     * gets the name of the employee
     * @return the name of the employee
     */
    public String getName() {
        return name;
    }

    /**
     * gets the position of the employee
     * @return the position of the employee
     */
    public String getPosition() {
        return position;
    }

    /**
     * gets the username of the employee
     * @return the username of the employee
     */
    public String getUsername() {
        return username;
    }

    /**
     * gets the password of the employee
     * @return the password of the employee
     */
    public String getPassword() {
        return password;
    }

    

    /**
     * gets the ID of the employee
     * @return the ID of the employee
     */
    private String getEmployeeID() {
        return employeeID;
    }
    @Override
    public String getJsonId() {
        return "Employee_" + this.getEmployeeID();
    }

}

