/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CommandHandling;

import inventorysystem.DisplayTransactionLogCommand;

/**
 *
 * @author savin
 */
public class TransactionLogCommandHandler extends CommandHandler{
    
    private CommandHandler next;
    
    //set the next handler in the chain
    public void setNext(CommandHandler next) {
        this.next = next;
    }
    
    //override the handleCommand method
    public void handleCommand(int option) {
        //check if the option passed is 6, which corresponds to the add item functionality
        if (option == 6) {
            //create an object of DisplayTransactionLogCommand class 
            DisplayTransactionLogCommand command = new DisplayTransactionLogCommand();
            //creating a new object of DisplayTransactionLogCommand by calling the clone()
            //method on the command object
            command = (DisplayTransactionLogCommand) command.clone();
            command.execute();
        } else {
            //if the option passed is not 6, call the handleCommand 
            //method of the next handler in the chain
            if (next != null) {
                next.handleCommand(option);
            }
        }
    }
}
