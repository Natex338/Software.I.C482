package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class Inventory {
    //Part count is a static variable to increment everytime a part or product is added
    public static int partIdCount=0;

    //Observable lists for holding both Parts and products
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

    /**
     * Validate Part or Prodcut values
     * @param name Name of the product or part cannot be null
     * @param price price must be higher than zero
     * @param stock stock cannot be higher than Max, and no lower than min
     * @param min must be lower than max and lower than stock
     * @param max must be higher than stock and min
     * @return returns the error messages for values that are not correct.
     */
    public static String validatePart( String name, double price, int stock, int min, int max) {
        String message = "";
        if(name.isEmpty()){
            message+="Please Enter Name\n";
        }
        if (price<=0){
            message+="Price must be greater than zero\n";
        }
        if (stock < min ||stock > max) {
            message += "Inventory must be less than Max and greater than Min\n";
            }
        if (max < min) {
            message += "Max must be greater than min\n";
        }
        if (min < 0) {
            message += "Min must be greater than Zero\n";
        }
        if (stock<1){
            message += "Inventory must be greater than zero\n";
        }
    return message;
    }

    /** look up product by name
     * @param productName product name being searched
     * @return return the product with the same name else null
     */
    public static Product lookupProduct(String productName){
        for(Product p : allProducts)
        {
            if (p.getName().contains(productName))
                return p;
        }
      return null;
  }

    /**
     * Look up part by ID
     * @param partId  Part ID being searched
     * @return  return Part if ID matches else return null
     */
    public static Part lookupPart(int partId){
      for(Part p : allParts){
          if(p.getId() == partId)
              return p;
      }
      return null;
  }

    /**
     * @param partName look up specific part by name
     * @return return part with the same name
     */
    public static Part lookupPart(String partName){
      for(Part p : allParts){
            if(p.getName().contains(partName))
                return p;
        }
      return null;

  }

    /**
     * @param index of the part that will be updated
     * @param selectedPart the part that will be updated.
     */
    public static void updatePart(int index, Part selectedPart){
      allParts.set(index, selectedPart);
  }

    /**
     * @param index index of part that needs to be update
     * @param selectedProd update part at index in all products array.
     */
    public static void updateProduct(int index, Product selectedProd){
     allProducts.set(index, selectedProd);
  }

    /**
     * @param deletedPart delete selected part
     * @return return true if part is deleted false if not
     */
    public static boolean deletePart(Part deletedPart){
      for(Part p : allParts ){
          if (deletedPart == p){
              allParts.remove(p);
              return true;
          }
      }
      return false;
  }

    /**
     * @param deletedProd deleted product selected
     * @return true or false depending on deletion
     */
    public static boolean deleteProduct(Product deletedProd){
        for(Product p : allProducts ){
            if (deletedProd == p){
                allParts.remove(p);
                return true;
            }
        }
        return false;
    }

    /**
     * @return All parts from the all parts Observable list
     */
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    /**
     * get all products form the the Observable list
     * @return all products in product list
     */
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }
}


