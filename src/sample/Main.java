package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.model.InHouse;
import sample.model.Inventory;
import sample.model.Product;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("views/Main.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        Product testProduct1 = new Product(11, "Test ProductOne", 5.99, 1, 1, 1);
        InHouse testerInHouse1 = new InHouse(12, "Test In House ProductOne", 1, 1, 1, 1, 1);
        Product testProduct2 = new Product(13, "Test ProductTWO", 2, 2, 1, 5);
        InHouse testerInHouse2 = new InHouse(14, "Test In House ProductTWO", 5.99, 5, 1, 5, 5);
        Product testProduct3 = new Product(355, "Test ProductThree", 5.99, 5, 1, 5);
        InHouse testerInHouse3 = new InHouse(213, "Test In House ProductThree", 5.99, 5, 1, 5, 5);
        Product testProduct4 = new Product(41, "Test ProductFour", 5.99, 5, 1, 5);
        InHouse testerInHouse4 = new InHouse(44, "Test In House ProductFour", 5.99, 5, 1, 5, 5);

        Inventory.addProduct(testProduct1);
        Inventory.addPart(testerInHouse1);
        Inventory.addProduct(testProduct2);
        Inventory.addPart(testerInHouse2);
        Inventory.addProduct(testProduct3);
        Inventory.addPart(testerInHouse3);
        Inventory.addProduct(testProduct4);
        Inventory.addPart(testerInHouse4);

        launch(args);






    }
}
