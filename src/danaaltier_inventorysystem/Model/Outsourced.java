/*
 * Concrete class which extends the abstract Part class.
 */
package danaaltier_inventorysystem.Model;

/**
 *
 * @author Dana Altier
 */
public class Outsourced extends Part {
    
    //variable used to store the Company Name for outsourced parts
    private String companyName;
    
    //getter and setter methods for the companyName variable
    public String getCompanyName() {
        
        return companyName;
    
    }

    public void setCompanyName(String companyName) {
        
        this.companyName = companyName;
        
    }
  
}
