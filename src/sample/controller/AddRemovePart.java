package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    public void toMain(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/sample/views/Main.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }
}
