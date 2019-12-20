package manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class KeyInput2 extends KeyAdapter{
	private Handler handler;
	private JPanel panel;
	public HashMap<Integer, Boolean> keys;
	
	public KeyInput2(Handler handler, JPanel panel) {
		this.handler = handler;
		this.panel = panel;
		this.keys = new HashMap<Integer, Boolean>();
		
		keys.put(KeyEvent.VK_W, false);
		keys.put(KeyEvent.VK_S, false);
		keys.put(KeyEvent.VK_A, false);
		keys.put(KeyEvent.VK_D, false);
	}
	
	
	public void update() {
		GameObject tempObject = null;
		
		for(int i = 0; i < handler.list.size(); i++) {
			tempObject = handler.list.get(i);
			
			if(tempObject.getId() == ID.Player) {
				tempObject.setVelX(0);
				tempObject.setVelY(0);
				
				//System.out.println(keys.get(KeyEvent.VK_W));
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
	
	//=============
	// Set Up
	//=============
	/**
	 * Set up key binding and find player id
	 */
	public void setUp() {
		addKeyBinding(panel, KeyEvent.VK_A, "left", (evt) -> {
			keys.put(KeyEvent.VK_A, true);
		}, (evt) -> {
			keys.put(KeyEvent.VK_A, false);
		});
		
		addKeyBinding(panel, KeyEvent.VK_D, "right", (evt) -> {
			keys.put(KeyEvent.VK_D, true);
		}, (evt) -> {
			keys.put(KeyEvent.VK_D, false);
		});
		
		addKeyBinding(panel, KeyEvent.VK_W, "up", (evt) -> {
			System.out.println("down");
			keys.put(KeyEvent.VK_W, true);
		}, (evt) -> {
			keys.put(KeyEvent.VK_W, false);
		});
		
		addKeyBinding(panel, KeyEvent.VK_D, "down", (evt) -> {
			System.out.println("down");
			keys.put(KeyEvent.VK_D, true);
		}, (evt) -> {
			keys.put(KeyEvent.VK_D, false);
		});
	}
	
	public void addKeyBinding(JComponent comp, int keyCode, String id, ActionListener pressListener, ActionListener releaseListener) {
		InputMap inputMap = comp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = comp.getActionMap();

        
        
        inputMap.put(KeyStroke.getKeyStroke(keyCode, 0, false), id + "Pressed");
        System.out.println(id + "Pressed");
        actionMap.put(id + "Pressed", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("123");
				pressListener.actionPerformed(e);
			}
        });
        System.out.println(id + "Pressed");
        
        inputMap.put(KeyStroke.getKeyStroke(keyCode, 0, false), id + "Released");
        actionMap.put(id + "Released", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				releaseListener.actionPerformed(e);
			}
        });
        
        
	}
}
