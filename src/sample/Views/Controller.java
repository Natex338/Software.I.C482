package sample.Views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import sample.Model.Part;
import sample.Model.Product;

import java.net.URL;
import java.util.ResourceBundle;

import static sample.Model.Inventory.*;



public class Controller implements Initializable {
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

}
