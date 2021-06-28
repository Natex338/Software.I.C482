package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Model.InHouse;
import sample.Model.Inventory;
import sample.Model.Product;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        Product testProduct = new Product(5, "Test Product", 5.99, 5, 1, 5);
        InHouse testerInHouse = new InHouse(5, "Test In House Product", 5.99, 5, 1, 5, 5);
        Inventory.addProduct(testProduct);
        Inventory.addPart(testerInHouse);

        launch(args);






    }
}
