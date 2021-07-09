package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.model.Inventory;
import sample.model.Part;
import sample.model.Product;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static sample.model.Inventory.getAllParts;
import static sample.model.Inventory.getAllProducts;

public class AddProduct implements Initializable {


    public TableView allPartsView;
    public TableColumn partIdCol;
    public TableColumn partNameCol;
    public TableColumn partInventory;
    public TableColumn partPrice;
    public TableView allProductsView;
    public TableColumn prodId;
    public TableColumn prodName;
    public TableColumn prodInventory;
    public TableColumn prodPrice;
    public TextField partsTextField;


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


    private Part getPartByID(int pID){
        ObservableList<Part> allParts = Inventory.getAllParts();

        for (Part p:allParts){
            if (p.getId()== pID){
                return p;
            }
        }
        Alert noProduct =new Alert(Alert.AlertType.WARNING);
        noProduct.setTitle("No Part Found!");
        noProduct.setHeaderText("No Part Found!");
        noProduct.setContentText("No part with the ID: "+pID+" found!");
        Optional<ButtonType> result = noProduct.showAndWait();
        return null;
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
                Alert noPart =new Alert(Alert.AlertType.WARNING);
                noPart.setTitle("No Part Found!");
                noPart.setHeaderText("No Part Found!");
                noPart.setContentText("Please enter a valid part name or part ID");
                Optional<ButtonType> result = noPart.showAndWait();

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
}
