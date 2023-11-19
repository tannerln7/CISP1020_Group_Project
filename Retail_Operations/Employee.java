package Retail_Operations;

import Helpers.JsonIdentifiable;

public record Employee(String name, String employeeID, String position, String username, String password) implements JsonIdentifiable {

    private String getEmployeeID() {
        return employeeID;
    }
    @Override
    public String getJsonId() {
        return "Employee_" + this.getEmployeeID();
    }

}
