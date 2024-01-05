package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
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
		
	}
	public void updateEndState()  { 
		
	}
	public void drawMenuState(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, RainingTacos.WIDTH, RainingTacos.HEIGHT);
	}
	public void drawGameState(Graphics g) { 
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, RainingTacos.WIDTH, RainingTacos.HEIGHT);
	}
	public void drawEndState(Graphics g)  {
		g.setColor(Color.RED);
		g.fillRect(0, 0, RainingTacos.WIDTH, RainingTacos.HEIGHT);
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
