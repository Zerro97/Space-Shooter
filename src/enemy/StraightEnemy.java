package enemy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import manager.GameObject;
import manager.Handler;
import manager.ID;

public class StraightEnemy extends GameObject {
	// Player
	private GameObject player;

	// Handler
	private Handler handler;

	// Timer
	private int timer;

	private boolean moveSwitch;

	public StraightEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);

		this.handler = handler;

		for (int i = 0; i < handler.list.size(); i++) {
			if (handler.list.get(i).getId() == ID.Player) {
				player = handler.list.get(i);
			}
		}

		width = 16;
		height = 16;

		health = 2;
	}

	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect((int) x, (int) y, width, height);
	}

	public void update() {
		if (timer >= 80) {
			moveSwitch = !moveSwitch;

			if (moveSwitch) {
				if (player.getX() > x) {
					velX = 7;
				} else {
					velX = -7;
				}
			} else {
				if (player.getY() > y) {
					velY = 7;
				} else {
					velY = -7;
				}
			}

			timer = 0;
		}
		timer++;

		x += velX;
		y += velY;
		
		if (Math.abs(player.getX() - x) < 8) {
			velX = 0;
		}
		if (Math.abs(player.getY() - y) < 8) {
			velY = 0;
		}

		if (health <= 0) {
			handler.removeObject(this);
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, width, height);
	}
}
