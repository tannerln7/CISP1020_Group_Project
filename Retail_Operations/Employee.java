package Retail_Operations;

import Helpers.JsonIdentifiable;
//TODO: Start working on the Management menu for Employees.
//BODy: This will be a menu that allows staff members to add, remove, and edit employee information.
public record Employee(String name, String employeeID, String position, String username, String password) implements JsonIdentifiable {

    private String getEmployeeID() {
        return employeeID;
    }
    @Override
    public String getJsonId() {
        return "Employee_" + this.getEmployeeID();
    }

}
