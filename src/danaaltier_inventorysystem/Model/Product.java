/*
 *Class of products that contain at least one part from the Part class.
 */
package danaaltier_inventorysystem.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Dana Altier
 */
public class Product {
    
    //Private fields declarations
    private final ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    
    public void setParts(ObservableList<Part> associatedParts) {
        
        associatedParts.forEach((p) -> {
            this.addAssociatedPart(p);
        });
        
    }
    
    //Adds a part associated with an existing product
    public void addAssociatedPart(Part p) {
        
        associatedParts.add(p);
        
    }
    
    //Removes a part associated with an existing product
    public void deleteAssociatedPart(Part p) {
        
        for (Part associatedPart : associatedParts) {
            if (associatedPart.getId() == p.getId()) {
                associatedParts.remove(associatedPart);
            }
        }
        
    }

    //Returns a list of all the parts associated with products
    public ObservableList<Part> getAllAssociatedParts() {
        
        return associatedParts;
        
    }
    
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
    
    public double costOfAllParts() {
        
        double total = 0;
        for(Part part: associatedParts) {
            total+=part.getPrice();
        }
        return total;
        
    }
  
}
