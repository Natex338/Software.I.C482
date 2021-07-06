package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.model.InHouse;
import sample.model.Inventory;
import sample.model.Outsourced;
import sample.model.Part;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ModifyPart implements Initializable {
    public TextField partIDField;
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


    }
    public void backtoMain(ActionEvent actionEvent) throws IOException {

            Parent root = FXMLLoader.load(getClass().getResource("/sample/views/Main.fxml"));
            Stage stage =  new Stage();
            stage.setTitle("Inventory Management System");
            stage.setScene(new Scene(root));
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
        int pPrice = Integer.parseInt(partPrice.getText());

        if (inHousePart.isSelected()) {
            int machineId = Integer.parseInt(machineIDCompName.getText());
            InHouse p = new InHouse(partID, pName, pPrice, pInv, pMax, pMin, machineId);
            Inventory.addPart(p);
        } else {
            String companyName = machineIDCompName.getText();
            Outsourced outPart = new Outsourced(partID, pName, pPrice, pInv, pMin, pMax, companyName);
            Inventory.addPart(outPart);
        }

            Parent root = FXMLLoader.load(getClass().getResource("/sample/views/Main.fxml"));
            Stage stage =  new Stage();
            stage.setTitle("Inventory Management System");
            stage.setScene(new Scene(root));
            stage.show();

    }
}
