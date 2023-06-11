package inventorysystem.EmployeeandTransactionLog;

import inventorysystem.Connection.SingletonConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Employee 
{
    private static Connection conn = SingletonConnection.getInstance().getCon();
	public String EmpName;
	
        // getter method
	public String getEmpName()
	{
		return EmpName;
	}
	
        // constructor to set the employee name
	public Employee(String empname)
	{
		this.EmpName = empname;
	}
        
        
//    public boolean employeeExistInDB(String employeeName) {
//        boolean employeeExists = false;
//        try {
//            // SQL query to select the employee with the given name from the employee table
//            String sql = "SELECT * FROM employee WHERE EmpName = ?";
//            PreparedStatement stmt = conn.prepareStatement(sql);
//            stmt.setString(1, employeeName);
//            ResultSet result = stmt.executeQuery();
//
//            // if the employee name is found in the result set, set employeeExists to true
//            if (result.next()) {
//                employeeExists = true;
//            }
//        } catch (SQLException e) {
//            System.out.println("Error checking employee name in database: " + e.getMessage());
//        }
//        return employeeExists;
//    }
    
    // method to add the employee to the database
    public void addEmployeeToDB() {
        try {
            String sql = "INSERT INTO employee (EmpName) VALUES (?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, EmpName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting employee name into employee database: " + e.getMessage());
        }
    }
}
