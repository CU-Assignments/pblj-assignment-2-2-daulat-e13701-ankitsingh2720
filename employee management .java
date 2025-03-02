
import java.io.*;
import java.util.*;

class Employee implements Serializable {
    private String name;
    private int id;
    private String designation;
    private double salary;

    public Employee(String name, int id, String designation, double salary) {
        this.name = name;
        this.id = id;
        this.designation = designation;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee [ID=" + id + ", Name=" + name + ", Designation=" + designation + ", Salary=" + salary + "]";
    }
}

public class EmployeeManagementApp {
    private static final String FILE_PATH = "employees.dat";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Employee> employees = new ArrayList<>();
        
        // Load employees from file (if exists)
        loadEmployees(employees);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add an Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    // Add an employee
                    System.out.print("Enter Employee Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Employee ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Designation: ");
                    String designation = sc.nextLine();
                    System.out.print("Enter Salary: ");
                    double salary = sc.nextDouble();
                    employees.add(new Employee(name, id, designation, salary));
                    saveEmployees(employees);  // Save employee list to file
                    break;

                case 2:
                    // Display all employees
                    System.out.println("\nEmployee Details:");
                    if (employees.isEmpty()) {
                        System.out.println("No employees found.");
                    } else {
                        for (Employee employee : employees) {
                            System.out.println(employee);
                        }
                    }
                    break;

                case 3:
                    // Exit the application
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Load employees from file
    private static void loadEmployees(List<Employee> employees) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            employees.addAll((List<Employee>) in.readObject());
        } catch (FileNotFoundException e) {
            System.out.println("No existing employee data found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading employee data: " + e.getMessage());
        }
    }

    // Save employees to file
    private static void saveEmployees(List<Employee> employees) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(employees);
        } catch (IOException e) {
            System.out.println("Error saving employee data: " + e.getMessage());
        }
    }
}
