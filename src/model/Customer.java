
package model;

import java.io.Serializable;

/**
 *
 * @author TranHongGam
 */
public class Customer implements Serializable{
    private String id;
    private String name;
    private String phone;
    private String email;
    private static final long serialVersionUID = -7273131369306327890L; // hoặc giá trị bạn chọn

    public Customer() {
    }

    public Customer(String id) {
        this.id = id;
        this.name = "";
        this.phone = "";
        this.email = "";
    }

    @Override
    public boolean equals(Object obj) {
        Customer c = (Customer)obj;
        return this.id.equals(c.id); 
    }

    public Customer(String id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void showInfor(){
        
        String str = String.format(
                "|%-10s | %-25s |%-12s|%-28s|",
                id, name, phone, email
        );
        System.out.println(str);
    }
    public void showInforInOrder(){
        System.out.println("|Customer code : " + id);
        System.out.println("|Customer name : " + name);
        System.out.println("|Phone number  : " + phone);
        System.out.println("|Email         : " + email);
        
    }
   
    
   
        
    
}
