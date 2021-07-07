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
import java.util.ResourceBundle;

public class ModifyPart implements Initializable {
    public TextField partIDField;
    public Label errorMessages;
    @FXML
    private RadioButton inHousePart;
    @FXML
    private RadioButton partOutsourced;
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

    private static Part partP= null;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       //setting the parts fields
        partP = MainController.partPass();
        partName.setText(partP.getName());
        partMin.setText(String.valueOf(partP.getMin()));
        partMax.setText(String.valueOf(partP.getMax()));
        partInv.setText(String.valueOf(partP.getStock()));
        partPrice.setText(String.valueOf(partP.getPrice()));

        //radio buttons selected to the right position/fill in correct fields.
        if(partP instanceof InHouse){
            inHousePart.setSelected(true);
            machineCompany.setText("Machine ID");
            machineIDCompName.setText(String.valueOf(((InHouse) partP).getMachineID()));
        }
        if (partP instanceof Outsourced) {
            partOutsourced.setSelected(true);
            machineCompany.setText("Company Name");
            machineIDCompName.setText(String.valueOf(((Outsourced) partP).getCompanyName()));
        }

    }
    public void backToMain(ActionEvent actionEvent) throws IOException {

        Parent partCancel = FXMLLoader.load(getClass().getResource("/sample/views/Main.fxml"));
        Scene scene = new Scene(partCancel);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    public void onInHouse(ActionEvent actionEvent) {
        machineCompany.setText("Machine ID");

    }
    public void onOutsourced(ActionEvent actionEvent) {
        machineCompany.setText("Company ID");

    }
    public void onSaveP(ActionEvent actionEvent) throws IOException {
        boolean validSave=true;
        try {
           partP.setName(partName.getText());
           partP.setMin(Integer.parseInt(partMin.getText()));
           partP.setMax(Integer.parseInt(partMax.getText()));
           partP.setPrice(Double.parseDouble(partPrice.getText()));
           partP.setStock(Integer.parseInt(partInv.getText()));


           if (partOutsourced.isSelected())
           {
               Outsourced part = new Outsourced(partP.getId(), partP.getName(), partP.getPrice(), partP.getStock(), partP.getMin(), partP.getMax(), machineIDCompName.getText());
               Inventory.addPart(part);
               Inventory.deletePart(partP);
           }
           if (inHousePart.isSelected())
           {
               InHouse part = new InHouse(partP.getId(), partP.getName(), partP.getPrice(), partP.getStock(), partP.getMin(), partP.getMax(), Integer.parseInt(machineIDCompName.getText()));
               Inventory.addPart(part);
               Inventory.deletePart(partP);
           }


        }
        catch (Exception e){
            validSave =false;
            errorMessages.setText(e.getMessage());
            System.out.println(e.getMessage());

        }
        if (validSave){
            Parent partCancel = FXMLLoader.load(getClass().getResource("/sample/views/Main.fxml"));
            Scene scene = new Scene(partCancel);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
    }
}
