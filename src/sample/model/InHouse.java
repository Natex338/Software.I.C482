package sample.model;

public class InHouse extends Part {

    /**
     * private variables
     */
    private int machineID;

    /**
     * Default constructors
     * @param id Part ID
     * @param name Name of the part
     * @param price price of part
     * @param stock number of parts in stock
     * @param min number of parts
     * @param max number of parts
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineID) {
        super(id, name, price, stock, min, max);
        this.machineID = machineID;
    }

    /**
     * Setters
     * @param machineID set Machine ID
     */
        public void setMachineID(int machineID) {
        this.machineID = machineID;
    }

    /**
     * getters
     * @return returns Machine ID
     */
    public int getMachineID() {
        return machineID;
    }
}
