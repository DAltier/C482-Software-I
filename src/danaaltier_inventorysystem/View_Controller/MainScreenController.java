/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danaaltier_inventorysystem.View_Controller;

import danaaltier_inventorysystem.Model.Inventory;
import danaaltier_inventorysystem.Model.Part;
import danaaltier_inventorysystem.Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.collections.ObservableList;


/**
 * FXML Controller class
 *
 * @author Dana Altier
 */
public class MainScreenController implements Initializable {

    //Buttons
    @FXML
    private Button partSearchBtn;
    @FXML
    private Button partAddBtn;
    @FXML
    private Button partModifyBtn;
    @FXML
    private Button partDeleteBtn;
    @FXML
    private Button productSearchBtn;
    @FXML
    private Button productAddBtn;
    @FXML
    private Button productModifyBtn;
    @FXML
    private Button productDeleteBtn;
    @FXML
    private Button mainScreenExitBtn;
    
    //Text fields
    @FXML
    private TextField partSearchTxt;
    @FXML
    private TextField productSearchTxt;
    
    //Table views and columns
    //Parts
    @FXML
    private TableView<Part> partsTbl;
    @FXML
    private TableColumn<Part,Integer> partIDClm;
    @FXML
    private TableColumn<Part,String> partNameClm;
    @FXML
    private TableColumn<Part,Integer> partStockClm;
    @FXML
    private TableColumn<Part,Double> partPriceClm;
    //Products
    @FXML
    private TableView<Product> productsTbl;
    @FXML
    private TableColumn<Product,Integer> productIDClm;
    @FXML
    private TableColumn<Product,String> productNameClm;
    @FXML
    private TableColumn<Product,Integer> productStockClm;
    @FXML
    private TableColumn<Product,Double> productPriceClm;
    
    //Stores current inventory, both parts and products
    public static Inventory stock = new Inventory();
    
    //name of FXML form being loaded
    public static String formName = "";
    
    public static int partToMod = 0;
    public static int prodToMod = 0;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    //Calls the appropriate lookup nethod based on text input; triggered by pressing the Enter key 
    @FXML
    private void searchTxtAction(ActionEvent event) throws IOException {
        
        if (event.getSource() == partSearchTxt) {
            lookupPart();
        }
        else {
            if (event.getSource() == productSearchTxt) {
                lookupProduct();
            }
        }
    }
    
    //Calls the appropriate lookup nethod based on text input; triggered by Search button interaction 
    @FXML
    private void searchBtnAction(ActionEvent event) throws IOException {
        
        if (event.getSource() == partSearchBtn) {
            lookupPart();
        }
        else {
            if (event.getSource() == productSearchBtn) {
                lookupProduct();
            }
        }
        
    }
    
    //Method looks up part based on text input from the left side search box
    @FXML
    public void lookupPart() {
        
        String text = partSearchTxt.getText().trim();
        if (text.isEmpty()) {
            //No text was entered. Displaying all existing parts from inventory, if available
            displayMessage("No text entered. Displaying existing parts, if available");
            updatePartsTable(stock.getAllParts());
        }
        else{
            ObservableList<Part> result = stock.lookupPart(text);
            if (!result.isEmpty()) {
                //Part foung in inventory. Displaying information available. 
                updatePartsTable(result);
            }
            else {
                //Part not found in inventory. Displaying all existing parts from inventory, if available
                displayMessage("Part not found. Displaying existing parts, if available");
                updatePartsTable(stock.getAllParts());
            }
        }
            
    }
    
