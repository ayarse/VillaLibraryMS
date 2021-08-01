/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
