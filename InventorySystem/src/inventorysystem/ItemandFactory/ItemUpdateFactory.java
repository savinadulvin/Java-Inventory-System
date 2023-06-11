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
public interface ItemUpdateFactory {
    
    // creates an item object with the updated quantity and price
    public Item updateQuantityandPriceItem(int id, int updatedQuantity, float updatedPrice, String employeeName, LocalDateTime dateUpdated);   

    // updates the quantity and price of an existing item in the database
    public void updateItem(int id, int updatedQuantity, float updatedPrice, String employeeName);
}
