
package util;

import util.*;

/**
 *
 * @author TranHongGam
 */
public class Menu {
    public static int showMenu() {
        System.out.println("------------------------Menu-------------------------");
        System.out.println("1. Register customer                               |");// add a laptop
        System.out.println("2. Update customer information                     |");// update the laptop
        System.out.println("3. Search customer by name                         |");// search by name
        System.out.println("4. Display feast menu                              |");// display all
        System.out.println("5. Place a feast order                             |");// change 
        System.out.println("6. Update order information                        |");// update 
        System.out.println("7. Save data to file                               |");// save to binary file
        System.out.println("8. Display Customer or Order lists                 |");// display 
        System.out.println("0. Exit                                            |");// exit
        System.out.println("-----------------------------------------------------");
        

        return Inputter.inputInt("Please choose an option: ","Valid option", 0, 8);
    }
    public static int showMenuInFun8 (){
        System.out.println("------------------------Menu-------------------------");
        System.out.println("|1. Diplay Customers List                           |");
        System.out.println("|2. Display Orders List                             |");
        System.out.println("|0. Exit                                            |");// exit
        System.out.println("-----------------------------------------------------");
        return Inputter.inputInt("Please choose an option: ","Valid option", 0, 2);
    }
}
