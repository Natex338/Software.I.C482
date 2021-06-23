
package sample;
public class Outsourced extends Part {
    /**
     * Private variables
     */

    private String companyName;

    /**
     * Constructors
     *
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     */

    public Outsourced(int id, String name, double price, int stock, int min, int max) {
        super(id, name, price, stock, min, max);
    }

    /**
     * Getters and Setters
     * @param companyName
     */

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }
}


