package Retail_Operations;

import Helpers.JsonIdentifiable;


/**
 * The Employee class represents an employee in a retail system.
 * It implements the JsonIdentifiable interface.
 */
public class Employee implements JsonIdentifiable {
    private final String name;
    private final String employeeID;
    private final String position;
    private final String username;
    private final String password;


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
     * Retrieves the position of the employee.
     *
     * @return the position of the employee
     */
    public String getPosition() {
        return position;
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
     * Retrieves the password of the employee.
     *
     * @return the password of the employee
     */
    public String getPassword() {
        return password;
    }

    /**
     * Retrieves the ID of the employee.
     *
     * @return the ID of the employee
     */
    private String getEmployeeID() {
        return employeeID;
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

}

