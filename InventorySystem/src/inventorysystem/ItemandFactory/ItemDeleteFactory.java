/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventorysystem.ItemandFactory;

import java.time.LocalDateTime;

/**
 *
 * @author savin
 */
public interface ItemDeleteFactory {
    
    // creates a new item object with the given id, quantity, empName, and dateDeleted
    public Item deleteItem(int id, int quantity, String empName, LocalDateTime dateDeleted);   

    // deletes an item from the database with the given id, itemQuantity, and empname
    public void deleteItemDB(int id, int itemQuantity, String empname);

    
}
