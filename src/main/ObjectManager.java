package main;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import game_tools.Sound;
public class ObjectManager implements ActionListener{
	Human h;
	Random random = new Random();
	int score = 0;
	public ObjectManager(Human h) {
		this.h = h;
	}
	ArrayList<Taco> tacos = new ArrayList<Taco>();
	ArrayList<Rock> rocks = new ArrayList<Rock>();
	public int getScore() {
		return score;
	}
	public void addTaco() {
		tacos.add(new Taco(random.nextInt(RainingTacos.WIDTH),0,50,50));
	}
	public void addRock() {
		rocks.add(new Rock(random.nextInt(RainingTacos.WIDTH),0,50,50));
	}
	public void update() {
		if (h.isActive) {
			for (Taco t : tacos) {
				t.update();
				if (t.y > RainingTacos.HEIGHT) {
					t.isActive = false;
				}
			}
			for (Rock r : rocks) {
				r.update();
				if (r.y > RainingTacos.HEIGHT) {
					r.isActive = false;
				}
			}
			h.update();
			checkCollision();
			purgeObjects();
		}
	}
	public void draw(Graphics g) {
		h.draw(g);
		for (Taco t : tacos) {
			t.draw(g);
		}
		for (Rock r : rocks) {
			r.draw(g);
		}
	}
	public void purgeObjects() {
		if (h.isActive) {
			for (int i = 0; i < rocks.size(); i++) {
				if (!rocks.get(i).isActive) {
					rocks.remove(i);
				}
			}
			for (int i = 0; i < tacos.size(); i++) {
				if (!tacos.get(i).isActive) {
					tacos.remove(i);
				}
			}
		}
	}
	public void checkCollision() {
		if (h.isActive) {
			for (Rock r : rocks) {
				if (h.collisionBox.intersects(r.collisionBox)) {
					h.isActive = false;
					r.isActive = false;
				}
			}
			for (Taco t : tacos) {
				if (h.collisionBox.intersects(t.collisionBox)) {
					t.isActive = false;
					score++;
				}
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		addTaco();
		addRock();
	}
}
