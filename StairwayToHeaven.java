/**********************************************************************************
 *  Compilation:  javac -cp .:stdplayer.jar StairwayToHeaven.java
 *  Execution:    java  -cp .:stdplayer.jar StairwayToHeaven
 *  Dependencies: Wave.java
 *
 *  Note:  under Windows, replace the : with a ; when specifying the classpath.
 *  Note:  the file stdplayer.jar must be in the current directory
 *
 *  Creates the first part of Led Zeppelin's famous Stairway to Heaven using the
 *  Wave data type, and plays it through the sound card.
 *
 **********************************************************************************/

import javazoom.jl.player.StdPlayer;
import java.awt.Font;

public class StairwayToHeaven {
    public static void main(String[] args) {
        double amplitude = .4;
        StdPlayer.open();

        // Create the notes
        // A "delay", i.e., a wave without sound
        Wave delay = new Wave(0.0, 0.4, amplitude);

        Wave E3   = new Wave(659.26 / 4, 0.4, amplitude);
        Wave B4   = new Wave(493.88 / 2, 0.4, amplitude);
        Wave C4   = new Wave(523.25 / 2, 0.4, amplitude);
        Wave E4   = new Wave(659.26 / 2, 0.4, amplitude);
        Wave Gb4  = new Wave(739.99 / 2, 0.4, amplitude);
        Wave G4   = new Wave(783.99 / 2, 0.4, amplitude);
        Wave Gs4  = new Wave(830.61 / 2, 0.4, amplitude);
        Wave A5   = new Wave(440.00 / 1, 0.4, amplitude);
        Wave B5   = new Wave(493.88 / 1, 0.4, amplitude);
        Wave C5   = new Wave(523.25 / 1, 0.4, amplitude);
        Wave C5x2 = new Wave(523.25 / 1, 0.8, amplitude);
        Wave D5   = new Wave(587.33 / 1, 0.4, amplitude);
        Wave E5   = new Wave(659.26 / 1, 0.4, amplitude);
        Wave Gb5  = new Wave(739.99 / 1, 0.4, amplitude);
        Wave A6   = new Wave(440.00 * 2, 0.4, amplitude);
        Wave B6   = new Wave(493.88 * 2, 0.4, amplitude);
        Wave C6   = new Wave(523.25 * 2, 0.4, amplitude);

        // create the combo-notes
        Wave GsB = B6.plus(Gs4);
        Wave GC  = G4.plus(C6);
        Wave GbG = Gb4.plus(Gb5);
        Wave EE  = E4.plus(E5);
        Wave GB  = G4.plus(B5);
        Wave AC  = A5.plus(C5);
        Wave AClong = new Wave(440.0, .8, amplitude).plus(new Wave(523.3, .8, amplitude));

        // play twice
        for (int i = 0; i < 2; i++) {
            A5.play();
            C5.play();
            E5.play();
            A6.play();

            GsB.play();
            E5.play();
            C5.play();
            B6.play();

            GC.play();
            E5.play();
            C5.play();
            C6.play();

            GbG.play();
            D5.play();
            A5.play();
            Gb5.play();

            EE.play();
            C5.play();
            A5.play();
            C5x2.play();

            E5.play();
            C5.play();
            A5.play();

            GB.play();
            AC.play();
            AClong.play();

            delay.play();
            E3.play();
            C4.play();
            B4.play();
        }

        StdPlayer.close();
        System.exit(0);
    }
}
