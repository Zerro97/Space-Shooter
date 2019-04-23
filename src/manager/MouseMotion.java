package manager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import main.Game;
import main.Game.STATE;
import screen.HUD;

public class MouseMotion extends MouseMotionAdapter{
	private Game game;
	private Handler handler;
	private GameObject player;

	public MouseMotion(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
	}
	
	public void update() {
		if(game.gameState == STATE.Game) {
			if(player == null) {
				for(int i = 0; i < handler.list.size(); i++) {
					if(handler.list.get(i).getId() == ID.Player) {
						player = handler.list.get(i);
					}
				}
			}
		} else if(game.gameState == STATE.End) {
			player = null;
		}
	}

	public void mouseMoved(MouseEvent e) {
		if(player == null) {
			return;
		}
		
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState == STATE.Game) {
			double angle = Math.atan2(player.getY() + player.height/2 - my, player.getX() + player.width/2 - mx) - Math.PI / 2;
			player.setAngle(angle);
		}
	}
}
