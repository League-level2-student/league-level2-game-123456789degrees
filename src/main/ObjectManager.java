package main;
import java.awt.Graphics;
import java.applet.AudioClip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

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
					playSound("rockhit.wav");
				}
			}
			for (Taco t : tacos) {
				if (h.collisionBox.intersects(t.collisionBox)) {
					t.isActive = false;
					score++;
					playSound("chomp.wav");
				}
			}
		}
	}
	private void playSound(String soundFile) {
		String path = "src/main/";
			File sound = new File(path+soundFile);
			if (sound.exists()) {
				new Thread(() -> {
				try {
					Clip clip = AudioSystem.getClip();
					clip.open(AudioSystem.getAudioInputStream(sound));
					clip.start();
					Thread.sleep(clip.getMicrosecondLength()/1000);
				}
				catch (Exception e) {
					System.out.println("Could not play this sound");
				}}).start();
	 		}
			else {
				System.out.println("File does not exist");
			}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		addTaco();
		addRock();
	}
}
