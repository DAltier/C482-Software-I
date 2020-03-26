/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danaaltier_inventorysystem.View_Controller;

import danaaltier_inventorysystem.Model.InHouse;
import danaaltier_inventorysystem.Model.Outsourced;
import danaaltier_inventorysystem.Model.Part;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dana Altier
 */
public class AddModifyPartScreenController implements Initializable {

    //Radio buttons
    @FXML
    private RadioButton inHouseRBtn;
    @FXML
    private RadioButton outsourcedRBtn;
    @FXML
    private ToggleGroup PartToggleGroup;
    
    //Labels
    @FXML
    private Label screenNameLbl;
    @FXML
    private Label partSourcingLbl;
    
    //Text fields
    @FXML
    private TextField partIDTxt;
    @FXML
    private TextField partNameTxt;
    @FXML
    private TextField partStockTxt;
    @FXML
    private TextField partPriceTxt;
    @FXML
    private TextField partMaxStockTxt;
    @FXML
    private TextField partMinStockTxt;
    @FXML
    private TextField partCompanyNameTxt;
    
    //Buttons
    @FXML
    private Button partSaveBtn;
    @FXML
    private Button partCancelBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        screenNameLbl.setText(MainScreenController.formName);
        
        if (screenNameLbl.getText().contains("Modify")) {
            Part part = MainScreenController.stock.lookupPart(MainScreenController.partToMod);
            if (part != null) {
                partIDTxt.setText(Integer.toString(part.getId()));
                partNameTxt.setText(part.getName());
                partStockTxt.setText(Integer.toString(part.getStock()));
                partPriceTxt.setText(Double.toString(part.getPrice()));
                partMaxStockTxt.setText(Integer.toString(part.getMax()));
                partMinStockTxt.setText(Integer.toString(part.getMin()));
                
                if (part instanceof InHouse) {
                    inHouseRBtn.setSelected(true);
                    partCompanyNameTxt.setText(Integer.toString(((InHouse)part).getMachineID()));
                }
                else{
                    outsourcedRBtn.setSelected(true);
                    partCompanyNameTxt.setText(((Outsourced)part).getCompanyName());
                }
            }  
        }
        partSourcingLbl.setText(getSourcingTxt());
    } 
    
    public void partRBtn() {
        
        partSourcingLbl.setText(getSourcingTxt());
        
    }

    private String getSourcingTxt() {
        
        String lblTxt="";
        if (inHouseRBtn.isSelected()) {
            lblTxt = "Machine ID";
        }
        else if (outsourcedRBtn.isSelected()) {
            lblTxt = "Company Name";
        }
        return lblTxt;
        
    }
    
    private String noBlanks() {
        
        partNameTxt.setText(partNameTxt.getText().trim());
        partStockTxt.setText(partStockTxt.getText().trim());
        partPriceTxt.setText(partPriceTxt.getText().trim());
        partMinStockTxt.setText(partMinStockTxt.getText().trim());
        partMaxStockTxt.setText(partMaxStockTxt.getText().trim());
        partCompanyNameTxt.setText(partCompanyNameTxt.getText().trim());
        
        if (partNameTxt.getText().isEmpty()) {
            return "The name field requires a value.";       
        }
        
        if (partStockTxt.getText().isEmpty()) {
            return "The inventory field requires a value.";       
        }
        
        if (partPriceTxt.getText().isEmpty()) {
            return "The price field requires a value.";       
        }
        
        if (partMinStockTxt.getText().isEmpty()) {
            return "The minimum stock field requires a value.";       
        }
        
        if (partMaxStockTxt.getText().isEmpty()) {
            return "The maximum stock field requires a value.";       
        }
        
        if (partCompanyNameTxt.getText().isEmpty()) {
            if(inHouseRBtn.isSelected()) {
                return "The machine Id field requires a value.";
            }
            else {
                return "The company name field requires a value.";  
            }
        }
        return "";
        
    }
    
    @FXML
    private void partCancelBtn() throws IOException {
        
        Alert message = new Alert(Alert.AlertType.CONFIRMATION);
        message.setTitle("Closing screen");
        message.setHeaderText("Any unsaved work will be lost upon leaving this screen");
        message.setContentText("Do you want to exit the screen?");
            
        Optional<ButtonType> response = message.showAndWait();
        if (response.get() == ButtonType.OK) {
            Stage stage = (Stage)partCancelBtn.getScene().getWindow();
            stage.close();
        }
            
    }
    
    @FXML
    private void partSaveBtn() throws IOException {
        
        String error="";
        int inv, min, max, compName = 0;
        double price;
        
        try {
            //ensures there are no blank fields
            error = noBlanks();
            if (!error.isEmpty()) {
                throw new IllegalArgumentException(error);
            }
                
            error = "Integer type required for Inv";
            inv = Integer.parseInt(partStockTxt.getText());
                
            error = "Double type required for Price/Cost";
            price = Double.parseDouble(partPriceTxt.getText());
                
            error = "Integer type required for Max";
            max = Integer.parseInt(partMaxStockTxt.getText());
                
            error = "Integer type required for Min";
            min = Integer.parseInt(partMinStockTxt.getText());
                
            if (inHouseRBtn.isSelected()) {
                error = "Integer required for Machine Id";
                compName = Integer.parseInt(partCompanyNameTxt.getText());
            }  
                
            error = "";
            if (min > max) {
                error = "Min cannot be greater than Max";
                throw new IllegalArgumentException(error);
            }
            if (inv > max) {
                error = "Inv cannot be greater than Max";
                throw new IllegalArgumentException(error);
            }
            if (inv < min) {
                error = "Inv cannot be less than Min";
                throw new IllegalArgumentException(error);
            }
                
            Part part;
            if (inHouseRBtn.isSelected()) {
                part = new InHouse();
                ((InHouse)part).setMachineID(compName);
            }
            else {
                part = new Outsourced();
                ((Outsourced)part).setCompanyName(partCompanyNameTxt.getText());
            }
            part.setName(partNameTxt.getText());
            part.setStock(inv);
            part.setPrice(price);
            part.setMax(max);
            part.setMin(min);
                
            if (screenNameLbl.getText().contains("Modify")) {
                part.setId(Integer.parseInt(partIDTxt.getText()));
                if (inHouseRBtn.isSelected()) {
                    MainScreenController.stock.updatePart((InHouse)part);
                }
                else {
                    MainScreenController.stock.updatePart((Outsourced)part);
                }
            }
            else {
                int partID = MainScreenController.stock.nextPartID();
                part.setId(partID);
                if (inHouseRBtn.isSelected()) {
                    MainScreenController.stock.addPart((InHouse)part);
                }
                else {
                    MainScreenController.stock.addPart((Outsourced)part);
                }
            }
                
            displayMessage("Part saved");
            Stage stage = (Stage)partSaveBtn.getScene().getWindow();
            stage.close();
        }
        catch (IllegalArgumentException e) {
            Alert save = new Alert(AlertType.ERROR);
            save.setTitle(null);
            save.setHeaderText(null);
            save.setContentText(error);
            save.showAndWait();
        }
       
    }
    
    //Display messages to user
    @FXML
    public void displayMessage(String message) {
        
        Alert displayMessage = new Alert(Alert.AlertType.INFORMATION);
        displayMessage.setTitle(null);
        displayMessage.setHeaderText(null);
        displayMessage.setContentText(message);
        displayMessage.showAndWait();
        
    }
       
}
