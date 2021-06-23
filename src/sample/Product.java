package sample;

public class Product {
  private int id;
  private String name;
  private double price;
  private int stock;
  private int min;
  private int max;

    /**
     * constructors
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     */
  public Product(int id, String name, double price, int stock, int min, int max){
    this.id=id;
    this.name=name;
    this.price=price;
    this.stock=stock;
    this.min=min;
    this.max=max;
  }

    /**
     * Getters
     * @return
     */
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getStock() {
        return stock;
    }
    public int getMin() {
        return min;
    }
    public int getMax() {
        return max;
    }

    /**
     * setters
     * @param id
     *
     * */

    public void setId(int id) {
        this.id = id;
    }
    public void setMax(int max) {
        this.max = max;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public void setMin(int min) {
        this.min = min;
    }






}
