/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CommandHandling;

import inventorysystem.ReportPersonalUsageCommand;

/**
 *
 * @author savin
 */
public class ReportPersonalUsageCommandHandler extends CommandHandler{
    
    private CommandHandler next;
    
    //set the next handler in the chain
    public void setNext(CommandHandler next) {
        this.next = next;
    }
    
    //override the handleCommand method
    public void handleCommand(int option) {
        //check if the option passed is 7, which corresponds to the add item functionality
        if (option == 7) {
            //create an object of ReportPersonalUsageCommand class 
            ReportPersonalUsageCommand command = new ReportPersonalUsageCommand();
            //creating a new object of ReportPersonalUsageCommand by calling the clone()
            //method on the command object
            command = (ReportPersonalUsageCommand) command.clone();
            command.execute();
        } else {
            //if the option passed is not 7, call the handleCommand method of the next handler in the chain
            if (next != null) {
                next.handleCommand(option);
            }
        }
    }
}
