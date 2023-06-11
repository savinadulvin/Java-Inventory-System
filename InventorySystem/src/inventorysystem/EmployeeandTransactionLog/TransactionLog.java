package inventorysystem.EmployeeandTransactionLog;

import inventorysystem.Connection.SingletonConnection;
import inventorysystem.ItemandFactory.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TransactionLog 
{
    private static Connection conn = SingletonConnection.getInstance().getCon();                

    public String TypeOfTransaction;
    public int ItemID;
    public String ItemName;
    public float ItemPrice;
    public int ItemQuantity;
    public String EmployeeName;
    public LocalDateTime DateAdded;
    
    //getter methods
    public String getTypeOfTransaction()
    {
    	return TypeOfTransaction;
    }

    public int getItemID()
    {
    	return ItemID;
    }
    
    public String getItemName()
    {
    	return ItemName;
    }
    
    public float getItemPrice()
    {
    	return ItemPrice;
    }
    
    public int getItemQuantity()
    {
    	return ItemQuantity;
    }
    
    public String getEmployeeName()
    {
    	return EmployeeName;
    }
    
    public LocalDateTime getDateAdded()
    {
    	return DateAdded;
    }
    
    // The constructor for the class
    public TransactionLog(String type, Item i, float itemPrice, LocalDateTime dateAdded, String employeeName, String itemName) {
        this.TypeOfTransaction = type;
        this.ItemID = i.getID();
        this.ItemQuantity = i.getQuantity();
        this.ItemPrice = itemPrice;
        this.EmployeeName = employeeName;
        this.DateAdded = dateAdded;
        this.ItemName = itemName;
    }

    public static void createLogEntry(String type, Item i, float itemPrice, LocalDateTime dateAdded,String EmployeeName, String itemName)
    {

        // Insert the log entry into the transactionlog table in the database
        try {
            String sql = "INSERT INTO transactionlog (Type, id, name, quantity, price, EmployeeName, datecreated) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, type);
            stmt.setInt(2, i.getID());
            stmt.setString(3, itemName);
            stmt.setInt(4, i.getQuantity());
            stmt.setFloat(5, itemPrice);
            stmt.setString(6, EmployeeName);
            stmt.setTimestamp(7, Timestamp.valueOf(dateAdded));
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting record into transactionlog table: " + e.getMessage());
            
        }
    }
}
