package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.Stage;
import sample.model.Part;
import sample.model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.model.Inventory.*;



public class MainController implements Initializable {

    public Button addButton;
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

    @FXML
    private Button removePart;
    @FXML
    private Button removeProd;







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



    public void onAddRemove(ActionEvent actionEvent)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/AddRemovePart.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Second Screen");
        stage.setScene(scene);
        stage.show();
    }


}