    //Method looks up product based on text input from the right side search box
    @FXML
    public void lookupProduct() {
        
        String text = productSearchTxt.getText().trim();
        if (text.isEmpty()) {
            //No text was entered. Displaying all existing products from inventory, if available
            displayMessage("No text entered. Displaying existing products, if available");
            updateProductsTable(stock.getAllProducts());
        }
        else{
            ObservableList<Product> result = stock.lookupProduct(text);
            if (!result.isEmpty()) {
                //Product found in inventory. Displaying information available. 
                updateProductsTable(result);
            }
            else {
                //Product not found in inventory. Displaying all existing products from inventory, if available
                displayMessage("Product not found. Displaying existing products, if available");
                updateProductsTable(stock.getAllProducts());
            }
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
    
    //Populates the parts table on the main screen
    @FXML
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
    
    //Populates the products table on the main screen
    @FXML
    public void updateProductsTable(ObservableList<Product> list) {
        
        PropertyValueFactory<Product,Integer> id = new PropertyValueFactory<>("id");
        PropertyValueFactory<Product,String> partName = new PropertyValueFactory<>("name");
        PropertyValueFactory<Product,Integer> inv = new PropertyValueFactory<>("stock");
        PropertyValueFactory<Product,Double> cost = new PropertyValueFactory<>("price");
        
        productIDClm.setCellValueFactory(id);
        productNameClm.setCellValueFactory(partName);
        productStockClm.setCellValueFactory(inv);
        productPriceClm.setCellValueFactory(cost);
        
        productsTbl.setItems(list);
        productsTbl.refresh();
        
    }
    
    //loads "Add Part" form when the Add button is pressed for parts
    @FXML
    private void addPartAction() throws IOException {
        
        generateScreen("AddModifyPartScreen.fxml","Add Part");
        updatePartsTable(stock.getAllParts());
        
    }

    //loads "Modify Part" form when the Modify button is pressed for parts if an item from the Parts table is selected
    @FXML
    private void modifyPartAction() throws IOException {
        
        if (!partsTbl.getSelectionModel().isEmpty()) {
            Part selected = partsTbl.getSelectionModel().getSelectedItem();
            partToMod = selected.getId();
            generateScreen("AddModifyPartScreen.fxml", "Modify Part");
        }
        else {
            displayMessage("No part selected for modification");
        }
            
    }

    //Attempts to remove a part from inventory
    @FXML
    private void deletePartAction() throws IOException {
        
        if (!partsTbl.getSelectionModel().isEmpty()) {
            Part selected = partsTbl.getSelectionModel().getSelectedItem();
            
            Alert message = new Alert(Alert.AlertType.CONFIRMATION);
            message.setTitle("Confirm delete");
            message.setHeaderText(" Are you sure you want to delete part ID: " + selected.getId() + ", name: " + selected.getName() + "?" );
            message.setContentText("Please confirm your selection");
            
            Optional<ButtonType> response = message.showAndWait();
            if (response.get() == ButtonType.OK)
            {
                stock.deletePart(selected);
                partsTbl.setItems(stock.getAllParts());
                partsTbl.refresh();
                displayMessage("The part " + selected.getName() + " was successfully deleted");
            }
            else {
                displayMessage("Deletion cancelled by user. Part not deleted.");
            }
        }
        else {
           displayMessage("No part selected for deletion");
        }
    }


    @FXML
    private void addProductAction(ActionEvent event) throws IOException {
        
        if (!stock.getAllParts().isEmpty()) {
            generateScreen("AddModifyProductScreen.fxml","Add Product");
            updateProductsTable(stock.getAllProducts());
        }
        else {
            displayMessage("No parts exist in inventory. Cannot add products without associated parts.");
        }
    }

    @FXML
    private void modifyProductAction(ActionEvent event) throws IOException {
        
        if (!productsTbl.getSelectionModel().isEmpty()) {
            Product selected = productsTbl.getSelectionModel().getSelectedItem();
            prodToMod = selected.getId();
            generateScreen("AddModifyProductScreen.fxml", "Modify Product");
        }
        else {
            displayMessage("No product selected for modification");
        }
        
    }

    @FXML
    private void deleteProductAction() throws IOException {
        
        if (!productsTbl.getSelectionModel().isEmpty()) {
            Product selected = productsTbl.getSelectionModel().getSelectedItem();
            if (!selected.getAllAssociatedParts().isEmpty()) {
                displayMessage("Product cannot be deleted due to associated parts. Remove parts before deleting.");
            }
            else {
                Alert message = new Alert(Alert.AlertType.CONFIRMATION);
                message.setTitle("Confirm delete");
                message.setHeaderText(" Are you sure you want to delete product ID: " + selected.getId() + ", name: " + selected.getName() + "?" ); 
                message.setContentText("Please confirm you selection.");
                
                Optional<ButtonType> response = message.showAndWait();
                if (response.get() == ButtonType.OK) {
                    stock.deleteProduct(selected);
                    productsTbl.setItems(stock.getAllProducts());
                    productsTbl.refresh();
                    displayMessage("Product has been deleted.");
                }
                else {
                    displayMessage("Deletion cancelled by user. Product not deleted.");
                }
            }
        }
        else {
            displayMessage("No product selected for deletion.");
        }
    }

    @FXML
    private void exitAction() throws IOException {
        
        Alert message = new Alert(Alert.AlertType.CONFIRMATION);
        message.setTitle("Confirm Exit");
        message.setHeaderText("You are about to exit the program.");
        message.setContentText("Please confirm your selection");
        
        Optional<ButtonType> response = message.showAndWait();
        if (response.get() == ButtonType.OK) {
            Stage stage = (Stage)mainScreenExitBtn.getScene().getWindow();
            stage.close();
        }
    }

    //generates modal form; text variable used to populate form label
    private void generateScreen(String form,String text) throws IOException {
        
        MainScreenController.formName = text;
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(form));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(partAddBtn.getScene().getWindow());
        stage.showAndWait();
        
    }
        
}
