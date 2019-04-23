package screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import enemy.BasicEnemy;
import main.Game;
import main.Game.STATE;
import manager.GameObject;
import manager.Handler;
import manager.ID;
import player.Player;
import player.Projectile;

public class Menu extends MouseAdapter {
	private Game game;
	private HUD hud;

	public Menu(Game game, HUD hud) {
		this.game = game;
		this.hud = hud;
	}

	public void update() {
		
	}

	public void render(Graphics g) {
		if (game.gameState == STATE.Menu) {
			g.setColor(Color.white);

			g.setFont(new Font("arial", 1, 50));
			g.drawString("Menu", 255, 70);

			g.setFont(new Font("arial", 1, 30));
			g.drawString("Play", 288, 190);
			g.drawString("Help", 288, 290);
			g.drawString("Quit", 288, 390);

			g.drawRect(210, 150, 220, 64);
			g.drawRect(210, 250, 220, 64);
			g.drawRect(210, 350, 220, 64);
		} else if (game.gameState == STATE.Help) {
			g.setColor(Color.white);
			g.setFont(new Font("arial", 1, 50));
			g.drawString("Help", 262, 70);

			g.drawRect(210, 350, 220, 64);
			g.setFont(new Font("arial", 1, 30));
			g.drawString("Back", 282, 390);
		} else if (game.gameState == STATE.End) {
			g.setColor(Color.red);
			g.setFont(new Font("arial", 1, 50));
			g.drawString("Game Over", 185, 70);

			g.setColor(Color.white);
			g.setFont(new Font("arial", 1, 30));
			g.drawString("Total Score: " + hud.getScore(), 202, 150);

			g.drawRect(210, 350, 220, 64);
			g.drawString("Back", 282, 390);
		}
	}
}