
package business;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import util.Menu;
import java.util.ArrayList;
import java.util.Date;
import model.Customer;
import model.Order;
import model.SetMenu;
import util.Inputter;


public class Cabinet {
    private CustomersList customers = new CustomersList();
    private OrdersList orders = new OrdersList();
    private SetMenuList setmenus = new SetMenuList();
    private Menu menu = new Menu();
    public void readFile(){
        customers.readFromFile();
        setmenus.readFromFile();
        orders.readFromFile();
    }

    public void addNewCustomer() {
        String id;
        String name;
        String email;
        String phone;
        while (true) {
            customers.showAll();
            System.out.println("-----------------Register new customer-----------------");
            while (true) {

                id = Inputter.inputString("Enter ID (format: starts with C, K, or G, followed by 4 digits, e.g., C1234): ",
                        "Invalid format. Please try again.",
                        "^[CKG][0-9]{4}$");
                if (!customers.searchByIdReturnBoolean(id)) {
                    break;
                } else {
                    System.out.println("Customer ID already exists!!!");
                }
            }
            //enter name
            while (true) {
                String firstName = Inputter.inputString("Enter first name: ", "Invalid format. Please try again.", "^[A-Za-z ]+$");
                String lastName = Inputter.inputString("Enter last name: ", "Invalid format. Please try again.", "^[A-Za-z ]+$");
                name = firstName + "," + lastName;
                if (name.length() > 2 && name.length() < 25) {
                    break;
                } else {
                    System.out.println("Full name must be between 2 and 25 characters!");
                }
            }
            //enter email
            email = Inputter.inputEmail("Enter email: ", "Invalid format. Please try again.");

            //enter phone
            phone = Inputter.inputPhoneNumber("Enter phone number: ", "Invalid phone number", "^0\\d{9}$");

            Customer newCus = new Customer(id, name, phone, email);
            if (customers.addNew(newCus)) {
                System.out.println("Addition Succesfully");
            } else {
                System.out.println("Failed");
            }   
            String wantToContinue = Inputter.inputString("Do you want to continue?[y/n]", "Invalid format", "^[yYnN]$");
            if (wantToContinue.equalsIgnoreCase("N")) {
                break;
            }
        }
    }
    public void updateCustomerInformation() {
        String updateId;
        String name;
        String email;
        String phone;
        while (true) {
            customers.showAll();
            System.out.println("---------------------Update customer information---------------------");
            while (true) {
                System.out.println("Enter ID to update update (format: starts with C, K, or G, followed by 4 digits, e.g., C1234 ");
                updateId = Inputter.inputString("Enter ID to Update: ", "Invalid format", "^[CKG][0-9]{4}$");
                //check exits
                Customer customerToUpdate = customers.searchById(updateId);
                if (customerToUpdate != null) {
                    break;
                }
                System.out.println("Customer not exist");
            }
            //enter name
            while (true) {
                String firstName = Inputter.inputString("Enter first name: ", "Invalid format. Please try again.", "^[A-Za-z ]+$");
                String lastName = Inputter.inputString("Enter last name: ", "Invalid format. Please try again.", "^[A-Za-z ]+$");
                name = firstName + "," + lastName;
                if (name.length() > 2 && name.length() < 25) {
                    break;
                } else {
                    System.out.println("Full name must be between 2 and 25 characters!");
                }
            }
            //enter email
            email = Inputter.inputEmail("Enter email: ", "Invalid format. Please try again.");

            //enter phone
            phone = Inputter.inputPhoneNumber("Enter phone number: ", "Invalid phone number", "^0\\d{9}$");

            Customer customer = new Customer(updateId, name, phone, email);
            if (customers.update(customer)) {
                orders.updateNameOfCustomerWhenCustomerUpdate(customer);
                System.out.println("Update Successfully");
            } else {
                System.out.println("Failed");
            }
            customers.showAll();
            String wantToContinue = Inputter.inputString("Do you want to continue?[y/n]", "Invalid format", "^[yYnN]$");
            if (wantToContinue.equalsIgnoreCase("N")) {
                break;
            }
        }
    }
    public void searchCustomerByName() {
        while (true) {
            String nameTosearch;
            System.out.println("---------------------Search customer by name---------------------");
            if (customers.isEmpty()) {
                System.out.println("The list is empty. No customers found.");
                break;
            }
            nameTosearch = Inputter.inputString("Enter first name of customer to find: ",
                    "Invalid format!!!", "^[A-Za-z ]+$", 2, 25);

            ArrayList<Customer> result = (ArrayList<Customer>) customers.filterByName(nameTosearch);
            if (result.size() == 0) { 
                System.out.println("No customer named " + nameTosearch + " found in the list.");
            } else {
                for (Customer customer : result) {
                    customer.showInfor();
                }
            }
            String wantToContinue = Inputter.inputString("Do you want to continue?[y/n]", "Invalid format", "^[yYnN]$");
            if (wantToContinue.equalsIgnoreCase("N")) {
                break;
            }
        }
    }
    public void displayMenu(){
        setmenus.showAll();
    }
    public void makeNewOrrder(){
        String customerId = null;
        Customer customerTmp = null;
        String dateToOrder = null;
        SetMenu newMenuOrder = null;
        int numberOfTableToOrder ;
        double totalPrice ;
        String orderId = null;
        System.out.println("-----------------------Place a feast order-----------------------");
        orders.showBasic();
        while (true) {
            while (true) {
                //check id cua customer dat ban
                while (true) {
                    customerId = Inputter.inputString("Enter ID customer to check exist: ", "Invalid format", "^[CKG][0-9]{4}$");
                    customerTmp = customers.searchById(customerId);
                    //check exits
                    if (customerTmp != null) {
                        break;
                    }
                    System.out.println("Not found");
                }
                //chon menu 
                setmenus.showAll();
                while (true) {
                    String menuIDToOrder = Inputter.inputString("Enter Set Menu code (format: PW + 3 digits, e.g., PW001 or PW092):", "Valid format", "^PW[0-9]{3}$");
                    newMenuOrder = setmenus.searchById(menuIDToOrder);
                    if (newMenuOrder == null) {
                        System.out.println("Not exist");
                    } else {
                        break;
                    }
                }

                //Xử lý ngày đặt tiệc : ngày đặt phải trong tương lai
                while (true) {
                    LocalDate dateNow = LocalDate.now();   //luu ngay hom nay
                    String dateToOrganize = Inputter.inputString("Enter the desired event date (DD/MM/YYYY): ",
                            "Please enter the date in the correct format (e.g., DD/MM/YYYY)..",
                            "^(0[1-9]|[12]\\d|3[01])/(0[1-9]|1[0-2])/\\d{4}$");// vdu 23/09/2025
                    String[] date = dateToOrganize.split("/");
                    String convertedDate = date[2] + "-" + date[1] + "-" + date[0];                    
                    LocalDate futureToOrganize = LocalDate.parse(convertedDate);//luu ngay to chuc tiec trong tuong lai 
                    if (!futureToOrganize.isAfter(dateNow) ) {
                        System.out.println("Order date must be in the future!!!");
                    } else {
                        dateToOrder = futureToOrganize.toString();
                        break;
                    }
                }
                //check duplicate
                if(orders.isDupplicate(customerId,newMenuOrder.getMenuId(), dateToOrder) == false){
                    break;
                }else{
                    System.out.println("Dupplicate data!!!");
                }
                
            }
            //đặt bàn 
            numberOfTableToOrder = Inputter.inputInt("Enter the banquet table number you wish to reserve: ", "Invalid input! Please enter a number between 1 and 100.", 1, 100);
            //xử lý giá của đơn 
            totalPrice = numberOfTableToOrder * newMenuOrder.getPrice();
            //xử lý mã đơn
            //check duplicate
            
            //create order ID
            Date currentDate = new Date();// để lấy ngày tháng năm phút giây 
            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");//đưa về format là số 
            //đưa về dạng ko có kí tự / : vì ngày tháng năm lấy ra là 21/05/2025 11:13:46                 
            String formattedDate = sdf.format(currentDate);
            orderId = formattedDate;
            Order orderNew = new Order(orderId, customerTmp, newMenuOrder, dateToOrder, numberOfTableToOrder, totalPrice);
            orders.add(orderNew);
            System.out.println("Order Placed Successfully!");
            orderNew.showInfor();
            String wantToContinue = Inputter.inputString("Do you want to continue?[y/n]", "Invalid format", "^[yYnN]$");
            if (wantToContinue.equalsIgnoreCase("N")) {
                break;
            }
        }
    }
    public void updateOrderInformation(){
        Order orderWantUpdate;
        String idOrderToUpdate;
        String customerId = null;
        String dateToOrder = null;
        SetMenu newMenuOrder = null;
        int numberOfTableToOrder ;
        double totalPrice ;
        String orderId = null;
        Customer cusOfOder =null;
        System.out.println("---------------------Update order information---------------------");
        orders.showBasic();
        while (true) {
            while (true) {
                idOrderToUpdate = Inputter.inputString("Enter ID Order to Update: ", "Invalid format!!!!!!", "^\\d{14}$");
                orderWantUpdate = orders.searchById(idOrderToUpdate);
                if (orderWantUpdate != null) {
                    break;
                }
                System.out.println("Order not exist.Please enter again");
                break;
            }
            cusOfOder = orderWantUpdate.getCustomer();
            setmenus.showAll();
            while (true) {
                String menuIDToOrder = Inputter.inputString("Enter Set Menu code (format: PW + 3 digits, e.g., PW001 or PW002):", "Valid format", "^PW[0-9]{3}$");
                newMenuOrder = setmenus.searchById(menuIDToOrder);
                if (newMenuOrder == null) {
                    System.out.println("Not exist");
                } else {
                    break;
                }
            }

                       //Xử lý ngày đặt tiệc : ngày đặt phải trong tương lai
            while (true) {
                LocalDate dateNow = LocalDate.now();   //luu ngay hom nay
                String dateToOrganize = Inputter.inputString("Enter the desired event date (DD/MM/YYYY): ",
                        "Please enter the date in the correct format (e.g., DD/MM/YYYY)..",
                        "^(0[1-9]|[12]\\d|3[01])/(0[1-9]|1[0-2])/\\d{4}$");//23/09/2025
                String[] date = dateToOrganize.split("/");
                String convertedDate = date[2] + "-" + date[1] + "-" + date[0];
                LocalDate futureToOrganize = LocalDate.parse(convertedDate);//luu ngay to chuc tiec trong tuong lai 
                if (!futureToOrganize.isAfter(dateNow)) {
                    System.out.println("Order date must be in the future!!!");
                } else {
                    dateToOrder = futureToOrganize.toString();
                    break;
                }
            }

            //check duplicate
            if (orders.isDupplicate(customerId, newMenuOrder.getMenuId(), dateToOrder) == false) {
                break;
            } else {
                System.out.println("Dupplicate data!!!");
            }
        }
        //đặt bàn 
        numberOfTableToOrder = Inputter.inputInt("Enter the banquet table number you wish to reserve: ", "Invalid input! Please enter a number between 1 and 100.", 1, 100);
        //xử lý giá của đơn 
        totalPrice = numberOfTableToOrder * newMenuOrder.getPrice();
        
        //xử lý mã đơn create order ID
        Date currentDate = new Date();// để lấy ngày tháng năm phút giây 
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");//đưa về format là số 
        //đưa về dạng ko có kí tự / : vì ngày tháng năm lấy ra là 21/05/2025 11:13:46                 
        String formattedDate = sdf.format(currentDate);
        orderId = formattedDate;

        Order orderUpdate = new Order(idOrderToUpdate, cusOfOder, newMenuOrder, dateToOrder, numberOfTableToOrder, totalPrice);
        if (orders.update(orderUpdate)) {
            orders.saveToFile();
            System.out.println("Update Successfully");
        } else {
            System.out.println("Failed");
        }
        orders.showAll();

    }
    public void saveToFile(){
        customers.saveToFile();
        orders.saveToFile();
        System.out.println("Customer data has been successfully saved to \"customers.dat\".");
        System.out.println("Order data has been successfully saved to \"feastMenu.csv\".");
    }
    public void displayByChoice() {
        while (true) {
            int choiceToDisplay = Menu.showMenuInFun8();
            if (choiceToDisplay == 1) {
                customers.sortCustomerByName();
                customers.showAll();
            } else if (choiceToDisplay == 2) {
                orders.sortOrderByDate();
                orders.showBasic();
                //orders.showAll();
            } else {
                break;
            }
        }

    }
    public void exit(){
        String choiceTmp = Inputter.inputString("Do you wwant to save data to file ? [y/n]: ", "Invalid format.Please try agagin", "^[yYnN]$").toLowerCase();
        boolean checkSaveCustomerList = false;
        boolean checkSaveOrderList = false;
        if (choiceTmp.equals("y")) {
            if(customers.saveToFile()){
                checkSaveCustomerList = true;
            }
            if(orders.saveToFile()){
                checkSaveOrderList = true;
            }
        }
        if(checkSaveCustomerList && checkSaveOrderList){
             System.out.println("Save Succcessfully. ");
        }
        System.out.println("Bye Bye");
    }
    
    
    
    
    
}
