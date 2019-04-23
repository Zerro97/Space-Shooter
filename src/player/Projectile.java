package player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import manager.GameObject;
import manager.Handler;
import manager.ID;
import screen.GameMap;
import screen.HUD;

public class Projectile extends GameObject{
	private Handler handler;
	private GameObject player;
	private int damage;
	
	public Projectile(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		for(int i = 0; i < handler.list.size(); i++) {
			if(handler.list.get(i).getId() == ID.Player) {
				player = handler.list.get(i);
			}
		}
		
		width = 3;
		height = 3;
		
		damage = 1;
	}
	
	public void update() {
		if (velX == 0 && velY == 0) {
			velX = (float) Math.sin(player.getAngle()) * 4;
			velY = -(float) Math.cos(player.getAngle()) * 4;
		}
		
		x += velX;
		y += velY;
		
		if(GameMap.outOfBound(x, y)) handler.removeObject(this);

		collision();
	}

	public void render(Graphics g) {
		g.setColor(Color.cyan);
		g.fillOval((int)x, (int)y, width, height);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, width, height);
	}
	
	private void collision() {
		for(int i = 0; i<handler.list.size(); i++) {
			GameObject tempObject = handler.list.get(i);
			
			if(tempObject.getId() == ID.Enemy) {
				if(getBounds().intersects(tempObject.getBounds())) {
					tempObject.setHealth(tempObject.getHealth() - damage);
					handler.removeObject(this);
				}
			}
		}
	}
}
