package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	int score;
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	Font titleFont;
	Font regFont;
	Timer frameDraw;
	Human gamer = new Human(350, 800, 50,100); 
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	Timer alienSpawn;
	ObjectManager manager = new ObjectManager(gamer);
	public GamePanel() {
	    titleFont = new Font("Arial", Font.BOLD, 48);
	    regFont = new Font("Arial", Font.PLAIN, 30);
	    frameDraw = new Timer(1000/60, this);
	    frameDraw.start();
	    if (needImage) {
	        loadImage ("sky.jpeg");
	    }
	}
	public void startGame() {
		alienSpawn = new Timer(1000, manager);
		alienSpawn.start();
	}
	@Override
	public void paintComponent(Graphics g){
		if(currentState == MENU){
		    drawMenuState(g);
		}
		else if(currentState == GAME){
		    drawGameState(g);
		}
		else if(currentState == END){
		    drawEndState(g);
		}
	}
	public void updateMenuState() {  
		
	}
	public void updateGameState() { 
		manager.update();
	}
	public void updateEndState()  { 
		
	}
	public void drawMenuState(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, RainingTacos.WIDTH, RainingTacos.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.RED);
		g.drawString("IT'S RAINING TACOS!", 60, 100);
		g.setFont(regFont);
		g.drawString("PRESS ENTER TO START", 150, 360);
		g.drawString("PRESS SPACE FOR INSTRUCTIONS", 100, 540);
	}
	public void drawGameState(Graphics g) { 
		if (gotImage) {
			g.drawImage(image, 0, 0, RainingTacos.WIDTH, RainingTacos.HEIGHT, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, RainingTacos.WIDTH, RainingTacos.HEIGHT);
		}
		manager.draw(g);
	}
	public void drawEndState(Graphics g)  {
		g.setColor(Color.RED);
		g.fillRect(0, 0, RainingTacos.WIDTH, RainingTacos.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.GREEN);
		g.drawString("GAME OVER", 175, 100);
		g.setFont(regFont);
		g.drawString("You ate " + score + " tacos.", 215, 360);
		g.drawString("PRESS ENTER TO RESTART", 140, 540);
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END) {
		        currentState = MENU;
		    }
		    else if (currentState == MENU) {
		    	startGame();
		    }
		    else {
		    	alienSpawn.stop();
		        currentState++;
		    }
		}
		if (currentState == GAME) {
			if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
			   	if (gamer.x < 640) {
			   		gamer.right();
			   	}
			}
			if (e.getKeyCode()==KeyEvent.VK_LEFT) {
			    if (gamer.x > 10) {
			    	gamer.left();
			    }
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(currentState == MENU){
		    updateMenuState();
		}else if(currentState == GAME){
		    updateGameState();
		}else if(currentState == END){
		    updateEndState();
		}
		System.out.println("action");
		repaint();
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
