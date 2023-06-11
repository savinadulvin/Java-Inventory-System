/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CommandHandling;

import inventorysystem.InventoryReportCommand;

/**
 *
 * @author savin
 */
public class InventoryReportCommandHandler extends CommandHandler{
    
    private CommandHandler next;

    //set the next handler in the chain
    public void setNext(CommandHandler next) {
        this.next = next;
    }

    //override the handleCommand method
    public void handleCommand(int option) {
        //check if the option passed is 4, which corresponds to the add item functionality
        if (option == 4) {
            //create an object of InventoryReportCommand class
            InventoryReportCommand command = new InventoryReportCommand();
            //creating a new object of InventoryReportCommand by calling the clone()
            //method on the command object
            command = (InventoryReportCommand) command.clone();
            command.execute();
        } else {
            //if the option passed is not 4, call the handleCommand 
            //method of the next handler in the chain
            if (next != null) {
                next.handleCommand(option);
            }
        }
    }
    
}


