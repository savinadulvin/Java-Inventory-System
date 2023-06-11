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
public class ItemUpdateFactoryImpl implements ItemUpdateFactory{
    private static Connection conn = SingletonConnection.getInstance().getCon();
    
    // override the createItem method from the ItemUpdateFactory interface
    @Override
    public Item updateQuantityandPriceItem(int id, int updatedQuantity, float updatedPrice, 
            String employeeName, LocalDateTime dateUpdated) {
        // create a new Item object using the builder pattern and passing in the updated values
        return new Item.Builder()
                .ID(id)
                .Quantity(updatedQuantity)
                .Price(updatedPrice)
                .DateUpdated(dateUpdated)
                .UpdatedBy(employeeName)
                .build();
    }    
    
    // override the updateItem method from the ItemUpdateFactory interface
    @Override
    public void updateItem(int id, int updatedQuantity, float updatedPrice, String employeeName) {
        try {
            // create an SQL query to update the item in the database
            String sql = "UPDATE item SET quantity = ?, price = ?, dateupdated = ?, UpdatedBy = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, updatedQuantity);
            stmt.setFloat(2, updatedPrice);
            stmt.setString(3, LocalDateTime.now().toString());
            stmt.setString(4, employeeName);
            stmt.setInt(5, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating record in database: " + e.getMessage());
        }
    }
    
}
