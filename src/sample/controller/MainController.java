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
import java.io.IOException;
import java.net.URL;
import java.security.AllPermission;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import static sample.model.Inventory.*;



public class MainController implements Initializable {
    public Button removePart;
    public Button removeProd;
    public TextField prodTextField;
    public TextField partsTextField;
    public Button exit;
    public Button modifyProd;
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

    //public object passed to different screens
    public static Part partP = null;
    public static Product  productP=null;

    //part object to check validation
    private Part partCheck = null;



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

    /**
     * @return passes part to modify part screen.
     */
    public static Part partPass(){
        return partP;
    }

    /**
     * @return passes product object to modify product screen.
     */
    public static Product prodPass(){
        return productP;
    }

    /**
     * @param actionEvent removes parts from parts list
     */
    public void onRemovePart(ActionEvent actionEvent) {
        Alert removePartAlert = new Alert(Alert.AlertType.CONFIRMATION);
        removePartAlert.setTitle("Deleting Part!");
        removePartAlert.setHeaderText("Part will be removed!");
        removePartAlert.setContentText("Are you sure you want to delete this part?");
        Optional<ButtonType> result = removePartAlert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Part SP = allPartsView.getSelectionModel().getSelectedItem();
            if (SP == null)
                return;
            if (!deletePart(SP)) {
                Alert deletionError = new Alert(Alert.AlertType.WARNING);
                deletionError.setTitle("Deleting Part Error");
                deletionError.setContentText("Error");
                deletionError.showAndWait();
                return;
            } else {
                deletePart(SP);
                getAllParts().remove(SP);
                allPartsView.setItems(getAllParts());
            }
        }
    }

    /**
     * @param actionEvent remove products, presents error if associated parts are still attached.
     */
    public void onRemoveProd(ActionEvent actionEvent) {
        Alert removePartAlert = new Alert(Alert.AlertType.CONFIRMATION);
        removePartAlert.setTitle("Deleting Product!");
        removePartAlert.setHeaderText("Product will be removed!");
        removePartAlert.setContentText("Are you sure you want to delete this Product?");
        Optional<ButtonType> result = removePartAlert.showAndWait();
        if(result.get() == ButtonType.OK) {
            Product SP = allProductsView.getSelectionModel().getSelectedItem();
            if (SP == null)
                return;
            if (SP.getAllAssociatedParts().isEmpty()) {
                if (!deleteProduct(SP)) {
                    Alert deletionError = new Alert(Alert.AlertType.WARNING);
                    deletionError.setTitle("Deleting Product");
                    deletionError.setContentText("Error");
                    deletionError.showAndWait();
                    return;
                    }
                else
                    getAllProducts().remove(SP);
                    allProductsView.setItems(getAllProducts());
                }
            else {
                Alert deletionError = new Alert(Alert.AlertType.WARNING);
                deletionError.setTitle("Cannot Delete Product");
                deletionError.setHeaderText("Product still has associated parts");
                deletionError.setContentText("Product still has associated parts, remove parts before it can be deleted");
                deletionError.showAndWait();
            }
        }
    }

    /**
     * @param actionEvent open add part screen
     * @throws IOException  throw errors if it cant find the add part fxml
     */
    public void onclickAddPart(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/views/AddPart.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Part");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param actionEvent present and handle part search.
     */
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

    /**
     * @param partialName search for part match with partial name
     * @return parts that match name search
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
     * @param pID search part by ID
     * @return return matching part with ID match or message no part found
     */
    private Part getPartByID(int pID) {
        Part p = lookupPart(pID);
        if (p == null) {
            Alert noProduct = new Alert(Alert.AlertType.WARNING);
            noProduct.setTitle("No Part Found!");
            noProduct.setHeaderText("No Part Found!");
            noProduct.setContentText("No part with the ID: " + pID + " found!");
            Optional<ButtonType> result = noProduct.showAndWait();
            return null;
        }
        return p;
    }

    /**
     * @param actionEvent gather and present product search
     */
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
                Alert noProduct =new Alert(Alert.AlertType.WARNING);
                noProduct.setTitle("No Product Found!");
                noProduct.setHeaderText("No Product Found!");
                noProduct.setContentText("Please enter a valid product name or product ID");
                Optional<ButtonType> result = noProduct.showAndWait();
            }
        }
        if(!prod.isEmpty())
            allProductsView.setItems(prod);


    }

    /**
     * @param pID search products by part ID
     * @return return products matching part ID
     */
    private Product getProdByID(int pID){
        Product p = lookupProduct(pID);
        if (p == null) {
            Alert noProduct = new Alert(Alert.AlertType.WARNING);
            noProduct.setTitle("No Product Found!");
            noProduct.setHeaderText("No Product Found!");
            noProduct.setContentText("No Product with the ID: " + pID + " found!");
            Optional<ButtonType> result = noProduct.showAndWait();
            return null;
        }
        return p;
    }

    /**FUTURE ENHANCEMENT**
     *Search all products that have specific associated parts in the product.
     * Example search brakes on the product screen and show all products with brakes
     *
     * current function
     * @param partialName searches prod list for partial name match
     * @return returns parts found with partial match
     */
    private ObservableList<Product>searchByProdName(String partialName) {
        ObservableList<Product> prodName = FXCollections.observableArrayList();
        ObservableList<Product> allProd = Inventory.getAllProducts();
        for (Product p : allProd) {
            if ((p.getName()).toLowerCase().contains((partialName).toLowerCase())) {
                prodName.add(p);
            }
        }
        return prodName;
    }

    /**
     * @param actionEvent close program, prompts users to confirm exit.
     */
    public void onExit(ActionEvent actionEvent) {
        Alert exitAlert= new Alert(Alert.AlertType.CONFIRMATION);
        exitAlert.setTitle("Exiting Program");
        exitAlert.setHeaderText("Confirm Exit");
        exitAlert.setContentText("Are you sure you want to close the program?");
        Optional<ButtonType> result = exitAlert.showAndWait();
        if(result.get()==ButtonType.OK)
            System.exit(0);
    }

    /**
     * @param actionEvent navigate to modify part screen with selected part
     * @throws IOException throws error if it cant find the Fxml
     */
    public void onModifyPart(ActionEvent actionEvent) throws IOException {
        if (allPartsView.getSelectionModel().isEmpty()) {
        Alert nothingSelected = new Alert(Alert.AlertType.WARNING);
        nothingSelected.setTitle("Nothing Selected");
        nothingSelected.setHeaderText("Please Select a part");
        nothingSelected.setContentText("You must select a part to modify it");
        nothingSelected.showAndWait();
        return;
        }
        partP = allPartsView.getSelectionModel().getSelectedItem();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sample/views/ModifyPart.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Modify Part");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param actionEvent navigate to add new product screen
     * @throws IOException throws error if i cant find the Fxml file
     */
    public void onClickAddProd(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sample/views/AddProduct.fxml")));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Product");
        stage.setScene(scene);
        stage.show();
    }

    /**RUNTIME ERROR**
     * If nothing is selected how do modify the part?
     * I overcame this by checking if anything is selected, if its not it prompts user to make selection
     * @param actionEvent modify select part
     * @throws IOException if it cant find the fxml throws error.
     */
    public void onModifyProduct(ActionEvent actionEvent) throws IOException {

        if (allProductsView.getSelectionModel().isEmpty()) {
            Alert nothingSelected = new Alert(Alert.AlertType.WARNING);
            nothingSelected.setTitle("Nothing Selected");
            nothingSelected.setHeaderText("Please Select a product");
            nothingSelected.setContentText("You must select a product to modify it");
            nothingSelected.showAndWait();
            return;
        }
        productP = allProductsView.getSelectionModel().getSelectedItem();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sample/views/ModifyProduct.fxml")));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Product");
        stage.setScene(scene);
        stage.show();

    }
}
