package enemy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import manager.GameObject;
import manager.Handler;
import manager.ID;

public class HugeEnemy extends GameObject{
	// Player
	private GameObject player;
	
	// Handler
	private Handler handler;
	
	public HugeEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;

		for(int i = 0; i < handler.list.size(); i++) {
			if(handler.list.get(i).getId() == ID.Player) {
				player = handler.list.get(i);
			}
		}
		
		width = 48;
		height = 48;
		
		health = 8;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.orange);
		g.fillRect((int)x, (int)y, width, height);
	}
	
	public void update() {
		x += velX;
		y += velY;
		
		float diffX = x - player.getX()-8;
		float diffY = y - player.getY()-8;
		float distance = (float) Math.sqrt((x-player.getX())*(x-player.getX()) + (y-player.getY())*(y-player.getY()));
		
		velX = (float) ((-2.0/distance) * diffX)/3;
		velY = (float) ((-2.0/distance) * diffY)/3;
		
		if(health <= 0) {
			handler.removeObject(this);
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, width, height);
	}
}
