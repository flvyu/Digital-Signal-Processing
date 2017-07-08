


/**********************************************************************************
 *  
 *  Compilation:  javac -cp .:stdplayer.jar EchoFilter.java
 *  Execution:    java  -cp .:stdplayer.jar EchoFilter songs/pearlharbor.mp3  delay
 *  Dependencies: Wave.java
 *
 *  Applys an echo filter to an mp3 audio file.
 *
 *  Note:  under Windows, replace the : with a ; when specifying the classpath.
 *  Note:  stdplayer.jar must be located in the current directory.
 *
 **********************************************************************************/


import javazoom.jl.player.StdPlayer;

public class EchoFilter {
	/* Add sound wave at time t - 10 to sound wav at time t
	   add the Wave that was originally read 10 waves ago to the current Wave
	*/
	public static void main(String[] args) {
		int j = 1;
		StdPlayer.open(args[0]);
		int delay = Integer.parseInt(args[1]);
		Wave[] last_x = new Wave[delay];
		int step = 0;
		while (!StdPlayer.isEmpty()) {
			if (last_x[delay - 1] == null) {
				for (int i = 0; i < delay; i++) {
					double[] left  = StdPlayer.getLeftChannel();
					double[] right = StdPlayer.getRightChannel();
					Wave w = new Wave(left, right);
					last_x[i] = w;
				}
			}
			double[] left  = StdPlayer.getLeftChannel();
			double[] right = StdPlayer.getRightChannel();
			Wave w = new Wave(left, right);
			Wave echo = last_x[step].plus(w);
			echo.play();
			last_x[step] = w;
			if (step == delay - 1) { step = 0; }
			else { step++; }
		}
		StdPlayer.close();
		System.exit(0);
	}
}
