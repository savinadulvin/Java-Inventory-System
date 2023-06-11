/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package inventorysystem;

/**
 *
 * @author savin
 */
import inventorysystem.InventoryFacadeandProxy.InventoryProxy;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ManageTransaction 
{

     public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
     
     public static void main(String[] args) throws Exception
     {        
          // Create an instance of InventoryProxy class
         InventoryProxy facade = new InventoryProxy();
    
         // Display the menu options
         DisplayMenu();
         int option = Choice();
         
         // Loop through the options until the user chooses option 8 to exit
         while (option != 8)
         {
             switch (option)
             {
                 case 1:         
                     facade.addItem();      // adds a new item to the inventory
                     break;
                 case 2:
                     facade.addStock();     // adds stock to an existing item in the inventory
                     break;
                 case 3:
                     facade.removeStock();  // removes stock from an existing item in the inventory
                     break;
                 case 4:
                     facade.displayInventoryReport();    // generates an inventory report
                     break;
                 case 5:
                     facade.displayFinancialReport();   // generates a financial report
                     break;
                 case 6:
                     facade.displayTransactionLog();     // displays the transaction log
                     break;
                 case 7:
                     facade.displayPersonalUsageReport();   // generates a report on personal usage of items
                     break;
                 case 8:
                     //Exit();
                     break;
             }
             DisplayMenu();    // displays the menu again
             option = Choice();
         }
     }

     private static void DisplayMenu()
     {
         System.out.println("\n1. Add new item");
         System.out.println("2. Add to stock");
         System.out.println("3. Take from stock");
         System.out.println("4. Inventory Report");
         System.out.println("5. Financial Report");
         System.out.println("6. Display Transaction Log");
         System.out.println("7. Report Personal Usage");
         System.out.println("8. Exit");
     }

     private static int Choice()
     {
         int option = ReadInteger("\nOption");
         while (option < 1 || option > 8)
         {
             System.out.println("\nChoice not recognised, Please enter again");
             option = ReadInteger("\nOption");
         }
         return option;
     }

     private static int ReadInteger(String prompt)
     {
         try
         {
        	 System.out.println(prompt + ": > ");
             return Integer.parseInt(reader.readLine().toString());
         }
         catch (Exception e)
         {
             return -1;
         }
     }    
}