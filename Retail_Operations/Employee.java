package Retail_Operations;

import Helpers.JsonIdentifiable;


/**
 * The Employee class represents an employee in a retail system.
 * It implements the JsonIdentifiable interface.
 */
public class Employee implements JsonIdentifiable {
    private String name;
    private String employeeID;
    private String position;
    private String username;
    private String password;


    /**
     * Constructs a new Employee with the specified name, ID, position, username, and password.
     *
     * @param name       the name of the employee
     * @param employeeID the ID of the employee
     * @param position   the position of the employee
     * @param username   the username of the employee
     * @param password   the password of the employee
     */

    public Employee(String name, String employeeID, String position, String username, String password) {
        this.name = name;
        this.employeeID = employeeID;
        this.position = position;
        this.username = username;
        this.password = password;
    }

    /**
     * Retrieves the name of the employee.
     *
     * @return the name of the employee
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the employee.
     *
     * @param name the name of the employee
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the position of the employee.
     *
     * @return the position of the employee
     */
    public String getPosition() {
        return position;
    }

    /**
     * Sets the position of the employee.
     *
     * @param position the position of the employee
     */
    public void setPosition(String position) {
        this.position = position;
    }
    /**
     * Retrieves the username of the employee.
     *
     * @return the username of the employee
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the employee.
     *
     * @param username the username of the employee
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * Retrieves the password of the employee.
     *
     * @return the password of the employee
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the employee.
     *
     * @param password the password of the employee
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retrieves the ID of the employee.
     *
     * @return the ID of the employee
     */
    public String getEmployeeID() {
        return employeeID;
    }
    /**
     * Sets the ID of the employee.
     *
     * @param employeeID the ID of the employee
     */
    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    /**
     * Returns a JSON-compatible ID for this employee.
     *
     * @return the JSON-compatible ID
     */
    @Override
    public String getJsonId() {
        return "Employee_" + this.getEmployeeID();
    }

    /**
     * Returns a string representation of this employee.
     *
     * @return a string representation of this employee
     */
    @Override
    public String toString() {
        return "Employee:\n" +
                "name: " + name + "\n" +
                "employeeID: " + employeeID + "\n" +
                "position: " + position + "\n" +
                "username: " + username + "\n";
    }

}

