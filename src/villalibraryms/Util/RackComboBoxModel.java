package villalibraryms.Util;

import javax.swing.DefaultComboBoxModel;
import villalibraryms.Models.Rack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ayasnasih
 */
public class RackComboBoxModel  extends DefaultComboBoxModel<Rack> {
        public RackComboBoxModel(Rack[] items) {
        super(items);
    }
 
    @Override
    public Rack getSelectedItem() {
        Rack selectedJob = (Rack) super.getSelectedItem();
 
        // do something with this job before returning...
 
        return selectedJob;
    }
}
