package manager;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import main.Game.STATE;

public class KeyInput extends KeyAdapter{
	private Handler handler;
	public HashMap<Integer, Boolean> keys;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
		this.keys = new HashMap<Integer, Boolean>();
		
		keys.put(KeyEvent.VK_W, false);
		keys.put(KeyEvent.VK_S, false);
		keys.put(KeyEvent.VK_A, false);
		keys.put(KeyEvent.VK_D, false);
	}
	
	public void update() {
		//System.out.println("Key Check");
		GameObject tempObject = null;
		
		for(int i = 0; i < handler.list.size(); i++) {
			tempObject = handler.list.get(i);
			
			if(tempObject.getId() == ID.Player) {
				tempObject.setVelX(0);
				tempObject.setVelY(0);
				
				System.out.println(keys.get(KeyEvent.VK_W));
				if(keys.get(KeyEvent.VK_W)) {
					tempObject.setVelY(-5);
				}
				if(keys.get(KeyEvent.VK_S)) {
					tempObject.setVelY(5);
				}
				if(keys.get(KeyEvent.VK_A)) {
					tempObject.setVelX(-5);
				}
				if(keys.get(KeyEvent.VK_D)) {
					tempObject.setVelX(5);
				}
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("Pressed: " + e.getKeyChar());
		keys.put(e.getKeyCode(), true);
	}
	
	public void keyReleased(KeyEvent e) {
		System.out.println("Released:  " + e.getKeyChar());
		keys.put(e.getKeyCode(), false);
		
		/*for(int i = 0; i < handler.list.size(); i++) {
			GameObject tempObject = handler.list.get(i);
			
			if(tempObject.getId() == ID.Player) {
				if(key == KeyEvent.VK_W) tempObject.setVelY(0);
				if(key == KeyEvent.VK_S) tempObject.setVelY(0);
				if(key == KeyEvent.VK_A) tempObject.setVelX(0);
				if(key == KeyEvent.VK_D) tempObject.setVelX(0);
			}
		}*/
	}
}
