package runtime;

import business.Cabinet;
import util.*;


public class Program {

    //static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Menu menu = new Menu();
        Cabinet cabinet = new Cabinet();
        //read file
        cabinet.readFile();
       
        while (true) {
            int choice = menu.showMenu();
            switch (choice) {
                case 1: {
                    cabinet.addNewCustomer();
                    break;
                }
                case 2: {//Update customer information  
                    cabinet.updateCustomerInformation();
                    break;
                }
                case 3: {//Search customer by name
                    cabinet.searchCustomerByName();
                    break;
                    }
                case 4: {//Display feast menu 
                    cabinet.displayMenu();
                    break;
                }
                case 5: {// Place a feast order 
                   cabinet.makeNewOrrder();
                    break;
                }
                case 6: {//Update order information
                   cabinet.updateOrderInformation();
                    break;
                }
                case 7: {//Save data to file
                    cabinet.saveToFile();
                    break;
                }
                case 8: {//Display Customer or Order lists    
                    cabinet.displayByChoice();
                    break;
                }
                case 0: {
                    cabinet.exit();
                    return;
                }

            }
        }
    }

}
