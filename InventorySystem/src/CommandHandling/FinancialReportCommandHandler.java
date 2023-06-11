/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CommandHandling;

import inventorysystem.FinancialReportCommand;

/**
 *
 * @author savin
 */
public class FinancialReportCommandHandler extends CommandHandler{
    
    private CommandHandler next;
    
    //set the next handler in the chain
    public void setNext(CommandHandler next) {
        this.next = next;
    }

    //override the handleCommand method
    public void handleCommand(int option) {
        //check if the option passed is 5, which corresponds to the add item functionality
        if (option == 5) {
            //create an object of FinancialReportCommand class
            FinancialReportCommand command = new FinancialReportCommand();
            //creating a new object of FinancialReportCommand by calling the clone()
            //method on the command object
            command = (FinancialReportCommand) command.clone();
            command.execute();
        } else {
            //if the option passed is not 5, call the handleCommand 
            //method of the next handler in the chain
            if (next != null) {
                next.handleCommand(option);
            }
        }
    }
}
