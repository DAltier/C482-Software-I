/*
 * Abstract class used by the InHouse and Outsourced classes.
 */
package danaaltier_inventorysystem.Model;

/**
 *
 * @author Dana Altier
 */
public abstract class Part {
    
    //Private fields declarations
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    
    

    //Id getter and setter
    public int getId() {
        
        return id;
        
    }

    public void setId(int id) {
        
        this.id = id;
        
    }
    
    //Name getter and setter
    public String getName() {
        
        return name;
        
    }

    public void setName(String name) {
        
        this.name = name;
        
    }
    
    //Price getter and setter
    public double getPrice() {
        
        return price;
        
    }

    public void setPrice(double price) {
        
        this.price = price;
        
    }
    
    //Stock getter and setter
    public int getStock() {
        
        return stock;
        
    }

    public void setStock(int stock) {
        
        this.stock = stock;
        
    }
    
    //Min getter and setter
    public int getMin() {
        
        return min;
        
    }

    public void setMin(int min) {
        
        this.min = min;
        
    }
    
    //Max getter and setter
    public int getMax() {
        
        return max;
        
    }

    public void setMax(int max) {
        
        this.max = max;
        
    }
    
}
