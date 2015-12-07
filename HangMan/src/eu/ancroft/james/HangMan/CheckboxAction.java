package eu.ancroft.james.HangMan;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JCheckBox;

class CheckboxAction extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8770879135505628294L;
	String letterGuess;
	public CheckboxAction(String text) {
	
		super(text);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JCheckBox cbLog = (JCheckBox) e.getSource();
		if (cbLog.isSelected()) {
			cbLog.setEnabled(false);
			System.out.println("You chose "+cbLog.getText());
			letterGuess = cbLog.getText();
		} else {
			
		}
	}
	public String getLetterChoice()
	{
		
		return letterGuess;
		
	}
}
