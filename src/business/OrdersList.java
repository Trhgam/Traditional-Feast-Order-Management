
package business;

import Interf.Workable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import model.Customer;
import model.Order;


public class OrdersList extends ArrayList<Order> implements Workable<Order>{
    String pathFile = "orderList.dat";
    private static final long serialVersionUID =-7624394640276946423L;
    public OrdersList() {
    }
    
    @Override
    public boolean addNew(Order x) {
        this.add(x);
        return true;
    }
    
    @Override
    public boolean update(Order order) { // ch lam
        boolean check = false;
        Order updateOrder = searchById(order.getOrderId());
        if(updateOrder != null){
            // Cập nhật thông tin đơn hàng
            updateOrder.setMenu(order.getMenu());
            updateOrder.setNumOfTables(order.getNumOfTables());
            updateOrder.setEvenDate(order.getEvenDate());
            updateOrder.setTotalPrice(order.getNumOfTables() * order.getMenu().getPrice());
            check = true;
        }
        return check;
    }

    @Override
    public Order searchById(String id) {
        return this.get(this.indexOf(new Order(id)));
    }

    @Override
    public void showAll() {
        if (this.size() == 0) {
            System.out.println("Danh sách đơn rỗng.");
        }else{
            for (Order item : this) {
                item.showInfor();
            }
        }
    }
    public boolean isDupplicate(String customerId,String setMenuId, String date){
        boolean check = false;
        for (Order order : this) {
            if(order.getCustomer().getId().equalsIgnoreCase(customerId)
                    && order.getMenu().getMenuId().equals(setMenuId)
                    && order.getEvenDate().equals(date)){
                check = true;
            }
        }
        return check;
    }
    public void showBasic(){
        if (this.size() == 0) {
            System.out.println("Danh sách đơn rỗng.");
        }else{
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("                                      List Orders                                           |");
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("ID            | Event date  | Customer ID | Set Menu | Price      | Tables | Cost           |");
            System.out.println("---------------------------------------------------------------------------------------------");
            for (Order item : this) {
                item.showInforBasic();
            }
        }
    }

    @Override
    public boolean readFromFile() {
        FileInputStream f = null;
        ObjectInputStream of = null;
        //boolean check = false;
        File checkFile = new File(pathFile);
        if (!checkFile.exists()) {
            try {
                checkFile.createNewFile();
                return true; // New empty file created successfully
            } catch (IOException e) {
                System.err.println("Failed to create new file: " + e.getMessage());
                return false;
            }
        }
        // Kiểm tra nếu file rỗng
        if (checkFile.length() == 0) {
            return true; // File trống không có gì để đọc
        }
         try {
            f = new FileInputStream(pathFile);
            of = new ObjectInputStream(f);
            ArrayList<Order> temp = (ArrayList<Order>) of.readObject();//	Đọc object từ file và ép kiểu đúng 
            this.clear();         // Xóa dữ liệu cũ nếu có
            this.addAll(temp);  //ko dùng trực tiếp this ví this ko gán đc
            return true;
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
            return false;
        } finally {
            try {
                if (of != null) {
                    of.close();
                }
                if (f != null) {
                    f.close();
                }
            } catch (Exception e) {
                System.err.println("Error closing file " + e.getMessage());
            }
        }
    }

    @Override
    public boolean saveToFile() {
         FileOutputStream f = null;//ghi vào file nhi phan
        ObjectOutputStream of = null;//lớp thư viện  này giúp ghi object
        try{
           f = new FileOutputStream(pathFile);//mảng này chỉ chứa mảng byte nhưng mảng của mk lại chưa object
           of = new ObjectOutputStream(f) ;
           of.writeObject(this);
           // Must enable Seriazable
           return true;
        }catch(Exception e){ 
            System.out.println("error file" + e.getMessage());
            return false;
        }finally{
            try {
                if(of!= null) of.close();
                if(f!= null) f.close();
            } catch (Exception e) {
                System.out.println("error closing file" + e.getMessage());
            }
        }
    }
     //Update customer
    public boolean updateNameOfCustomerWhenCustomerUpdate(Customer customer) {
        boolean check = false;
        for (Order order : this) {
            if (order.getCustomer().getId().equals(customer.getId())) {
                order.setCustomer(customer);
                check = true;
            }
        }
        return check;
    }
    
    public void sortOrderByDate() {
        Comparator<Order> sortByDate = new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                LocalDate dateO1 = LocalDate.parse(o1.getEvenDate());
                LocalDate dateO2 = LocalDate.parse(o2.getEvenDate());
                return dateO1.compareTo(dateO2);
            }

        };
        Collections.sort(this, sortByDate);
    }
}
//chi return 1 lan trong ham + equal + ham searchBy Id + de y ten