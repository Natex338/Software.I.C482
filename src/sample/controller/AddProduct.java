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
import sample.model.*;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

import static sample.model.Inventory.*;

public class AddProduct implements Initializable {

    public Label ProdErrorMsg;
    public TextField searchPartProd;
    public TextField prodMinField;
    @FXML
    private TextField productPartID;
    @FXML
    private TextField productPartName;
    @FXML
    private TextField prodPartInv;
    @FXML
    private TextField productIDField;
    @FXML
    private TextField productNameField;
    @FXML
    private TextField prodInvField;
    @FXML
    private TextField prodPriceField;
    @FXML
    private TextField prodMaxField;
    @FXML
    private TableView <Part>allPartsView;
    @FXML
    private TableView <Part>allProductsPartsView;
    @FXML
    private TableColumn<Part, Integer> partIdCol;
    @FXML
    private TableColumn<Part,String> partNameCol;
    @FXML
    private TableColumn<Part,Integer> partInventory;
    @FXML
    private TableColumn<Part,Integer> partPrice;
    @FXML
    public TableColumn<Part,Integer> prodPartId;
    public TableColumn prodPartName;
    public TableColumn <Part,Integer>prodPartInventory;
    public TableColumn<Part,Integer> prodPartPrice;
    public TableColumn<Part,Integer> partsTextField;
    private final ObservableList<Part> partsName = FXCollections.observableArrayList();
    private String ProdErrorMessage = "";


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Increment part ID
        Inventory.incrementPartId();
        allPartsView.setItems(getAllParts());
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        prodPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        prodPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodPartInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        prodPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**
     * @param pID search part by ID
     * @return return matching part with ID or message no part found
     */
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

    /**
     * @param actionEvent present search results in parts table
     */
    public void getResultsPartHandler(ActionEvent actionEvent){
        String q = searchPartProd.getText();
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
            allPartsView.getSelectionModel().selectFirst();
    }

    /**
     * @param partialName search parts by name
     * @return return part with matching name
     */
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

    /**
     * @param actionEvent go back to main screen
     * @throws IOException  trhows error if it can find main FXML
     */
    public void backToMain(ActionEvent actionEvent) throws IOException {
        Parent partCancel = FXMLLoader.load(getClass().getResource("/sample/views/Main.fxml"));
        Scene scene = new Scene(partCancel);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /**
     * @param actionEvent add part to product
     */
    public void onClickProdPartAdd(ActionEvent actionEvent) {
        Part p = allPartsView.getSelectionModel().getSelectedItem();
        partsName.add(p);
        allProductsPartsView.setItems(partsName);
    }

    /**
     * @param actionEvent Saving Product
     * @throws IOException throw error if input is invalid
     */
    public void onSaveProduct(ActionEvent actionEvent) throws IOException {
        String ProdErrorMessage="";
        boolean validSave = true;

        try {
            String prodName = productPartName.getText();
            int prodMin = Integer.parseInt(prodMinField.getText());
            int prodID = Inventory.partIdCount;
            int prodInv = Integer.parseInt(prodInvField.getText());
            int prodMax = Integer.parseInt(prodMaxField.getText());
            double prodPrice = Double.parseDouble(prodPriceField.getText());

            ProdErrorMessage += Inventory.validatePart(prodName, prodPrice, prodInv, prodMin, prodMax);

            if (!ProdErrorMessage.isEmpty()) {
                validSave = false;
                ProdErrorMsg.setText(ProdErrorMessage+"\n");

            }
            else {
                Product p =new Product(prodID,prodName,prodPrice,prodInv,prodMin,prodMax);
                for (Part pp:partsName){
                    p.addAssociatedPart(pp);
                }
                addProduct(p);
            }

        }
        catch (Exception e)
        {
            validSave = false;
            ProdErrorMessage += "ERROR: Please correct\n" + e.getMessage().toLowerCase() + "\n to a valid entry for the field.\n";
            ProdErrorMsg.setText(ProdErrorMessage+"\n");
            System.out.println(e.getMessage());
        }
        //return to Main screen if valid part is valid
        if (validSave) {
            Parent backToMain = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sample/views/Main.fxml")));
            Scene scene = new Scene(backToMain);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
    }

    /**
     * @param actionEvent Removing associated part selected
     */
    public void OnRemoveAssociatedPart(ActionEvent actionEvent) {
        Alert removePartAlert = new Alert(Alert.AlertType.CONFIRMATION);
        removePartAlert.setTitle("Deleting associated Part!");
        removePartAlert.setHeaderText("Part will be removed!");
        removePartAlert.setContentText("Are you sure you want to delete this part?");
        Optional<ButtonType> result = removePartAlert.showAndWait();
        if(result.get() == ButtonType.OK) {
            Part SP = allProductsPartsView.getSelectionModel().getSelectedItem();
            if (SP == null)
                return;
            partsName.remove(SP);
        }
    }
}
