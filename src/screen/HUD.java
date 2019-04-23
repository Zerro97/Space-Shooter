package screen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import manager.GameObject;
import manager.ID;

public class HUD{
	public static float HEALTH = 100;
	private float greenValue = 255;
	
	private int score = 1;
	private int level = 1;
	
	public void update() {
		HEALTH = GameMap.clamp(HEALTH, 100, 0);
		greenValue = GameMap.clamp(greenValue, 255, 0);
		
		greenValue = HEALTH*2;
		score++;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 32);
		g.setColor(new Color(75, (int)greenValue, 0));
		g.fillRect(15, 15, (int)HEALTH * 2, 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);
		
		g.drawString("Score: " + score, 15, 64);
		g.drawString("Level: " + level, 15, 80);
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}
}
