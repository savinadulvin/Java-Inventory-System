/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CommandHandling;

import inventorysystem.AddItemCommand;
import inventorysystem.AddStockCommand;
import inventorysystem.Command;
import inventorysystem.DisplayTransactionLogCommand;
import inventorysystem.FinancialReportCommand;
import inventorysystem.InventoryReportCommand;
import inventorysystem.RemoveStockCommand;
import inventorysystem.ReportPersonalUsageCommand;

/**
 *
 * @author savin
 */
public class CommandHandler {
    
    // The next CommandHandler in the chain
    private CommandHandler next;
    // The command to be executed
    private Command command;

    // Set the command to be executed
    public void setCommand(Command command) {
    this.command = command;
    }

    // Set the next CommandHandler in the chain
    public void setNext(CommandHandler next) {
        this.next = next;
    }

    // Handle the command based on the option provided
    public void handleCommand(int option) {
        // Check if the current command is an instance of AddItemCommand and the option is 1
        if(command instanceof AddItemCommand && option == 1){
            // If both conditions are true, execute the command
            command.execute();
        }else if(command instanceof AddStockCommand && option == 2){
            command.execute();
        }else if(command instanceof RemoveStockCommand && option == 3){
            command.execute();
        }else if(command instanceof InventoryReportCommand && option == 4){
            command.execute();
        }else if(command instanceof FinancialReportCommand && option == 5){
            command.execute();
        }else if(command instanceof DisplayTransactionLogCommand && option == 6){
            command.execute();
        }else if(command instanceof ReportPersonalUsageCommand && option == 7){
            command.execute();
        // If none of the above conditions are met and there is a next CommandHandler in the chain
        }else if(next != null){
            // Call the handleCommand method on the next CommandHandler
            next.handleCommand(option);
        }
    }
    
}
