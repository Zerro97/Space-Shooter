package player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.Game;
import manager.GameObject;
import manager.Handler;
import manager.ID;
import screen.GameMap;
import screen.HUD;

public class Player extends GameObject {
	// Handler
	private Handler handler;
	
	// Image
	private BufferedImage image;


	public Player(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		angle = 0;
		width = 32;
		height = 32;
	}
	
	public void update() {
		// Update player's position
		x += velX;
		y += velY;
		
		// Prevent player from getting out of map bound
		x = GameMap.clamp(x, Game.WIDTH - width, 0);
		y = GameMap.clamp(y, Game.HEIGHT - height, 0);
		
		// Lowers player HP if collided
		collision();
	}

	public void render(Graphics g) {
		try {
			//Graphics2D g2d = (Graphics2D) g;
			image = ImageIO.read(getClass().getResource("/SpaceShip.png"));

			//g2d = (Graphics2D) image.getGraphics();
			AffineTransform old = ((Graphics2D) g).getTransform();
			((Graphics2D) g).rotate(angle, x+width/2, y+height/2);
			g.drawImage(image, (int)x, (int)y, null);
			((Graphics2D) g).setTransform(old);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, width, height);
	}
	
	private void collision() {
		for(int i = 0; i<handler.list.size(); i++) {
			GameObject tempObject = handler.list.get(i);
			
			if(tempObject.getId() == ID.Enemy) {
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 2;
				}
			}
		}
	}
}
