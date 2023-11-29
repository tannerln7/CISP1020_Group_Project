package Retail_Operations;

import Helpers.Cls;
import Helpers.ObjectJson;

import java.io.File;
import java.util.Scanner;
import static Main.Main.execute;
import static Main.Main.main;

/**
 * The EmployeeManagement class manages the employees in the retail system.
 */
public abstract class EmployeeManagement{
    /**
     * The main method for the EmployeeManagement class.
     *
     * @param employee the employee that is currently logged in
     */
    public static void employeeManagement(Employee employee){
        Cls.cls();
        Scanner scanner = new Scanner(System.in);
        if (!(employee.getPosition().equals("Manager"))){
            System.out.println("You do not have access to this menu");
            System.out.println("Press Enter to return to the Main Menu");
            scanner.nextLine();
            execute(() -> main(null));
            return;
        }
        System.out.println("Welcome to The Employee Management Menu");
        System.out.println("1. Add Employee");
        System.out.println("2. Remove Employee");
        System.out.println("3. Edit Employee");
        System.out.println("4. View Employee");
        System.out.println("5. Exit");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice){
            case 1:
                Cls.cls();
                System.out.println("Enter Employee Name");
                String nameID = scanner.nextLine();
                if(getEmployee(nameID) != null){
                    System.out.println("Employee already exists");
                    System.out.println("Press Enter to Continue");
                    scanner.nextLine();
                    execute(() -> employeeManagement(employee));
                    break;
                }
                System.out.println("Enter Employee ID");
                String id = scanner.nextLine();
                System.out.println("Enter Employee Position");
                String position = scanner.nextLine();
                System.out.println("Enter Employee Username");
                String username = scanner.nextLine();
                System.out.println("Enter Employee Password");
                String password = scanner.nextLine();
                Employee newEmployee = new Employee(nameID, id, position, username, password);
                ObjectJson.objectToJson(newEmployee);
                Cls.cls();
                System.out.println("Employee Added");
                System.out.println(newEmployee);
                System.out.println("Press Enter to Continue");
                scanner.nextLine();
                execute(() -> employeeManagement(employee));
                break;
            case 2:
                Cls.cls();
                System.out.println("Enter Employee ID or Name");
                String removeId = scanner.nextLine();
                if(getEmployee(removeId) == null){
                    Cls.cls();
                    System.out.println("Employee does not exist");
                    System.out.println("Press Enter to Continue");
                    scanner.nextLine();
                    execute(() -> employeeManagement(employee));
                    break;
                }
                Employee removeEmployee = getEmployee(removeId);
                if(removeEmployee == null){
                    Cls.cls();
                    System.out.println("Employee does not exist");
                    System.out.println("Press Enter to Continue");
                    scanner.nextLine();
                    execute(() -> employeeManagement(employee));
                    break;
                }
                System.out.println("Are you sure you want to remove this employee?");
                System.out.println(removeEmployee);
                System.out.println("1. Yes");
                System.out.println("2. No");
                int removeChoice = scanner.nextInt();
                scanner.nextLine();
                if (removeChoice == 2){
                    Cls.cls();
                    System.out.println("Employee not removed");
                    System.out.println("Press Enter to Continue");
                    scanner.nextLine();
                    execute(() -> employeeManagement(employee));
                    break;
                }
                ObjectJson.deleteObject(removeEmployee.getJsonId(), Employee.class);
                Cls.cls();
                System.out.println("Employee Removed");
                System.out.println("Press Enter to Continue");
                scanner.nextLine();
                execute(() -> employeeManagement(employee));
                break;
            case 3:
                Cls.cls();
                System.out.println("Enter Employee ID or Name");
                String editId = scanner.nextLine();
                if (getEmployee(editId) == null){
                    Cls.cls();
                    System.out.println("Employee does not exist");
                    System.out.println("Press Enter to Continue");
                    scanner.nextLine();
                    execute(() -> employeeManagement(employee));
                    break;
                }
                Employee employee1 = getEmployee(editId);
                if (employee1 == null){
                    Cls.cls();
                    System.out.println("Employee does not exist");
                    System.out.println("Press Enter to return to the Employee Management Menu");
                    scanner.nextLine();
                    execute(() -> employeeManagement(employee));
                    break;
                }
                System.out.println("What would you like to edit?");
                System.out.println("1. Name");
                System.out.println("2. ID");
                System.out.println("3. Position");
                System.out.println("4. Username");
                System.out.println("5. Password");
                int editChoice = scanner.nextInt();
                scanner.nextLine();
                switch (editChoice){
                    case 1:
                        Cls.cls();
                        System.out.println("Enter the Employees new name");
                        String newName = scanner.nextLine();
                        employee1.setName(newName);
                        ObjectJson.objectToJson(employee1);
                        Cls.cls();
                        System.out.println("Employee Edited");
                        System.out.println(employee1);
                        System.out.println("Press Enter to return to the Employee Management Menu");
                        scanner.nextLine();
                        execute(() -> employeeManagement(employee));
                        break;
                    case 2:
                        Cls.cls();
                        System.out.println("Enter the Employees new ID or Name");
                        String newID = scanner.nextLine();
                        employee1.setEmployeeID(newID);
                        ObjectJson.objectToJson(employee1);
                        Cls.cls();
                        System.out.println("Employee Edited");
                        System.out.println(employee1);
                        System.out.println("Press Enter to return to the Employee Management Menu");
                        scanner.nextLine();
                        execute(() -> employeeManagement(employee));
                        break;
                    case 3:
                        Cls.cls();
                        System.out.println("Enter new Position");
                        String newPosition = scanner.nextLine();
                        employee1.setPosition(newPosition);
                        ObjectJson.objectToJson(employee1);
                        Cls.cls();
                        System.out.println("Employee Edited");
                        System.out.println(employee1);
                        System.out.println("Press Enter to return to the Employee Management Menu");
                        scanner.nextLine();
                        execute(() -> employeeManagement(employee));
                        break;
                    case 4:
                        Cls.cls();
                        System.out.println("Enter new Username");
                        String newUsername = scanner.nextLine();
                        employee1.setUsername(newUsername);
                        ObjectJson.objectToJson(employee1);
                        Cls.cls();
                        System.out.println("Employee Edited");
                        System.out.println(employee1);
                        System.out.println("Press Enter to return to the Employee Management Menu");
                        scanner.nextLine();
                        execute(() -> employeeManagement(employee));
                        break;
                    case 5:
                        Cls.cls();
                        System.out.println("Enter new Password");
                        String newPassword = scanner.nextLine();
                        employee1.setPassword(newPassword);
                        ObjectJson.objectToJson(employee1);
                        Cls.cls();
                        System.out.println("Employee Edited");
                        System.out.println(employee1);
                        System.out.println("Press Enter to return to the Employee Management Menu");
                        scanner.nextLine();
                        execute(() -> employeeManagement(employee));
                        break;
                    default:
                        Cls.cls();
                        System.out.println("Invalid Input");
                        System.out.println("Press Enter to return to the Employee Management Menu");
                        scanner.nextLine();
                        execute(() -> employeeManagement(employee));
                        break;
                }
                break;
            case 4:
                Cls.cls();
                System.out.println("Enter Employee ID or Name");
                String viewId = scanner.nextLine();
                Employee viewEmployee = getEmployee(viewId);
                if (viewEmployee == null){
                    Cls.cls();
                    System.out.println("Employee does not exist");
                    System.out.println("Press Enter to return to the Employee Management Menu");
                    scanner.nextLine();
                    execute(() -> employeeManagement(employee));
                    break;
                }
                Cls.cls();
                System.out.println("Employee Found");
                System.out.println();
                System.out.println(viewEmployee);
                System.out.println("Press Enter to return to the Employee Management Menu");
                scanner.nextLine();
                execute(() -> employeeManagement(employee));
                break;
            case 5:
                execute(() -> main(null));
                break;
            default:
                System.out.println("Invalid Input");
                System.out.println("Press Enter to return to the Employee Management Menu");
                scanner.nextLine();
                execute(() -> employeeManagement(employee));
                break;
        }
    }

    /**
     * Retrieves the employee with the specified ID or name.
     *
     * @param searchTerm The ID or name of the employee to retrieve.
     * @return The employee with the specified ID or name.
     */
    public static Employee getEmployee(String searchTerm){
        File[] files = ObjectJson.listFiles(Employee.class);
        if (files != null) {
            for (File file : files) {
                try {
                    Employee employee = ObjectJson.objectFromJson(file.getName(), Employee.class);
                    if (employee != null) {
                        if (employee.getEmployeeID().equalsIgnoreCase(searchTerm) || employee.getName().equalsIgnoreCase(searchTerm)) {
                            return employee;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Employee not found");
                    return null;
                }
            }
        }
        return null;
    }
}
