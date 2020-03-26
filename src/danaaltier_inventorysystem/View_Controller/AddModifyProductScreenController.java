/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danaaltier_inventorysystem.View_Controller;

import danaaltier_inventorysystem.Model.Part;
import danaaltier_inventorysystem.Model.Product;
import static danaaltier_inventorysystem.View_Controller.MainScreenController.stock;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dana Altier
 */
public class AddModifyProductScreenController implements Initializable {

    //Label
    @FXML
    private Label screenNameLbl;
     
    //Buttons
    @FXML
    private Button partSearchBtn;
    @FXML
    private Button addPartBtn; 
    @FXML
    private Button associatedPartDeleteBtn;
    @FXML
    private Button productSaveBtn;
    @FXML
    private Button productCancelBtn;
    
    //Text fields
    @FXML
    private TextField productIDTxt;
    @FXML
    private TextField productNameTxt;
    @FXML
    private TextField productStockTxt;
    @FXML
    private TextField productPriceTxt;
    @FXML
    private TextField productMaxStockTxt;
    @FXML
    private TextField productMinStockTxt;
    @FXML
    private TextField partSearchTxt;
    
    //Tables and columns
    @FXML
    private TableView<Part> partsTbl;  
    @FXML
    private TableColumn<Part, Integer> partIDClm;
    @FXML
    private TableColumn<Part, String> partNameClm;
    @FXML
    private TableColumn<Part, Integer> partStockClm;
    @FXML
    private TableColumn<Part, Double> partPriceClm;
    @FXML
    private TableView<Part> associatedPartsTbl;
    @FXML
    private TableColumn<Part, Integer> associatedPartIDClm;
    @FXML
    private TableColumn<Part, String> associatedPartNameClm;
    @FXML
    private TableColumn<Part, Integer> associatedPartStockClm;
    @FXML
    private TableColumn<Part, Double> associatedPartPriceClm;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        screenNameLbl.setText(MainScreenController.formName);
        if (!stock.getAllParts().isEmpty()) {
            updatePartsTable(stock.getAllParts());
        }
        if (screenNameLbl.getText().contains("Modify")) {
            Product product = MainScreenController.stock.lookupProduct(MainScreenController.prodToMod);
            if (product != null) {
                productIDTxt.setText(Integer.toString(product.getId()));
                productNameTxt.setText(product.getName());
                productStockTxt.setText(Integer.toString(product.getStock()));
                productPriceTxt.setText(Double.toString(product.getPrice()));
                productMaxStockTxt.setText(Integer.toString(product.getMax()));
                productMinStockTxt.setText(Integer.toString(product.getMin()));
                updateAssociatedPartsTable(product.getAllAssociatedParts());
            }
        }
        else {
            productStockTxt.setText("0");
        }
    }

    //Search part by pressing Enter on text field
    @FXML
    private void searchTxtAction() throws IOException {
        
        searchParts();
        
    }
    
    //Search part button handler
    @FXML
    public void searchBtnAction() throws IOException {
        
        searchParts();
        
    }
    
    //Search Parts list
    public void searchParts() {
        
        String input = partSearchTxt.getText().trim();
        if (input.isEmpty()) {
            displayMessage("No text entered. Displaying existing parts, if available");
            updatePartsTable(stock.getAllParts());
        } else {
            ObservableList<Part> result = stock.lookupPart(input);
            if (!result.isEmpty()) {
                updatePartsTable(result);
            }
            else {
                displayMessage("No items found. Displaying existing parts, if available");
                updatePartsTable(stock.getAllParts());
            }
        }
        
    }
    
    //Populate parts table
    public void updatePartsTable(ObservableList<Part> list) {
        
        PropertyValueFactory<Part,Integer> id = new PropertyValueFactory<>("id");
        PropertyValueFactory<Part,String> partName = new PropertyValueFactory<>("name");
        PropertyValueFactory<Part,Integer> inv = new PropertyValueFactory<>("stock");
        PropertyValueFactory<Part,Double> cost = new PropertyValueFactory<>("price");
        
        partIDClm.setCellValueFactory(id);
        partNameClm.setCellValueFactory(partName);
        partStockClm.setCellValueFactory(inv);
        partPriceClm.setCellValueFactory(cost);
        
        partsTbl.setItems(list);
        partsTbl.refresh();
        
    }
    
    //Populate associated parts table
    public void updateAssociatedPartsTable(ObservableList<Part> list) {
        
        PropertyValueFactory<Part,Integer> id = new PropertyValueFactory<>("id");
        PropertyValueFactory<Part,String> partName = new PropertyValueFactory<>("name");
        PropertyValueFactory<Part,Integer> inv = new PropertyValueFactory<>("stock");
        PropertyValueFactory<Part,Double> cost = new PropertyValueFactory<>("price");
        
        associatedPartIDClm.setCellValueFactory(id);
        associatedPartNameClm.setCellValueFactory(partName);
        associatedPartStockClm.setCellValueFactory(inv);
        associatedPartPriceClm.setCellValueFactory(cost);
        
        associatedPartsTbl.setItems(list);
        associatedPartsTbl.refresh();
        
    }
    
    //Display messages to user
    public void displayMessage(String message) {
        
        Alert displayMessage = new Alert(Alert.AlertType.INFORMATION);
        displayMessage.setTitle(null);
        displayMessage.setHeaderText(null);
        displayMessage.setContentText(message);
        displayMessage.showAndWait();
        
    }
    
    //Ensured no product fields are empty
    private String noBlanks() {
        
        productNameTxt.setText(productNameTxt.getText().trim());
        productStockTxt.setText(productStockTxt.getText().trim());
        productPriceTxt.setText(productPriceTxt.getText().trim());
        productMinStockTxt.setText(productMinStockTxt.getText().trim());
        productMaxStockTxt.setText(productMaxStockTxt.getText().trim());
        
        if (productNameTxt.getText().isEmpty()) {
            return "The name field requires a value.";       
        }
        if (productStockTxt.getText().isEmpty()) {
            return "The inventory field requires a value.";       
        }
        if (productPriceTxt.getText().isEmpty()) {
            return "The price field requires a value.";       
        }
        if (productMinStockTxt.getText().isEmpty()) {
            return "The minimum stock field requires a value.";       
        }
        if (productMaxStockTxt.getText().isEmpty()) {
            return "The maximum stock field requires a value.";       
        } 
        return "";
        
    }
    
    //Adds a new associated part to the list from the list of current parts available
    @FXML
    private void addBtnAction() {
    
        if (!partsTbl.getSelectionModel().isEmpty()) {
            Part part = partsTbl.getSelectionModel().getSelectedItem();
            associatedPartsTbl.getItems().add(part);
            updateAssociatedPartsTable(associatedPartsTbl.getItems());
        } else {
            displayMessage("You have to select a part to add.");
        }
    }   
    
    //Removes a part from the associated parts list
    @FXML
    private void deleteBtnAction() throws IOException {
        
        if (!associatedPartsTbl.getSelectionModel().isEmpty()) {
            Part part = associatedPartsTbl.getSelectionModel().getSelectedItem();
            
            Alert message = new Alert(Alert.AlertType.CONFIRMATION);
            message.setTitle("Confirm delete");
            message.setHeaderText("Deleting part id " + part.getId() + " name: "+ part.getName());
            message.setContentText("Please confirm that you want to do this.");
        
            Optional<ButtonType> response = message.showAndWait();
            if (response.get() == ButtonType.OK) {
                associatedPartsTbl.getItems().remove(part);
                associatedPartsTbl.refresh();
                displayMessage("Part was successfully deleted");
            }
            else {
                displayMessage("Part deletion canceled by user");
            }
        }
        else {
            displayMessage("No part was selected for deletion.");
        }
    }
    
    //Save product button handler
    @FXML
    private void saveBtnAction() throws IOException {
        
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
            inv = Integer.parseInt(productStockTxt.getText());
                
            error = "Double type required for Price/Cost";
            price = Double.parseDouble(productPriceTxt.getText());
                
            error = "Integer type required for Max";
            max = Integer.parseInt(productMaxStockTxt.getText());
                
            error = "Integer type required for Min";
            min = Integer.parseInt(productMinStockTxt.getText());
           
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
                
            Product product = new Product();
            product.setName(productNameTxt.getText());
            product.setStock(inv);
            product.setPrice(price);
            product.setMax(max);
            product.setMin(min);
            
            boolean productCheck;
            if (screenNameLbl.getText().contains("Add")) {
                if (associatedPartsTbl.getItems().isEmpty()) {
                    displayMessage("Products must have at least one associated part.");
                    productCheck = false;
                }
                else {
                   int next = MainScreenController.stock.nextProductID();
                   product.setId(next);
                   product.setParts(associatedPartsTbl.getItems());
                   if (product.costOfAllParts() > product.getPrice()) {
                       productCheck = false;
                       displayMessage("Product price must be higher than the value of all associated parts: "+product.costOfAllParts());
                   }
                   else {
                       MainScreenController.stock.addProduct(product);
                       productCheck = true;
                   }
                }
            }
            else {
                product.setId(MainScreenController.prodToMod);
                if (associatedPartsTbl.getItems().isEmpty()) {
                    //Allowing user to remove associated parts in preparation for product deletion
                    Alert message = new Alert(Alert.AlertType.CONFIRMATION);
                    message.setTitle("Confirm product update");
                    message.setHeaderText("Saving current product without associated parts.");
                    message.setContentText("Please confirm your selection.");
                    
                    Optional<ButtonType> response = message.showAndWait();
                    if (response.get() == ButtonType.OK) {
                        productCheck = true;
                        product.setId(Integer.parseInt(productIDTxt.getText()));
                        product.setParts(associatedPartsTbl.getItems());
                        MainScreenController.stock.updateProduct(product);
                    }
                    else {
                        productCheck = false;
                        displayMessage("The product cannot be saved without at least one associated part.");
                    }
                }
                else {
                    product.setId(Integer.parseInt(productIDTxt.getText()));
                    product.setParts(associatedPartsTbl.getItems());
                    if (product.costOfAllParts() > product.getPrice()) {
                       productCheck = false;
                       displayMessage("Product price must be higher than the value of all associated parts: "+product.costOfAllParts());
                    }
                    else {
                        MainScreenController.stock.updateProduct(product);
                        productCheck = true;
                    }
                }
            }
            
            if (productCheck)
            {
                displayMessage("Product saved");
                
                Stage stage = (Stage)productSaveBtn.getScene().getWindow();
                stage.close();
            }
            
        }
        catch (IllegalArgumentException e) {
            Alert save = new Alert(Alert.AlertType.ERROR);
            save.setTitle("Input error");
            save.setHeaderText(null);
            save.setContentText(error);
            save.showAndWait();
        }
        
    } 
    
    //Cancel button handler
    @FXML
    private void cancelBtnAction() throws IOException {
        
        Alert message = new Alert(Alert.AlertType.CONFIRMATION);
        message.setTitle("Confirm exit");
        message.setHeaderText("Returning to main screen. All unsaved work will be lost.");
        message.setContentText("Please confirm that you want to do this.");
        
        Optional<ButtonType> response = message.showAndWait();
        if (response.get() == ButtonType.OK) {
            Stage stage = (Stage)productCancelBtn.getScene().getWindow();
            stage.close();
        }
    }
   
}
