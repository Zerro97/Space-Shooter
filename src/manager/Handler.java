package manager;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	public LinkedList<GameObject> list = new LinkedList<GameObject>();

	public void render(Graphics g) {
		for (int i = 0; i < list.size(); i++) {
			GameObject temp = list.get(i);
			temp.render(g);
		}
	}

	public void update() {
		for (int i = 0; i < list.size(); i++) {
			GameObject temp = list.get(i);
			temp.update();
		}
	}

	public void addObject(GameObject object) {
		list.add(object);
	}

	public void removeObject(GameObject object) {
		list.remove(object);
	}

	public void clearEnemy() {
		for (int i = 0; i < list.size(); i++) {
			GameObject temp = list.get(i);
			if (temp.id == ID.Enemy) {
				removeObject(temp);
			}
		}
	}
}
