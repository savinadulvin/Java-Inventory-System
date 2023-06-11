/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventorysystem;

/**
 *
 * @author savin
 */
public interface Command {
    //method
    void execute();
    
}
// This allows for easy management and execution of different commands, as they can all be treated as a single type.