import javazoom.jl.player.StdPlayer;

public class A {
      public static void main(String[] args) {
          StdPlayer.open();
          Wave A = new Wave(440.0, 2.0, .8);
          A.play();
          StdPlayer.close();
          System.exit(0);
      }
  }
