package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.model.InHouse;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddRemovePart implements Initializable {


    public TextField partID;
    public TextField partName;
    public TextField partInv;
    public TextField partPrice;
    public TextField partMax;
    public TextField machineIDCompName;
    public Label machineCompany;
    public RadioButton inHousePart;
    public RadioButton partOutsourced;
    public TextField partMin;


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

    public void onSaveP(ActionEvent actionEvent) {
       if(inHousePart.isSelected()){

           int pId = Integer.parseInt(partID.getText());
           String pName= partName.getText();
           int pInv = Integer.parseInt(partInv.getText());
           int pPrice = Integer.parseInt(partMax.getText());
           int pMax=  Integer.parseInt(partMax.getText());
           int pMin= Integer.parseInt(partMin.getText());
           int machineId= Integer.parseInt(machineIDCompName.getText());
           InHouse p = new InHouse(pId,pName,pInv,pPrice,pMax,pMin,machineId);
        }
    }
}