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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.Stage;
import sample.model.Inventory;
import sample.model.Part;
import sample.model.Product;


import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
    }
    public void onRemoveProd(ActionEvent actionEvent) {
        Product SP = allProductsView.getSelectionModel().getSelectedItem();
        if(SP == null)
            return;
        getAllProducts().remove(SP);
    }
    public void onclickAdd(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/views/addPart.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Second Screen");
        stage.setScene(scene);
        stage.show();
    }
    public void getResultsPartHandler(ActionEvent actionEvent){
        String q = partsTextField.getText();
        ObservableList<Part> parts=searchByPartName(q);
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
        if(!parts.isEmpty())
        allPartsView.setItems(parts);

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
    public void getResultsProdHandler(ActionEvent actionEvent) {
        String q = prodTextField.getText();
        ObservableList<Product> prod=searchByProdName(q);
        if(prod.size()==0){
            try {
                int prodId = Integer.parseInt(q);
                Product prodSearch = getProdByID(prodId);
                if (prodSearch != null) {
                    prod.add(prodSearch);
                }
            }
            catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "No Product Found", "ERROR!",JOptionPane.WARNING_MESSAGE);
            }
        }
        if(!prod.isEmpty())
            allProductsView.setItems(prod);

    }
    private Product getProdByID(int pID){
        ObservableList<Product> allProd = Inventory.getAllProducts();

        for (Product p:allProd){
            if (p.getId()== pID){
                return p;
            }
        }
        JOptionPane.showMessageDialog(null, "No product with the ID: "+pID+" found!", "ERROR!",JOptionPane.WARNING_MESSAGE);
        return null;
    }
    private ObservableList<Product>searchByProdName(String partialName){
        ObservableList<Product> prodName = FXCollections.observableArrayList();
        ObservableList<Product> allProd = Inventory.getAllProducts();

        for(Product p:allProd){
            if ((p.getName()).toLowerCase().contains((partialName).toLowerCase())){
                prodName.add(p);
            }
        }
        return prodName;
    }
    public void onExit(ActionEvent actionEvent) {
        Alert exitAlert= new Alert(Alert.AlertType.CONFIRMATION);
        exitAlert.setTitle("Exiting Program");
        exitAlert.setHeaderText("Confirm Exit");
        exitAlert.setContentText("Are you sure you want to close the program?");
        Optional<ButtonType> result = exitAlert.showAndWait();
        if(result.get()==ButtonType.OK)
            System.exit(0);


       
    }


}