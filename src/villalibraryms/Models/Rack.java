/*
 * Library Management System - Ayas Nasih, S1600655
 * 
 * 
 */
package villalibraryms.Models;

/**
 *
 * @author ayasnasih
 */
public class Rack {

    public Rack(int id, String location) {
        this.id = id;
        this.location = location;
    }
    public int id;
    public String location;
    
    @Override
    public String toString() {
     return this.location;
    }
}
