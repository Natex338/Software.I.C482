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
    public TextField partIDField;
    public RadioButton partOutsourced;
    public Label errorMessages;
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

    /**
     * @param actionEvent go back to main screen
     * @throws IOException throw error if cant find fmxl
     */
    public void toMain(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sample/views/Main.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param actionEvent set text field to Machine ID if Inhouse is selected.
     */
    public void onInHouse(ActionEvent actionEvent) {
        machineCompany.setText("Machine ID");
    }

    /**
     * @param actionEvent set text field to company name if outsourced is selected.
     */
    public void onOutsourced(ActionEvent actionEvent) {
        machineCompany.setText("Company Name");
    }

    /**
     * @param actionEvent save part
     * @throws IOException throw error if entery is invalid
     */
    public void onSaveP(ActionEvent actionEvent) throws IOException {
        String errorMessage = "";
        boolean validSave = true;
        try {
            String pName = partName.getText();
            int pMin = Integer.parseInt(partMin.getText());
            int partID = Inventory.partIdCount;
            int pInv = Integer.parseInt(partInv.getText());
            int pMax = Integer.parseInt(partMax.getText());
            double pPrice = Double.parseDouble(partPrice.getText());
            errorMessage += Inventory.validatePart(pName, pPrice, pInv, pMin, pMax);

            if (!errorMessage.isEmpty())
            {
                validSave = false;
            } else if (partOutsourced.isSelected()) {
                Outsourced part = new Outsourced(partID, pName, pPrice, pInv, pMin, pMax, machineIDCompName.getText());
                Inventory.addPart(part);
            } else if (inHousePart.isSelected()) {
                InHouse part = new InHouse(partID, pName, pPrice, pInv, pMin, pMax, Integer.parseInt(machineIDCompName.getText()));
                Inventory.addPart(part);
            }
            else
            {
                validSave=false;
                errorMessage+="Please select InHouse or Outsourced";
            }
            errorMessages.setText(errorMessage);
        }
        catch (Exception e)
        {
            validSave = false;
            errorMessage += "ERROR: Please correct\n" + e.getMessage().toLowerCase() + "\n to a valid entry for the field.";
            errorMessages.setText(errorMessage);
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
}
