
package business;

import Interf.Workable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import model.Customer;


public class CustomersList extends ArrayList<Customer> implements Workable<Customer>{
    String pathFile = "customers.dat";
    private static final long serialVersionUID = 6733369811600344014L;

    public CustomersList() {
    }
   
    public ArrayList<Customer> filterByName(String name){
        ArrayList<Customer> customerByName = new ArrayList<Customer>();
        String nameToSearch = name.toLowerCase().trim();//gam
        for (Customer item : this) {
            String customerName = item.getName().toLowerCase().replaceAll("[,]", "");//gamtranhong
            //String firstName = nameParts[0].trim();
            if(customerName.trim().contains(nameToSearch) ){
                customerByName.add(item);
            }
        }
        return customerByName;   
    }
    //customerNameLower.contains(nameToSearch) tìm tên đệm chứa chữ đó luôn
    @Override
    public boolean addNew(Customer x) {
        this.add(x);
        return true;
    }

    @Override
    public boolean update(Customer customer) {
        boolean check = false;
        Customer search = searchById(customer.getId());
        if (search != null) {
            // Cập nhật thông tin khách hàng
            search.setName(customer.getName());
            search.setPhone(customer.getPhone());
            search.setEmail(customer.getEmail());
            check = true;
        }
        return check;
    }
    //search by ID but return boolean
    public boolean searchByIdReturnBoolean(String id) {
        boolean check = false;
        for (Customer customer : this) {
            if (customer.getId().equals(id)) {
                check = true;
            }
        }
        return check;
    }

    @Override
    public Customer searchById(String id) {
        int index = this.indexOf(new Customer(id));
        return index == -1 ? null : this.get(index);
    }

    @Override
    public void showAll() {
        if (this.size() == 0) {
            System.out.println("Danh sách khách hàng rỗng.");
        }else{
            System.out.println("------------------------------------------------------------------------------------");
            System.out.println("                                 List Customer");
            System.out.println("------------------------------------------------------------------------------------");
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println(" Code       | Customer Name             |  Phone     | Email                      |");
            System.out.println("-----------------------------------------------------------------------------------");
            for (Customer item : this) {
                item.showInfor();
            }
            System.out.println("----------------------------------------------------------------------------------------------------------------------");
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
            ArrayList<Customer> temp = (ArrayList<Customer>) of.readObject();//	Đọc object từ file và ép kiểu đúng 
            this.clear();         // Xóa dữ liệu cũ nếu có trước khi bỏ vô list
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
            System.out.println("file error" + e.getMessage());
            return false;
        }finally{
            try {
                if(of!= null) of.close();
                if(f!= null) f.close();
            } catch (Exception e) {
                System.out.println("error closing file");
            }
        }
    }
    
    public  void sortCustomerByName(){
        Comparator<Customer> sortByName = new Comparator<Customer>() {
            @Override
            public int compare(Customer c1, Customer c2) {
                return c1.getName().compareTo(c2.getName());
            }
        };
        Collections.sort(this, sortByName);
                
    }
}
