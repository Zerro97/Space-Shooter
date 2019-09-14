package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import enemy.BasicEnemy;
import manager.GameObject;
import manager.Handler;
import manager.ID;
import manager.KeyInput;
import manager.MouseInput;
import manager.MouseMotion;
import manager.Spawn;
import player.Player;
import screen.GameMap;
import screen.HUD;
import screen.Menu;

public class Game extends Canvas implements Runnable {
	// Frame
	public Frame frame;
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;

	// Thread
	private Thread thread;
	private boolean isRunning;

	// Management Objects
	private Handler handler;
	private Spawn spawner;
	private MouseInput mouseInput;
	private MouseMotion mouseMotion;
	private KeyInput keyInput;
	public STATE gameState = STATE.Menu;

	// Game Screen
	public GameMap map;
	public Menu menu;
	public HUD hud;

	// Game States (Determines which screen to render)
	public enum STATE {
		Menu, Help, Game, End
	};

	public Game() {
		frame = new Frame(WIDTH, HEIGHT, this);

		handler = new Handler();
		hud = new HUD();
		spawner = new Spawn(handler, hud);
		menu = new Menu(this, hud);
		mouseInput = new MouseInput(this, handler, hud);
		mouseMotion = new MouseMotion(this, handler);
		keyInput = new KeyInput(handler);

		// Add event listeners
		this.addMouseMotionListener(mouseMotion);
		this.addMouseListener(mouseInput);
		this.addKeyListener(keyInput);

		// Adds black background 
		map = new GameMap(WIDTH, HEIGHT);
		
		//start();
		isRunning = true;
		run();
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.setName("thr1");
		isRunning = true;
		
		thread.start();
	}

	public synchronized void stop() {
		try {
			thread.join();
			isRunning = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		long now;
		double fps = 60.0;
		double optimal = 1000000000 / fps;
		double delta = 0;
		
		long timer = System.currentTimeMillis();
	    int frames = 0 ;

		while (isRunning) {
			now = System.nanoTime();
			delta += (now - lastTime) / optimal;
			lastTime = now;
			while (delta >= 1) {
				update();
				delta--;
			}

			if (isRunning) {
				render();
			}
			frames++;

	        if(System.currentTimeMillis() - timer > 1000) {
	            timer += 1000;
	            //System.out.println("FPS: " + frames);
	            frames = 0;
	        }
		}
		stop();
	}

	public void update() {
		handler.update();
		menu.update();
		mouseInput.update();
		mouseMotion.update();
		keyInput.update();

		if (gameState == STATE.Game) {
			hud.update();
			spawner.update();
			if (hud.HEALTH <= 0) {
				hud.HEALTH = 100;
				spawner.setScoreKeep(0);
				handler.list.clear();
				gameState = STATE.End;
			}
		}
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics2D g = (Graphics2D) bs.getDrawGraphics();

		map.render(g);
		handler.render(g);

		if (gameState == STATE.Game) {
			hud.render(g);
		}

		menu.render(g);

		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		new Game();
	}
}