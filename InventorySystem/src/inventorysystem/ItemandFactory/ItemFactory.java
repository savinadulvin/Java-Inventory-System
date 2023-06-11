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
public interface ItemFactory {
    
    //Method to create an instance of the Item class
    public Item createItem(int id, String name, int quantity, String empName, float price, LocalDateTime dateCreated);   
    
    //Method to add the item to the database
    void addItemToDB(Item item);


    
}
