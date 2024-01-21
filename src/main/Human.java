package main;

import java.awt.Color;
import java.awt.Graphics;

public class Human extends GameObject{

	public Human(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
	}
}
