
package sample;
public class Outsourced extends Part  {
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

    public Outsourced(int id,String name,Double price,int stock, int min, int max, String companyName) {
        super(id,name, price, stock,min, max);
        this.companyName = companyName;
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


