/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventorysystem.ItemandFactory;

import inventorysystem.Connection.SingletonConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author savin
 */

// implementation of the ItemFactory interface
public class ItemFactoryImpl implements ItemFactory {
    //The connection conn is used to get the database connection from the singleton connection class
    private static Connection conn = SingletonConnection.getInstance().getCon();
    
    //This method creates an instance of the Item class using the builder pattern and returns it
    @Override
    public Item createItem(int id, String name, int quantity, String empName, float price, LocalDateTime dateCreated) {
        //getting parameters and passes them to the builder object to set the corresponding fields in the Item object
        return new Item.Builder()
                .ID(id)
                .Name(name)
                .Quantity(quantity)
                .EmpName(empName)
                .Price(price)
                .DateCreated(dateCreated)
                .build();
    }
    
    //This method takes in an instance of the Item class as a parameter and uses 
    //it to insert a new record into the item table in the database
    @Override
    public void addItemToDB(Item item) {
        try {
            String sql = "INSERT INTO item (ID, Name, Quantity, EmpName, Price, DateCreated) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, item.getID());
            stmt.setString(2, item.getName());
            stmt.setInt(3, item.getQuantity());
            stmt.setString(4, item.getEmpName());
            stmt.setFloat(5, item.getPrice());
            stmt.setTimestamp(6, Timestamp.valueOf(item.getDateCreated()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting item into database: " + e.getMessage());
        }
    }

}
