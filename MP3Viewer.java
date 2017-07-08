/**********************************************************************************
 *  Author        Flavio Andrade
 *  Compilation:  javac -cp .:stdplayer.jar MP3Viewer.java
 *  Execution:    java  -cp .:stdplayer.jar MP3Viewer audio
 *  Dependencies: Wave.java
 *
 *  Visualizes the audio file.
 *
 *  Note:  under Windows, replace the : with a ; when specifying the classpath.
 *  Note:  stdplayer.jar must be located in the current directory.
 *
 **********************************************************************************/
import javazoom.jl.player.StdPlayer;
import java.lang.Math;

public class MP3Viewer {
	public static void main(String[] args) {
		double yscale = 300;
		String filename = args[0];
        StdPlayer.open(filename);
		StdDraw.setCanvasSize(576, 512);
		StdDraw.enableDoubleBuffering();
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.setXscale(-30, 1182);
		StdDraw.setYscale(-yscale, yscale);
		int i = 1;
        while (!StdPlayer.isEmpty()) {
            Wave w = new Wave(StdPlayer.getLeftChannel(), StdPlayer.getRightChannel());
			w.play();
			// Every other wave.
			if (i % 2 == 0) {
				w.draw(i / 2);
			}
			StdDraw.pause(10);
			i++;
        }
        StdPlayer.close();
        System.exit(0);
        }
	}
