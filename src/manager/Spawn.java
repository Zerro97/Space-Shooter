package manager;

import java.util.Random;

import enemy.BasicEnemy;
import enemy.HugeEnemy;
import enemy.StraightEnemy;
import main.Game;
import screen.HUD;

public class Spawn {
	private Handler handler;
	private HUD hud;
	private Random rand = new Random();

	private int scoreKeep = 0;

	public Spawn(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}

	public void update() {
		scoreKeep++;

		if (scoreKeep >= 200) {
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);

			for (int i = 0; i < 2; i++) {
				handler.addObject(
						new BasicEnemy(rand.nextInt(Game.WIDTH), rand.nextInt(Game.HEIGHT), ID.Enemy, handler));
			}

			if (hud.getLevel() % 2 == 0) {
				handler.addObject(
						new StraightEnemy(rand.nextInt(Game.WIDTH), rand.nextInt(Game.HEIGHT), ID.Enemy, handler));
			}

			if (hud.getLevel() == 5) {
				handler.addObject(
						new HugeEnemy(rand.nextInt(Game.WIDTH), rand.nextInt(Game.HEIGHT), ID.Enemy, handler));
			}
		}
	}

	public void setScoreKeep(int scoreKeep) {
		this.scoreKeep = scoreKeep;
	}
}
