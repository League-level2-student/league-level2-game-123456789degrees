package main;

import javax.swing.JFrame;

public class RainingTacos {
	JFrame frame;
	GamePanel panel;
	public static final int WIDTH = 700;
	public static final int HEIGHT = 1000;
	public RainingTacos() {
		frame = new JFrame();
		panel = new GamePanel();
	}
	public static void main (String[] args) {
		RainingTacos game = new RainingTacos();
		game.setup();
	}
	public void setup() {
		frame.setVisible(true);
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(panel);
		frame.add(panel);
	}
}
