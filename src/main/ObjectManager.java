package main;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
public class ObjectManager implements ActionListener{
	Human h;
	Random random = new Random();
	public ObjectManager(Human h) {
		this.h = h;
	}
	ArrayList<Taco> tacos = new ArrayList<Taco>();
	ArrayList<Rock> rocks = new ArrayList<Rock>();
	public void addTaco() {
		tacos.add(new Taco(random.nextInt(RainingTacos.WIDTH),0,50,50));
	}
	public void addRock() {
		rocks.add(new Rock(random.nextInt(RainingTacos.WIDTH),0,50,50));
	}
	public void update() {
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
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		addTaco();
	}
}
