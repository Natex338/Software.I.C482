
package sample.Model;

public class Outsourced extends Part {
    /**
     * Private variables
     */

    private String companyName;

    /**
     * Constructors
     *
     * @param id Part ID
     * @param name name of the part
     * @param price price of part
     * @param stock how many in stock
     * @param min min int
     * @param max max int
     */

    public Outsourced(int id,String name,Double price,int stock, int min, int max, String companyName) {
        super(id,name, price, stock,min, max);
        this.companyName = companyName;
    }






    /**
     * Getters and Setters
     * @param companyName set and retrieve private company name
     */

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }
}


