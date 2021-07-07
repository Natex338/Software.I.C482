package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class Inventory {
    public static int partIdCount=0;
    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();
        /**
     * Part count increments every part on add call..
     */
    public static void incrementPartId(){
       partIdCount++;
    }
    /**
     * @param newParts adding part to part list
     */
    public static void addPart(Part newParts){
      if(newParts !=null){
        allParts.add(newParts);
    }
  }
    /**
     * @param product Adding product to the product list
     */
    public static void addProduct(Product product){
      if(product !=null){
        allProducts.add(product);
    }
  }
    /**
     * @param productId looking up product part
     * @return Return part with ID
     */
    public static Product lookupProduct(int productId){
      for(Product p : allProducts)
   {
        if (p.getId() == productId)
         return p;
    }
      return null;
  }

    public static String validatePart( String name, double price, int stock, int min, int max, String exceptionMsg) {
        String message;



    return "g";
    }

    public static Product lookupProduct(String productName){
      for(Product p : allProducts)
        {
            if (p.getName() == productName)
                return p;
        }
      return null;
  }
    public static Part lookupPart(int partId){
      for(Part p : allParts){
          if(p.getId() == partId)
              return p;
      }
      return null;
  }
    public static Part lookupPart(String partName){
      for(Part p : allParts){
            if(p.getName() == partName)
                return p;
        }
      return null;

  }
    public static void updatePart(int index, Part selectedPart){
      allParts.set(index, selectedPart);
  }
    public static void updateProduct(int index, Product selectedProd){
     allProducts.set(index, selectedProd);
  }
    public static boolean deletePart(Part deletedPart){
      for(Part p : allParts ){
          if (deletedPart == p){
              allParts.remove(p);
              return true;

          }
      }
      return false;
  }
    public static boolean deleteProduct(Product deletedProd){
        for(Product p : allProducts ){
            if (deletedProd == p){
                allParts.remove(p);
                return true;
            }
        }
        return false;
    }
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }


}


