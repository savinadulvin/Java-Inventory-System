/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventorysystem.InventoryFacadeandProxy;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author savin
 */

//This class is the proxy class for the InventoryFacade class which implements the InventoryFacade interface
public class InventoryProxy implements InventoryFacade {

    //Creating an object of InventoryFacadeImpl class
    private InventoryFacade inventoryFacade;
    
    
    //Creating a logger object to log events in the program
    private static Logger logger = Logger.getLogger(InventoryProxy.class.getName());

    //Creating a constructor that initializes the inventoryFacade object
    public InventoryProxy() {
        inventoryFacade = new InventoryFacadeImpl();
    }

    //Overriding the addItem method of the InventoryFacade interface to log the method call
    public void addItem() {
        logger.log(Level.INFO, "addItem method called");
        inventoryFacade.addItem();
    }

    //Overriding the removeStock method of the InventoryFacade interface to log the method call
    public void addStock() {
        logger.log(Level.INFO, "addStock method called");
        inventoryFacade.addStock();
    }

    public void removeStock() {
        logger.log(Level.INFO, "removeStock method called");
        inventoryFacade.removeStock();
    }

    public void displayInventoryReport() {
        logger.log(Level.INFO, "displayInventoryReport method called");
        inventoryFacade.displayInventoryReport();
    }

    public void displayFinancialReport() {
        logger.log(Level.INFO, "displayFinancialReport method called");
        inventoryFacade.displayFinancialReport();
    }

    public void displayTransactionLog() {
        logger.log(Level.INFO, "displayTransactionLog method called");
        inventoryFacade.displayTransactionLog();
    }

    public void displayPersonalUsageReport() {
        logger.log(Level.INFO, "displayPersonalUsageReport method called");
        inventoryFacade.displayPersonalUsageReport();
    }
}
