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

import java.util.Objects;
import java.util.ResourceBundle;

public class AddPart implements Initializable {
    @FXML
    private RadioButton inHousePart;
    @FXML
    private TextField partID;
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
        String pMin =partMin.getText();
        String pId = partID.getText();
        String pInv = partInv.getText();
        String pMax =partMax.getText();
        String pPrice = partPrice.getText();


            if (inHousePart.isSelected()) {
                int machineId = Integer.parseInt(machineIDCompName.getText());
                InHouse p = new InHouse(pId, pName, pPrice, pInv, pMax, pMin, machineId);
                Inventory.addPart(p);
            } else {
                String companyName = machineIDCompName.getText();
                Outsourced outPart = new Outsourced(pId, pName, pPrice, pInv, pMin, pMax, companyName);
                Inventory.addPart(outPart);
            }

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sample/views/Main.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }
}
