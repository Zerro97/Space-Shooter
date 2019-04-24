package manager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import enemy.BasicEnemy;
import main.Game;
import main.Game.STATE;
import player.Player;
import player.Projectile;
import screen.HUD;

public class MouseInput extends MouseAdapter {
	private Game game;
	private Handler handler;
	private HUD hud;
	private GameObject player;

	public MouseInput(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}

	public void update() {

	}

	public synchronized void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		// MENU
		if (game.gameState == STATE.Menu) {
			// Play Button
			if (mouseOver(mx, my, 210, 150, 220, 64)) {
				game.gameState = STATE.Game;

				handler.addObject(new Player(100, 100, ID.Player, handler));

				for (int i = 0; i < handler.list.size(); i++) {
					if (handler.list.get(i).getId() == ID.Player) {
						player = handler.list.get(i);
					}
				}
			}

			// Help Button
			if (mouseOver(mx, my, 210, 250, 220, 64)) {
				game.gameState = STATE.Help;
			}

			// Quit Button
			if (mouseOver(mx, my, 210, 350, 220, 64)) {
				System.exit(0);
			}
		}

		// HELP
		else if (game.gameState == STATE.Help) {
			if (mouseOver(mx, my, 210, 350, 220, 64)) {
				game.gameState = STATE.Menu;
			}
		}

		// END
		else if (game.gameState == STATE.End) {
			if (mouseOver(mx, my, 210, 350, 220, 64)) {
				hud.setLevel(1);
				hud.setScore(0);

				game.gameState = STATE.Menu;
			}
		}

		// GAME
		else if (game.gameState == STATE.Game) {
			handler.addObject(new Projectile(player.getX() + player.width / 2, player.getY() + player.height / 2,
					ID.Bullet, handler));
		}
	}

	public void mouseReleased(MouseEvent e) {

	}

	/*
	 * Private helper method to be used in mouse pressed
	 */
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			}
		}
		return false;
	}
}
