package eu.ancroft.james.HangMan;

public class HMpenCoords {

	HMpenss penSS = new HMpenss();

	int[] HMx1 = new int[10];
	int[] HMy1 = new int[10];
	int[] HMx2 = new int[10];
	int[] HMy2 = new int[10];

	public HMpenCoords() {
		// Base
		HMx1[0] = 50;
		HMy1[0] = 250;
		HMx2[0] = 250;
		HMy2[0] = 250;
		// Pole
		HMx1[1] = 150;
		HMy1[1] = 250;
		HMx2[1] = 150;
		HMy2[1] = 50;
		// Support
		HMx1[2] = 150;
		HMy1[2] = 75;
		HMx2[2] = 175;
		HMy2[2] = 50;
		// Cross Pole
		HMx1[3] = 150;
		HMy1[3] = 50;
		HMx2[3] = 300;
		HMy2[3] = 50;
		// Rope
		HMx1[4] = 300;
		HMy1[4] = 50;
		HMx2[4] = 300;
		HMy2[4] = 75;

		// Head "Draw Oval"
		HMx1[5] = 290;
		HMy1[5] = 75;
		HMx2[5] = 20;
		HMy2[5] = 20;

		// Body
		HMx1[6] = 300;
		HMy1[6] = 95;
		HMx2[6] = 300;
		HMy2[6] = 135;

		// Arms
		HMx1[7] = 270;
		HMy1[7] = 100;
		HMx2[7] = 330;
		HMy2[7] = 100;

		// Left leg
		HMx1[8] = 300;
		HMy1[8] = 135;
		HMx2[8] = 270;
		HMy2[8] = 160;
		// Right leg
		HMx1[9] = 300;
		HMy1[9] = 135;
		HMx2[9] = 330;
		HMy2[9] = 160;

	}

	public HMpenss getHMss(int z) {

		penSS.x1 = HMx1[z];
		penSS.y1 = HMy1[z];
		penSS.x2 = HMx2[z];
		penSS.y2 = HMy2[z];

		return penSS;
	}

}
