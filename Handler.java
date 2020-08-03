import java.awt.Graphics;
import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Handler {

	Game game;
	LinkedList<Pipe> pipes = new LinkedList<Pipe>();
	protected Audio audio;
	
	public Handler(Game game)
	{
		this.game = game;
	}
	
	public void tick() 
	{
		if (!game.gameOver)
		{
			
			Game.bird.tick();
			Game.bird.fall();
			for (int i = 0; i < pipes.size(); i++) {
				Pipe tmp = pipes.get(i);
				tmp.tick();
				
				if (tmp.offScreen())
					this.removePipe(tmp);
				
				if (tmp.hit(Game.bird))
				{
					// remove one life
					HUD.removeLife();
					
					if (tmp.bottomHit)
						Game.bird.setY(Game.HEIGHT - tmp.bottom - Game.bird.DIM);
					if (tmp.topHit)
						Game.bird.setY(tmp.top);
					
					try {
						audio = new Audio();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
						e.printStackTrace();
					}
					System.out.println("BONK");
					
					game.updateScore(100);
				}
			}
			
		}
	}
	
	public void render(Graphics g)
	{
		if (!game.gameOver)
		{
			Game.bird.render(g);
			
			for (int i = 0; i < pipes.size(); i++) {
				Pipe tmp = pipes.get(i);
				tmp.render(g);
			}			
		}
	}
	
	public void gameOver()
	{
		while (pipes.size() > 0)
			pipes.remove(pipes.size() - 1);
	}

	
	public void addPipe(Pipe pipe)
	{
		this.pipes.add(pipe);
	}
	
	public void removePipe(Pipe pipe)
	{
		this.pipes.remove(pipe);
	}
	
}
