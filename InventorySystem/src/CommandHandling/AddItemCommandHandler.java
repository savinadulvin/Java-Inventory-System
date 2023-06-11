/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CommandHandling;

import inventorysystem.AddItemCommand;

/**
 *
 * @author savin
 */
public class AddItemCommandHandler extends CommandHandler{
    private CommandHandler next;

    //set the next handler in the chain
    public void setNext(CommandHandler next) {
        this.next = next;
    }
    
    //override the handleCommand method
    public void handleCommand(int option) {
        //check if the option passed is 1, which corresponds to the add item functionality
        if (option == 1) {
            //create an object of AddItemCommand class
            AddItemCommand command = new AddItemCommand();
            //creating a new object of AddItemCommand by calling the clone()
            //method on the command object
            command = (AddItemCommand) command.clone();
            //AddItemCommand command = (AddItemCommand) command.clone();
            command.execute();
        } else {           
            //if the option passed is not 1, call the handleCommand method 
            //of the next handler in the chain
            if (next != null) {
                next.handleCommand(option);
            }
        }
    }
    
}
