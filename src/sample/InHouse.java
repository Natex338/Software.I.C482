package sample;

public class InHouse extends Part {

    /**
     * private variables
     */
    private int machineID;

    /**
     * Defautl constructors
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     */
    public InHouse(int id, String name, double price, int stock, int min, int max) {
        super(id, name, price, stock, min, max);
    }

    /**
     * Setters
     * @param machineID
     */
        public void setMachineID(int machineID) {
        this.machineID = machineID;
    }

    /**
     * getters
     * @return
     */
    public int getMachineID() {
        return machineID;
    }
}
