/**********************************************************************************
 *  
 *  Compilation:  javac -cp .:stdplayer.jar Wave.java
 *  Execution:    java  -cp .:stdplayer.jar Wave
 *  Dependencies: Wave.java
 *
 *  Creates a Wav object with two arrays that represent left and right audio
 *  audio channels. Both arrays contain audio samples.
 *
 *  Note:  under Windows, replace the : with a ; when specifying the classpath.
 *  Note:  stdplayer.jar must be located in the current directory.
 *
 **********************************************************************************/
import java.lang.Math;
import javazoom.jl.player.StdPlayer;
import java.awt.Font;


public class Wave {
	private double amplitude, hertz, duration;
	private int SPS = 44100, N;
	private double[] left;
	private double[] right;

	public Wave(double hz, double seconds, double ampl) {
		N = (int) (SPS * seconds);
		duration = seconds;
                hertz = hz;
		amplitude = ampl;
		left = new double[N];
		right = new double[N];
		for (int i = 0; i < N; i++) {
			double sample = amplitude * Math.sin(2 * Math.PI * hertz * i / 44100);
			left[i] = sample;
			right[i] = sample;
		}
	}

	/* Constructor to play more than one note at a time. */
	public Wave(double[] b_left, double[] b_right) {
		left = b_left; right = b_right;
	}

	/* Create individual notes and combine them. */
	public Wave plus(Wave b) {
		double[] this_left = new double[b.left.length];
		double[] this_right = new double[b.right.length];

		for (int i = 0; i < this_left.length; i++) {
			if (left[i] + b.left[i] > 1 || left[i] + b.left[i] < -1) {
				this_left[i] = Math.sin(left[i] + b.left[i]);
			}
			else {
				this_left[i] = left[i] + b.left[i];
			}
			if ((right[i] + b.right[i]) > 1 || (right[i] + b.right[i]) < - 1) {
				this_right[i] = Math.sin(right[i] + b.right[i]);
			}
			else {
				this_right[i] = right[i] + b.right[i];
			}
		}
		return new Wave(this_left, this_right);
	}

	public void play() {
		StdPlayer.playWave(left, right);
	}

	public void draw(int j) {
		StdDraw.clear();
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.text(200, 260, "Blue: LEFT CHANNEL");
		StdDraw.setPenColor(StdDraw.GREEN);
	        StdDraw.text(225, 200, "Green: RIGHT CHANNEL");
		for (int i = 0; i < left.length; i++) {
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.circle(i, Math.abs(left[i]) * 3, Math.abs(left[i] * 5));
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.circle(i, -Math.abs(right[i]) * 3, Math.abs(right[i] * 5));
		}
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.text(160, -260, "Waves:     " + Integer.toString(j));
		StdDraw.setPenColor(StdDraw.GREEN);
		StdDraw.text(160, -200, "Waves:     " + Integer.toString(j));
		StdDraw.show();
		//StdDraw.pause(10);
	}

	public static void main(String[] args) {
		double yscale = 400;
		int pause = 10;
		StdDraw.setCanvasSize(576, 512);
		StdDraw.enableDoubleBuffering();
		StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 16));
		StdDraw.setXscale(-30, 1182);
		StdDraw.setYscale(-yscale, yscale);
		String filename = args[0];
                StdPlayer.open(filename);
		int i = 1;
		while (!StdPlayer.isEmpty()) {
			Wave wave = new Wave(StdPlayer.getLeftChannel(), StdPlayer.getRightChannel());
			wave.play();
			wave.draw(i);
			i++;
		}
		StdPlayer.close();
		System.exit(0);
	}
}
