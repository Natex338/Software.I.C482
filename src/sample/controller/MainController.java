package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.Stage;
import sample.model.Inventory;
import sample.model.Part;
import sample.model.Product;


import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import static sample.model.Inventory.*;



public class MainController implements Initializable {
    public Button removePart;
    public Button removeProd;
    public TextField prodTextField;
    public TextField partsTextField;
    public Button exit;
    @FXML
    private TableView<Part> allPartsView;
    @FXML
    private TableColumn <Part, Integer> partIdCol;
    @FXML
    private TableColumn <Part,String>partNameCol;
    @FXML
    private TableColumn <Part, Integer>partInventory;
    @FXML
    private TableColumn <Part, Integer>partPrice;
    @FXML
    private TableView<Product> allProductsView;
    @FXML
    private TableColumn<Product, Integer> prodId;
    @FXML
    private TableColumn <Product, String> prodName;
    @FXML
    private TableColumn <Product, Integer> prodInventory;
    @FXML
    private TableColumn<Product, Integer>  prodPrice;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allPartsView.setItems(getAllParts());
        allProductsView.setItems(getAllProducts());
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        prodId.setCellValueFactory(new PropertyValueFactory<>("id"));
        prodName.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        prodPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

    }
    public void onRemovePart(ActionEvent actionEvent) {
        Part SP = allPartsView.getSelectionModel().getSelectedItem();
        if(SP == null)
            return;
        getAllParts().remove(SP);

        System.out.println("You Clicked Remove Part");
    }
    public void onRemoveProd(ActionEvent actionEvent) {
        Product SP = allProductsView.getSelectionModel().getSelectedItem();
        if(SP == null)
            return;
        getAllProducts().remove(SP);

        System.out.println("You Clicked Remove Product");
    }
    public void onclickAdd(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/views/addRemove.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Second Screen");
        stage.setScene(scene);
        stage.show();
    }
    public void getResultsHandler(ActionEvent actionEvent){
        String q = partsTextField.getText();
        ObservableList<Part> parts=searchByPartName(q);
        allPartsView.setItems(parts);
        if(parts.size()==0){
            try {
                int partId = Integer.parseInt(q);
                Part partSearch = getPartByID(partId);
                if (partSearch != null) {
                    parts.add(partSearch);
                }
            }
            catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "No Part Found", "ERROR!",JOptionPane.WARNING_MESSAGE);
            }
        }

    }
    private ObservableList<Part>searchByPartName(String partialName){
        ObservableList<Part> partsName = FXCollections.observableArrayList();
        ObservableList<Part> allParts = Inventory.getAllParts();

        for(Part p:allParts){
            if ((p.getName()).toLowerCase().contains((partialName).toLowerCase())){
                partsName.add(p);
            }
        }
        return partsName;
    }
    private Part getPartByID(int pID){
        ObservableList<Part> allParts = Inventory.getAllParts();

        for (Part p:allParts){
            if (p.getId()== pID){
                return p;
            }
        }
        JOptionPane.showMessageDialog(null, "No part with the ID: "+pID+" found!", "ERROR!",JOptionPane.WARNING_MESSAGE);
       return null;
    }
    public void onExit(ActionEvent actionEvent) {
        int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to Exit?","Warning",JOptionPane.YES_NO_OPTION);
        if(dialogResult == JOptionPane.YES_OPTION){
         
            System.exit(0);
        }

       
    }
}