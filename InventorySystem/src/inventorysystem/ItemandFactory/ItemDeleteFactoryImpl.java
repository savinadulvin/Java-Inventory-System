/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventorysystem.ItemandFactory;

import inventorysystem.Connection.SingletonConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 *
 * @author savin
 */
public class ItemDeleteFactoryImpl implements ItemDeleteFactory {
    private static Connection conn = SingletonConnection.getInstance().getCon();
    
    // creates an Item object with the specified ID, quantity, employee name, and date deleted
    @Override
    public Item deleteItem(int id, int quantity, String empName, LocalDateTime dateDeleted) {
        return new Item.Builder()
                .ID(id)
                .Quantity(quantity)
                .EmpName(empName)
                .DateUpdated(dateDeleted)
                .build();
    }
    
    // deletes the specified quantity of an item from the database with the specified ID
    @Override
    public void deleteItemDB(int id, int itemQuantity, String empname) {
        try {
            // create and execute a prepared statement to update the quantity of the item in the database
            PreparedStatement stmt = conn.prepareStatement("UPDATE item SET quantity = quantity - ? WHERE id = ?");
            stmt.setInt(1, itemQuantity);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            System.out.println(empname + " has removed " + itemQuantity + " of Item ID: " + id + " on " + LocalDateTime.now());
        } catch (SQLException e) {
            System.out.println("Error deleting item from database: " + e.getMessage());
        }
    }
}
