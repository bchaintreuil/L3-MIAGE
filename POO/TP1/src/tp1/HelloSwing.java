package tp1;

public class HelloSwing {
	  public static void main(String[] args) {
	    new frame.FrameHelper(200, 200).draw(g -> {
	      g.drawString("Hello, World!", 0, 10);
	    });
	  }
	}