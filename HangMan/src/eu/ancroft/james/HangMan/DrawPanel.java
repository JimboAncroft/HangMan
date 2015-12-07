package eu.ancroft.james.HangMan;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class DrawPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9062427019063380537L;
	int x=100;
	int y=100;
	String s="";

	public void setX(int sX) {
		x = sX;
	}
	public void setY(int sY){
		y=sY;
	}
	public void setText(String str){
	s=str;	
		
	}
	public DrawPanel() // set up graphics window
	{
		super();
		setBackground(Color.WHITE);
		
	}
	 @Override
	public void paintComponent(Graphics g) // draw graphics in the panel
	{
		
		//int width = getWidth(); // width of window in pixels
		//int height = getHeight(); // height of window in pixels
	
		super.paintComponent(g); // call superclass to make panel display
									// correctly

	
		g.drawString("a", x, y);
		g.fillRect(200, 62, 30, 10);
		// Drawing code goes here
	}
	 
}
