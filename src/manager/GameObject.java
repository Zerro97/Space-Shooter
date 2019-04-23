package manager;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	// Position
	protected float x;
	protected float y;
	
	// ID
	protected ID id;
	
	// Velocity
	protected float velX;
	protected float velY;
	
	// Rotation
	protected double angle;
	
	// Size
	protected int width;
	protected int height;
	
	// Health
	protected int health;
	
	public GameObject(float x, float y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void update();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public void setId(ID id) {
		this.id = id;
	}
	
	public void setVelX(float velX) {
		this.velX = velX;
	}
	
	public void setVelY(float velY) {
		this.velY = velY;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public ID getId() {
		return id;
	}
	
	public float getVelX() {
		return velX;
	}
	
	public float getVelY() {
		return velY;
	}
	
	public int getHealth() {
		return health;
	}
	
	public double getAngle() {
		return angle;
	}
}
