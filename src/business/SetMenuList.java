
package business;

import Interf.Workable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import model.SetMenu;


public class SetMenuList extends ArrayList<SetMenu> implements Workable<SetMenu>{
    String csvFile = "FeastMenu.csv";

    public SetMenuList() {
    }

    
    @Override
    public boolean addNew(SetMenu x) {
       this.add(x);
       return true;
    }

    @Override
    public boolean update(SetMenu x) {
       return true;
    }

    public boolean searchByIdReturnBoolean(String id){
        for (SetMenu setMenu : this) {
            if (setMenu.getMenuId().equals(id)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public SetMenu searchById(String id) {
        for (SetMenu item : this) {
            if(item.getMenuId().equalsIgnoreCase(id)){
                return item;
            }
        }
        return null;
    }

    @Override
    public void showAll() {
        System.out.println("----------------------------------------------------------------");
        System.out.println(" List of Set Menus for ordering party:");
        System.out.println("----------------------------------------------------------------");
        for (SetMenu setmenu : this) {
            setmenu.showInfor();
        }
    }

    @Override
    public boolean readFromFile() {
        File file = new File(csvFile);
        FileReader f = null;// chỉ có đọc 1 từ 1 mảng kí tự mà phải biết size 
        BufferedReader bf = null; // có đọc 1 line nên dùng nó , đọc cho tới khi gặp /n thì thôi
        // Kiểm tra xem file có tồn tại không
        if (!file.exists()) {
            System.out.println("File " + csvFile + " does not exist.");
            return false;
        }
        
        try{
            f = new FileReader(file);
            bf = new BufferedReader(f); //copy f qua bf để đọc 1 line
            String line = bf.readLine() ;//doc khoa ne
            this.clear();
//          ở dòng tiếp theo nó chính là data nên phải xử lý nó 
            
            while (bf.ready()) { // hàm ready trẻ về true false khi chạm tới 'EOF'
                //khi lấy ra đươc dữ liệu ta phải biến nó thành object và lưu vào RAM
                line = bf.readLine();
                if (line == null) {
                    break;
                }
                String[] data = line.split(","); // tách từng thông tin ra 
                String menuId = data[0].trim();
                String menuName = data[1].trim();
                double price = Double.parseDouble(data[2].trim());
                String ingredients = data[3].trim();
                
                this.add(new SetMenu(menuId, menuName, price, ingredients));
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Không thể đọc dữ liệu từ feastMenu.csv.Vui lòng kiểm tra lại");
        }finally{
          try{ 
              if(f!= null) f.close();
              if(bf != null) bf.close();//đóng file lại  
          }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Không thể đọc dữ liệu từ feastMenu.csv.Vui lòng kiểm tra lại");
          }
        }
        return true;
    }

    @Override
    public boolean saveToFile() {
        return true;
    }
    
}
