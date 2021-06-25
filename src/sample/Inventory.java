package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
  private static ObservableList<Part> allParts = FXCollections.observableArrayList();
  private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

  public static void addPart(Part newParts){
    if(newParts !=null){
      allParts.add(newParts);
    }
  }

  public static void addProduct(Product product){
    System.out.println("Before");
    if(product !=null){
      System.out.println("Im here");
      allProducts.add(product);
    }
    else System.out.println("I didnt make it");
  }
  public static Product lookupProduct(int productId){
   for(Product p : allProducts)
    {
     if (p.getId() == productId)
       return p;
   }
   return null;
  }
}


