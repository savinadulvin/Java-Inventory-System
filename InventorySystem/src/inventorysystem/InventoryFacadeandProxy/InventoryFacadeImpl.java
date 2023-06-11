/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventorysystem.InventoryFacadeandProxy;

import CommandHandling.AddItemCommandHandler;
import CommandHandling.AddStockCommandHandler;
import CommandHandling.CommandHandler;
import CommandHandling.FinancialReportCommandHandler;
import CommandHandling.InventoryReportCommandHandler;
import CommandHandling.RemoveStockCommandHandler;
import CommandHandling.ReportPersonalUsageCommandHandler;
import CommandHandling.TransactionLogCommandHandler;

/**
 *
 * @author savin
 */

// Implementation class for the InventoryFacade interface
// Contains methods for executing different commands related to inventory management
public class InventoryFacadeImpl implements InventoryFacade {
    
    // private fields for different command classes
    private CommandHandler addItemHandler = new AddItemCommandHandler();
    private CommandHandler addStockHandler = new AddStockCommandHandler();
    private CommandHandler removeStockHandler = new RemoveStockCommandHandler();
    private CommandHandler inventoryReportHandler = new InventoryReportCommandHandler();
    private CommandHandler financialReportHandler = new FinancialReportCommandHandler();
    private CommandHandler transactionLogHandler = new TransactionLogCommandHandler();
    private CommandHandler personalUsageReportHandler = new ReportPersonalUsageCommandHandler();

    // constructor initializing all command classes
    public InventoryFacadeImpl() {
        addItemHandler.setNext(addStockHandler);
        addStockHandler.setNext(removeStockHandler);
        removeStockHandler.setNext(inventoryReportHandler);
        inventoryReportHandler.setNext(financialReportHandler);
        financialReportHandler.setNext(transactionLogHandler);
        transactionLogHandler.setNext(personalUsageReportHandler);
    }

    // methods for executing corresponding command classes
    public void addItem() {
        addItemHandler.handleCommand(1);
    }

    public void addStock() {
        addStockHandler.handleCommand(2);
    }

    public void removeStock() {
        removeStockHandler.handleCommand(3);
    }

    public void displayInventoryReport() {
        inventoryReportHandler.handleCommand(4);
    }

    public void displayFinancialReport() {
        financialReportHandler.handleCommand(5);
    }

    
    public void displayTransactionLog() {
        transactionLogHandler.handleCommand(6);
    }

    
    public void displayPersonalUsageReport() {
        personalUsageReportHandler.handleCommand(7);
    }
}
