
package model;

import java.io.Serializable;

/**
 *
 * @author TranHongGam
 */
public class SetMenu implements Serializable{
    private String menuId;
    private String menuName;
    private double price;
    private String ingredients;

    public SetMenu() {
    }

    public SetMenu(String menuId, String menuName, double price, String ingredients) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.price = price;
        this.ingredients = ingredients;
    }

    

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
    public void showInfor(){
        
        // Format menu information
        System.out.println("Code      : " + menuId);
        System.out.println("Name      : " + menuName);
        System.out.println("Price     : " + price + " Vnd");

        // Format ingredients with bullet points
        System.out.println("Ingredients:");
        ingredients = ingredients.replaceAll("\"", "");
        String[] monchinh = ingredients.split("#");
        
        for (String line : monchinh) {
            System.out.println(line.trim());
        }
       System.out.println("----------------------------------------------------------------");
    }
    
    
    
}
