/*
 * Concrete class which extends the abstract Part class.
 */
package danaaltier_inventorysystem.Model;

/**
 *
 * @author Dana Altier
 */
public class InHouse extends Part{
    
    //variable used to store the Machine ID for in-house parts
    private int machineID;

    //getter and setter methods for the machineID variable
    public int getMachineID() {
        
        return machineID;
        
    }

    public void setMachineID(int machineID) {
        
        this.machineID = machineID;
        
    }
       
}
