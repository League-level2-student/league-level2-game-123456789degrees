package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
//you better sign out of git next time or else 
//you better sign out of git next time or else 
//you better sign out of git next time or else 
//you better sign out of git next time or else 
//you better sign out of git next time or else 
//you better sign out of git next time or else 
//you better sign out of git next time or else 
//you better sign out of git next time or else 
//you better sign out of git next time or else 
//you better sign out of git next time or else 
//you better sign out of git next time or else 
//you better sign out of git next time or else 
//you better sign out of git next time or else 
//you better sign out of git next time or else 
//you better sign out of git next time or else 
//you better sign out of git next time or else 
//you better sign out of git next time or else 
//you better sign out of git next time or else 
//you better sign out of git next time or else 
//you better sign out of git next time or else 
//you better sign out of git next time or else 
//you better sign out of git next time or else 
//you better sign out of git next time or else 
//you better sign out of git next time or else 
//you better sign out of git next time or else 
//you better sign out of git next time or else 
//you better sign out of git next time or else 
//you better sign out of git next time or else 
//you better sign out of git next time or else 
//you better sign out of git next time or else 
//you better sign out of git next time or else 
//you better sign out of git next time or else 
//you better sign out of git next time or else 
v
	//you better sign out of git next time or else 
	//you better sign out of git next time or else 
	//you better sign out of git next time or else 
	//you better sign out of git next time or else 
	//you better sign out of git next time or else 
	//you better sign out of git next time or else 
	//you better sign out of git next time or else 
	//you better sign out of git next time or else 
	//you better sign out of git next time or else 
	
public class Human extends GameObject{
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	public Human(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		speed = 10;
		if (needImage) {
		    loadImage ("human.png");
		}
	}
	public void draw(Graphics g) {
        if (gotImage) {
        	g.drawImage(image, x, y, width, height, null);
        } else {
        	g.setColor(Color.BLUE);
        	g.fillRect(x, y, width, height);
        }
	}
	public void right() {
        x+=speed;
    }
	public void left() {
        x-=speed;
    }
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
}
