/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventorysystem.InventoryFacadeandProxy;

/**
 *
 * @author savin
 */
public interface InventoryFacade {
    
    public void addItem();
    public void addStock();
    public void removeStock();
    public void displayInventoryReport();
    public void displayFinancialReport();
    public void displayTransactionLog();
    public void displayPersonalUsageReport();
    
}
// this interface defines the methods that the facade class will 
//implement to provide a simplified interface for the user to access the inventory management system

