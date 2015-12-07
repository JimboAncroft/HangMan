package eu.ancroft.james.HangMan;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class HMgraphic {

	HMpenCoords penCoords = new HMpenCoords();
	HMpenss EDC = new HMpenss();
	JFrame frame = new JFrame();

	int Misses;
	boolean EOG = false;
	int[] Guessed;
	String Cword;

	public void go() {
		// Uses fetchWord to open a word files and get a random word from it.

		fetchWord FW = new fetchWord();
		FW.NewWord();
		Cword = FW.getFetchWord();
		Guessed = new int[Cword.length()];

		System.out.println(FW.getFetchWord());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MyDrawPanel drawPanel = new MyDrawPanel();
		frame.getContentPane().add(drawPanel);
		frame.setSize(500, 500);
		frame.setVisible(true);
		
		frame.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		drawPanel.setPreferredSize(new Dimension(400, 400));
		frame.add(drawPanel);
		frame.setVisible(true);
		frame.setSize(400, 700); // window is 500 pixels wide, 400 high
		
		
		
		
		// CheckboxES
		String labels[] = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
				"s", "t", "u", "v", "w", "x", "y", "z" };
		for (int i = 0; i < labels.length; i++) {
			JCheckBox checkbox = new JCheckBox(new CheckboxAction(labels[i]));
			frame.add(checkbox);
			

		}


		while (!EOG) {
			JFrame frame = new JFrame();
			
			
			/**
			 * Need to get guessed letter into letterGuess.
			 */
			
			
			
			
			int startPos = 0;
			int finalPos = 0;
			int currentPos = 0;
			do {

				// if (Cword.indexOf(text) != -1) {
				if (Cword.indexOf(letterGuess, startPos) != -1) {

					Guessed[Cword.indexOf(letterGuess, startPos)] = 1;
					drawPanel.paint(drawPanel.getGraphics());
					finalPos = Cword.lastIndexOf(letterGuess);
					currentPos = Cword.indexOf(letterGuess, startPos);

					startPos = Cword.indexOf(letterGuess, startPos) + 1;

				} else {
					Misses++;
					drawPanel.paint(drawPanel.getGraphics());
				}

			} while (finalPos != currentPos);

			for (int n = 0; n < Cword.length(); n++) {
				EOG = true;
				if (Guessed[n] == 0) {
					EOG = false;
				}
			}

		}

	}

	class MyDrawPanel extends JPanel {

		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g) {

			g.setColor(Color.black);

			for (int n = 0; n < Misses; n++) {
				EDC = penCoords.getHMss(n);
				if (n == 5) {
					g.drawOval(EDC.x1, EDC.y1, EDC.x2, EDC.y2);

				} else {
					g.drawLine(EDC.x1, EDC.y1, EDC.x2, EDC.y2);
				}
			}
			// g.drawString(Cword.substring(0), 100, 100);

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

		}

	}

	public class fetchWord {
		String rWord;
		int NOL;
		int Rnumber;
		Random rand = new Random();
		List<String> lines = null;

		public fetchWord() {
			try {
				lines = Files.readAllLines(Paths.get("words.txt"), Charset.defaultCharset());
			} catch (IOException e1) {

				e1.printStackTrace();
			}

			// On the first run get a new word
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