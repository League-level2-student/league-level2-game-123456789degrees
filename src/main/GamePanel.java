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
	int currentState2 = 0;
	Font titleFont;
	Font regFont;
	Timer frameDraw;
	Human gamer = new Human(350, 800, 50,100); 
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	public static Timer alienSpawn;
	public static Timer rockSpawn;
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
		rockSpawn = new Timer(500, manager);
		rockSpawn.start();
	}
	@Override
	public void paintComponent(Graphics g){
		if(currentState == MENU){
		    drawMenuState(g);
		    if (currentState2 == 1) {
		    	drawDirections(g);
		    }
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
		if (!gamer.isActive) {
			currentState = END;
		}
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
		g.setColor(Color.RED);
		g.setFont(regFont);
		g.drawString("Score: " + manager.getScore(), 25, 25);
		manager.draw(g);
	}
	public void drawEndState(Graphics g)  {
		g.setColor(Color.RED);
		g.fillRect(0, 0, RainingTacos.WIDTH, RainingTacos.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.GREEN);
		g.drawString("GAME OVER", 175, 100);
		g.setFont(regFont);
		g.drawString("You ate " + manager.getScore() + " tacos.", 215, 360);
		g.drawString("PRESS ENTER TO RESTART", 140, 540);
	}
	public void drawDirections(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(0, 0, RainingTacos.WIDTH, RainingTacos.HEIGHT);
		g.setFont(regFont);
		g.setColor(Color.BLUE);
		g.drawString("Use arrow keys to move right and left.", 100, 300);
		g.drawString("Try to eat as many tacos as you can. ", 115, 330);
		g.drawString("Don't get hit by the rocks!", 180, 360);
		g.drawString("Press SPACE to go back. ", 180, 400);
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
		    	gamer = new Human(350, 800, 50,100);
		    	manager = new ObjectManager(gamer);
		        currentState = MENU;
		        Rock.speed = 5;
		    }
		    else if (currentState == MENU) {
		    	currentState = GAME;
		    	startGame();
		    }
		    else {
		    	rockSpawn.stop();
		    	alienSpawn.stop();
		        currentState++;
		    }
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			currentState2++;
			if (currentState2 > 1) {
				currentState2 = 0;
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
