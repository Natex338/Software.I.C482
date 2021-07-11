package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Product {
    private ObservableList<Part> associatedParts= FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;


    /**
     * Product Constructor
     * @param id set product ID
     * @param name set product name
     * @param price set product price
     * @param stock set inventory amount
     * @param min set min of product
     * @param max set max of product
     */
    public Product(int id, String name, double price, int stock, int min, int max){

       this.id = id;
       this.name = name;
       this.price = price;
       this.stock = stock;
       this.min = min;
       this.max = max;

   }

    /**
     * @return product ID
     */
    public int getId() {
        return id;
    }

    /**
     * @param id set product ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return get product name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name set product name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return product price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price set product price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return get product stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock set inventory value.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return get min value of product
     */
    public int getMin() {
        return min;
    }

    /**
     * @param min set min value of product
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @return get max value from product
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max set Max value on product
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * @param p add associated part to product
     */
    public void addAssociatedPart(Part p){
       associatedParts.add(p);
    }

    /**
     * @param p deleted associated part from product
     * @return true if part is removed
     */
    public boolean deleteAssociatedPart(Part p){



        associatedParts.remove(p);
            return true;
    }

    /**
     * @return get all parts associated with product
     */
    public ObservableList<Part> getAllAssociatedParts(){

        return associatedParts;
    }
}
