import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;

public class Game extends Canvas implements Runnable{

	// consts
	private static final long serialVersionUID = -6112428091888191314L;
	protected static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	protected static final int SPEED = 5;
	protected static final int JUMP_HEIGHT = 12;
	protected static final int GRAV = 1;
	protected static final int TER_VEL = 3;
	
	// fields
	static Player bird = new Player(32, HEIGHT/2 - 100, ID.Player);
	
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	private Random rnd;
	private Window window;
	private HUD hud;
	protected int score = 0;
	protected int finalScore = 0;
	protected static int difficulty = 3;
	protected static int lives = 3;
	protected boolean gameOver = false;
	protected static boolean start = true;
	
	final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor(); // handle pipe creation
	
	
	// Game c-tor
	public Game()
	{
		handler = new Handler(this);
		rnd = new Random();
		hud = new HUD(this);
		try {
			hud.addLives(lives);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.addKeyListener(new KeyInput(handler));
		window = new Window(WIDTH, HEIGHT, "Mah first game", this);

		// generate pipes every 2 seconds
		executorService.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				handler.pipes.add(new Pipe());
			}
		}, 0, difficulty, TimeUnit.SECONDS);
		
		//start();
	}
	
	
	public synchronized void start() {
		
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {

		try {
			thread.join();
			running = false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		while(start)
		{
			Game game = new Game();
			game.start();
			start = false;
			while (!start)
			{
				System.out.print("");
				continue;
			}
			
			game.window.frame.setVisible(false);
		
		}
	}

	@Override
	public void run() {

		this.setFocusable(true);
		long lastTime = System.nanoTime(); // get current time to the nanosecond
		double amountOfTicks = 60.0; // set the number of ticks 
		double ns = 1000000000 / amountOfTicks; // this determines how many times we can devide 60 into 1e9 of nano seconds or about 1 second
		double delta = 0; // change in time (delta always means a change like delta v is change in velocity)
		long timer = System.currentTimeMillis(); // get current time
		int frames = 0; // set frame variable
		while(running){ 
			long now = System.nanoTime(); // get current time in nonoseconds durring current loop
			delta += (now - lastTime) / ns; // add the amount of change since the last loop
			lastTime = now; // set lastTime to now to prepare for next loop
			while (delta >= 1) {
				tick();
				delta--; // lower our delta back to 0 to start our next frame wait
			}
			if (running) {
				render(); // render the visuals of the game
			}
			frames++; // note that a frame has passed
			if (System.currentTimeMillis() - timer > 1000) { // if one second has passed
				timer += 1000; // add a thousand to our timer for next time
				//System.out.println("FPS: " + frames); // print out how many frames have happend in the last second
				frames = 0; // reset the frame count for the next second
		   }
		  }
		  stop(); // no longer running stop the thread
	 }
	
	public void tick()
	{
		if (!gameOver)
		{
			handler.tick();	
			score++;
			
			// check lives
			if (HUD.lives.size() == 0)
				gameOver = true;
		}
		else // game over
		{
			executorService.shutdown();
			handler.gameOver();
		}
	}
		
	public void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		if (!gameOver)
		{
			g.setColor(Color.cyan.darker());
			g.fillRect(0, 0, WIDTH, HEIGHT);
			
			handler.render(g);
			hud.render(g);
			
			
		}
		else
		{
			g.setColor(Color.cyan.darker());
			g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			g.setColor(Color.black);
			g.drawString("GAME OVER", Game.WIDTH / 2 - 50, Game.HEIGHT / 2 - 100);
			g.drawString("Score: " + (score + 99), Game.WIDTH / 2 - 50, Game.HEIGHT / 2 -50);
			window.restart.setVisible(true);
		}
		g.dispose();
		bs.show();
	}
	
	public void updateScore(int amt)
	{
		if (score >= amt)
			score -= amt;
	}
	
//	public void gameOver()
//	{
//		BufferStrategy bs = this.getBufferStrategy();
//		if (bs == null)
//		{
//			this.createBufferStrategy(3);
//			return;
//		}
//		Graphics g = bs.getDrawGraphics();
//		g.setColor(Color.cyan.darker());
//		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
//		g.setColor(Color.black);
//		g.drawString("GAME OVER", Game.WIDTH / 2, Game.HEIGHT / 2);
//		g.drawString("Score: " + finalScore, Game.WIDTH / 2, Game.HEIGHT / 2 + 50);
//	}
}
