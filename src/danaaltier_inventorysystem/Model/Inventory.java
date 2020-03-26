/*
 * Parts and products information
 */
package danaaltier_inventorysystem.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Dana Altier
 */
public class Inventory {
    
    private ObservableList<Part> allParts = FXCollections.observableArrayList();
    private ObservableList<Product> allProducts = FXCollections.observableArrayList();
    
    public void addPart(Part newPart) {
        
        allParts.add(newPart);
        
    }
    
    public void addProduct(Product newProduct) {
        
        allProducts.add(newProduct);
        
    }
    
    public ObservableList<Part> lookupPart(String partName) {
     
        ObservableList<Part> found = FXCollections.observableArrayList();
        for (Part part : allParts) {
            if ((Integer.toString(part.getId()).equals(partName)) || ((part.getName().toLowerCase()).contains(partName.toLowerCase()))) {
                found.add(part);
            }
        }
        return found;
        
    }       
    
    public Part lookupPart(int partId) {
        
        for (Part part : allParts) {
            if (part.getId() == partId) {
                return part;
            }
        }
        return null;
    }
    
    public ObservableList<Product> lookupProduct(String productName) {
     
        ObservableList<Product> found = FXCollections.observableArrayList();
        for (Product product : allProducts) {
            if ((Integer.toString(product.getId()).equals(productName)) || ((product.getName().toLowerCase()).contains(productName.toLowerCase()))) {
                found.add(product);
            }
        }
        return found;
        
    }  
    
    public Product lookupProduct(int productId) {
        
        for (Product product : allProducts) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }
    
//    public void updatePart(int index, Part selectedPart) { 
//    }
    
    public void updatePart(Part selectedPart) {
        
        for (int x=0; x<allParts.size(); x++){
            if (allParts.get(x).getId() == selectedPart.getId()) {
                allParts.set(x, selectedPart);
                break;
            }
        }
        
    }
    
//    public void updateProduct(int index, Product selectedProduct) { 
//    }
    
    public void updateProduct(Product selectedProduct) { 
        
        for (int x=0; x<allProducts.size(); x++){
            if (allProducts.get(x).getId() == selectedProduct.getId()) {
                allProducts.set(x, selectedProduct);
                break;
            }
        }
    }
    
    public void deletePart(Part selectedPart) {
        
        allParts.remove(selectedPart);
        
    }
    
    public void deleteProduct(Product selectedProduct) {
        
        allProducts.remove(selectedProduct);
        
    }
    
    public ObservableList<Part> getAllParts() {
        
        return allParts;
        
    }
    
    public ObservableList<Product> getAllProducts() {
        
        return allProducts;
        
    }
    
    public int nextPartID() {
        
        int max = 0;
        int id;
        for (Part p: allParts) {
            id = p.getId();
            if (id > max) {
                max = id;
            }
        }
        return max+1;
    }
    
    public int nextProductID() {
        
        int max = 0;
        int id;
        for (Product p: allProducts) {
            id = p.getId();
            if (id > max) {
                max = id;
            }
        }
        return max+1;
    }
 
}
