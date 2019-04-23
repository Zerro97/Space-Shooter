package screen;

import java.awt.Color;
import java.awt.Graphics;

import main.Game;

public class GameMap {
	// Dimension
	int width;
	int height;
	
	// Big Circle
	int radius;
	int x;
	int y;
	
	public GameMap(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public void update() {
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
	}
	
	public static float clamp(float var, float max, float min) {
		if(var > max) {
			return var = max;
		} else if(var < min) {
			return var = min;
		} else {
			return var;
		}
	}
	
	public static boolean outOfBound(float x, float y) {
		if(x > 0 && x < Game.WIDTH) {
			if(y > 0 && y < Game.HEIGHT) {
				return false;
			}
		}
		
		return true;
	}
}
