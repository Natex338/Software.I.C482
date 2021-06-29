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
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 898, 398));
        primaryStage.show();
    }


    public static void main(String[] args) {
        Product testProduct1 = new Product(1, "Test Product1", 5.99, 1, 1, 1);
        InHouse testerInHouse1 = new InHouse(1, "Test In House Product1", 1, 1, 1, 1, 1);
        Product testProduct2 = new Product(2, "Test Product2", 2, 2, 1, 5);
        InHouse testerInHouse2 = new InHouse(2, "Test In House Product2", 5.99, 5, 1, 5, 5);
        Product testProduct3 = new Product(3, "Test Product3", 5.99, 5, 1, 5);
        InHouse testerInHouse3 = new InHouse(3, "Test In House Product3", 5.99, 5, 1, 5, 5);
        Product testProduct4 = new Product(4, "Test Product4", 5.99, 5, 1, 5);
        InHouse testerInHouse4 = new InHouse(4, "Test In House Product4", 5.99, 5, 1, 5, 5);

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
