
package model;

import java.io.Serializable;




public class Order implements Serializable{
    private String orderId;
    private Customer customer;
    private SetMenu menu;
    private String evenDate;
    private int numOfTables;
    private double totalPrice;
    private static final long serialVersionUID = -8146927016511403540L; // hoặc giá trị bạn chọn

    public Order(String id) {
        this.orderId = id;
        this.customer = null;  // hoặc new Customer() nếu cần
        this.menu = null;      // hoặc new SetMenu() nếu cần
        this.evenDate = "";
        this.numOfTables = 0;
        this.totalPrice = 0.0;
    }

    @Override
    public boolean equals(Object obj) {
        Order p = (Order)obj;
        return this.orderId.equals(p.orderId);
    }
    
    //generic mã order 
    public Order(String orderId, Customer customer, SetMenu menu, String evenDate, int numOfTables, double totalPrice) {
        this.orderId = orderId;
        this.customer = customer;
        this.menu = menu;
        this.evenDate = evenDate;
        this.numOfTables = numOfTables;
        this.totalPrice = totalPrice;
    }


    public Order() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public SetMenu getMenu() {
        return menu;
    }

    public void setMenu(SetMenu menu) {
        this.menu = menu;
    }

    public String getEvenDate() {
        return evenDate;
    }

    public void setEvenDate(String evenDate) {
        this.evenDate = evenDate;
    }


    public int getNumOfTables() {
        return numOfTables;
    }

    public void setNumOfTables(int numOfTables) {
        this.numOfTables = numOfTables;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
   
    
   public void showInfor() {
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.println("|Customer order information                        [Order ID: " + orderId + "]");
        System.out.println("----------------------------------------------------------------------------------------------");
        customer.showInforInOrder();
        System.out.println("|---------------------------------------------------------------------------------------------");
        System.out.println("| Code of Set Menu: " + menu.getMenuId());
        System.out.println("| Set menu name   : " + menu.getMenuName());
        System.out.println("| Even date       : " + evenDate );
        System.out.println("| Number of table : " + numOfTables);
        System.out.println("| Price           : " + formatCurrency(menu.getPrice()));
        System.out.println("| Ingredients     : ");
        String igredients = menu.getIngredients().replaceAll("\"", "");
        String[] monChinh = igredients.split("#");
        for (String line : monChinh) {
            System.out.println("| " + line.trim());
       }
        System.out.println("|---------------------------------------------------------------------------------------------");
        System.out.println("|Total price: " + formatCurrency(totalPrice));
        System.out.println("----------------------------------------------------------------------------------------------");
    }
   public void showInforBasic(){
       String row = String.format("%-14s| %-12s| %-11s | %-8s | %,-10.0f | %-6d | %,-15.0f|",
               orderId,
               evenDate,
               customer.getId(),
               menu.getMenuId(),
               menu.getPrice(),
               numOfTables,
               totalPrice);

       System.out.println(row);
       System.out.println("---");
   }
    
    // Thêm phương thức định dạng tiền tệ
    public String formatCurrency(double amount) {
        return String.format("%,.0f VND", amount);
    }


}
