package eu.ancroft.james.HangMan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

class ActionHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent event) {
    	
        JCheckBox checkbox = (JCheckBox) event.getSource();
        if (checkbox.isEnabled()){checkbox.setEnabled(false);}
        
        
    }

}