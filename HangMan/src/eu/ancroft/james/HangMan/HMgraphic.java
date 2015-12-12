package eu.ancroft.james.HangMan;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class HMgraphic {

	HMpenCoords penCoords = new HMpenCoords();
	HMpenss EDC = new HMpenss();

	int Misses;
	boolean win = false;
	
	boolean lose=false;
	int[] Guessed;
	String Cword;
	Object[] letterGuess = new Object[1];
	String LG = ".";

	public void go() {
		// Uses fetchWord to open a word files and get a random word from it.
		fetchWord FW = new fetchWord();
		FW.NewWord();
		Cword = FW.getFetchWord();
		Guessed = new int[Cword.length()];
		String labels[] = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
				"s", "t", "u", "v", "w", "x", "y", "z" };

		System.out.println(Cword);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		frame.setSize(400, 600);

		MyDrawPanel drawPanel = new MyDrawPanel();
		drawPanel.setPreferredSize(new Dimension(400, 400));
		frame.getContentPane().add(drawPanel);

		Panel panel = new Panel();
		panel.setLayout(new FlowLayout());
		panel.setPreferredSize(new Dimension(400, 400));
		frame.getContentPane().add(panel);

		// An array of checkboxes
		// Create ActionListener for a checkbox array
		ActionListener actionListener = new ActionHandler();
		JCheckBox CheckBXArray[] = new JCheckBox[labels.length];
		for (int i = 0; i < labels.length; i++) {
			CheckBXArray[i] = new JCheckBox(labels[i]);
			CheckBXArray[i].addActionListener(actionListener);
			CheckBXArray[i].setEnabled(true);
			panel.add(CheckBXArray[i]);
		}

		frame.setVisible(true);

		int startPos = 0;
		int finalPos = 0;
		int currentPos = 0;
		String[] PreviousGuesses = new String[26];
		Component[] component = panel.getComponents();
		drawPanel.paint(drawPanel.getGraphics());
		while (!win) {
			LG = null;

			for (int i = 0; i < component.length; i++) {
				if (component[i] instanceof JCheckBox) {
					JCheckBox ChBx = (JCheckBox) component[i];

					if (ChBx.isSelected() && PreviousGuesses[i] == null) {

						LG = ChBx.getText();
						// Add current guess to the previous ones
						PreviousGuesses[i] = LG;

						startPos = 0;
						finalPos = 0;
						currentPos = 0;
						do {
							if (Cword.indexOf(LG, startPos) != -1) {

								Guessed[Cword.indexOf(LG, startPos)] = 1;
								finalPos = Cword.lastIndexOf(LG);
								currentPos = Cword.indexOf(LG, startPos);
								startPos = Cword.indexOf(LG, startPos) + 1;
								drawPanel.paint(drawPanel.getGraphics());

							} else {
								Misses++;
								drawPanel.paint(drawPanel.getGraphics());
							}

						} while (currentPos < finalPos);

					}

				}

			}

			win = true;
			for (int n = 0; n < Cword.length(); n++) {

				if (Guessed[n] == 0) {

					win = false;
					
				}
			}
			if (win) {
				drawPanel.paint(drawPanel.getGraphics());
				while(win){}
			}
			if (Misses > 9) {
				while (true) {
					lose=true;
					drawPanel.paint(drawPanel.getGraphics());
					while(lose){}

				}
			}

		}
	}

	class MyDrawPanel extends JPanel {

		private static final long serialVersionUID = -2237088330087604059L;

		public void paintComponent(Graphics g) {

			g.setColor(Color.blue);

			for (int n = 0; n < Misses; n++) {
				EDC = penCoords.getHMss(n);
				if (n == 5) {
					g.drawOval(EDC.x1, EDC.y1, EDC.x2, EDC.y2);

				} else {
					g.drawLine(EDC.x1, EDC.y1, EDC.x2, EDC.y2);
				}
			}

			int letterX = 100;
			int letterY = 300;
			int letterSpace = 10;
			for (int n = 0; n < Cword.length(); n++) {
				if (Guessed[n] == 1) {
					// Print a letter on Screen.
					g.drawString(Cword.substring(n, n + 1), letterX + (letterSpace * n), letterY);

				} else {
					// Print a dash.
					g.drawString("_", letterX + (letterSpace * n), letterY);
				}
			}
			if (win) {
				g.setColor(Color.GREEN);
				g.drawString("YOU WIN", 300, 300);
				

			}
			if (lose) {
				g.drawString("YOU LOSE", 300, 300);
				g.setColor(Color.RED);
				g.drawString("The word is: "+Cword, 100, 350);

			}
		}

	}

	public class fetchWord {
		String rWord;
		int NOL;
		int Rnumber;
		Random rand = new Random();
		ArrayList<String> lines = new ArrayList<String>();

		public fetchWord() {

			try (BufferedReader reader = new BufferedReader(new InputStreamReader(
					Thread.currentThread().getContextClassLoader().getResourceAsStream("words.txt")))) {
				for (String line = reader.readLine(); line != null; line = reader.readLine()) {
					lines.add(line);
					// System.out.println(line);
				}

			} catch (IOException e) { // TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		public void NewWord() {
			NOL = lines.size();
			Rnumber = rand.nextInt(NOL);
			rWord = lines.get(Rnumber);
		}

		public String getFetchWord() {
			return rWord;
		}
	}

}