package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.model.InHouse;
import sample.model.Inventory;
import sample.model.Outsourced;
import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

public class AddPart implements Initializable {
    public TextField partIDField;
    public RadioButton partOutsourced;
    @FXML
    private RadioButton inHousePart;
    @FXML
    private TextField partName;
    @FXML
    private TextField partInv;
    @FXML
    private TextField partPrice;
    @FXML
    private TextField partMax;
    @FXML
    private TextField machineIDCompName;
    @FXML
    private Label machineCompany;
    @FXML
    private TextField partMin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Inventory.incrementPartId();
    }
    public void toMain(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/views/Main.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }
    public void onInHouse(ActionEvent actionEvent) {
        machineCompany.setText("Machine ID");

    }
    public void onOutsourced(ActionEvent actionEvent) {
        machineCompany.setText("Company ID");

    }
    public void onSaveP(ActionEvent actionEvent) throws IOException {

        String pName =partName.getText();
        int pMin =Integer.parseInt(partMin.getText());
        int partID= Inventory.partIdCount;
        int pInv = Integer.parseInt(partInv.getText());
        int pMax =Integer.parseInt(partMax.getText());
        Double pPrice = Double.parseDouble(partPrice.getText());

            if (inHousePart.isSelected()) {
                int machineId = Integer.parseInt(machineIDCompName.getText());
                InHouse p = new InHouse(partID, pName, pPrice, pInv, pMax, pMin, machineId);
                Inventory.addPart(p);
            } else {
                String companyName = machineIDCompName.getText();
                Outsourced outPart = new Outsourced(partID, pName, pPrice, pInv, pMin, pMax, companyName);
                Inventory.addPart(outPart);
            }

        Parent backToMain = FXMLLoader.load(getClass().getResource("/sample/views/Main.fxml"));
        Scene scene = new Scene(backToMain);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
