/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventorysystem;

import Prototype.Prototype;
import inventorysystem.Connection.SingletonConnection;
import inventorysystem.ItemandFactory.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author savin
 */
public class FinancialReportCommand implements Command, Prototype{
    
    private static Connection conn = SingletonConnection.getInstance().getCon();
    
    public void execute(){
        
        // Financial report
//         Item.TotalPrice();

        float total = 0;
//        Connection conn = SingletonConnection.getCon();

        try {
            // SQL query to select all items from the item table
            String sql = "SELECT * FROM item";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();

            System.out.println("Financial Report:");

            // iterates through each item in the result set
            while (result.next()) {
                // Retrieve the name, price, and quantity of the item
                String itemName = result.getString("name");
                float itemPrice = result.getFloat("price");
                int itemQuantity = result.getInt("quantity");

                // Calculate the cost of the item by multiplying the price and quantity
                float cost = itemPrice * itemQuantity;
                System.out.println(itemName + "\t" + cost);
                total += cost;
            }

            System.out.println("Total price of all items:" + "\t" + total);
        } catch (SQLException e) {
            System.out.println("Error retrieving data from transactionlog table: " + e.getMessage());
        }
    }
    
    // Method to clone the FinancialReportCommand object
    public Prototype clone() {
        return new FinancialReportCommand();
    }
}
