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
public class Subject {

    public int id;
    public String name;

    public Subject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
