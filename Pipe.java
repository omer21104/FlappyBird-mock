import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.Random;

public class Pipe extends GameObject{
	int top, bottom, gap;
	final int PIPE_WIDTH = 166;
	final int PIPE_SPEED = -3;
	boolean hitBird = false;
	boolean bottomHit = false, topHit = false;
	Texture topTexture;
	Texture bottomTexture;
	Random rnd;
	
	public Pipe()
	{
		this.x = Game.WIDTH + PIPE_WIDTH;
		this.velX = PIPE_SPEED;
		rnd = new Random();
		top = rnd.nextInt(Game.HEIGHT/2);
		bottom = rnd.nextInt(Game.HEIGHT/2);
		
		while (Game.HEIGHT - (top + bottom) < 100 || Game.HEIGHT - (top + bottom) > 300)
		{
			top = rnd.nextInt(Game.HEIGHT/2) + 60;
			bottom = rnd.nextInt(Game.HEIGHT/2) + 60;
		}
		
		try {
			topTexture = new Texture("top");
			bottomTexture = new Texture("bottom");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public boolean offScreen()
	{
		if(this.x < -PIPE_WIDTH) // pipe is off screen
			return true;
		else 
			return false;
	}
	
	public boolean hit(Player bird)
	{
		if (!hitBird)
			{
			if (bird.getX() >= this.x && bird.getX() <= this.x + PIPE_WIDTH) // bird is within pipe x range
				{
					if (bird.getY() <= top)
					{
						topHit = true;
					}
					if (bird.getY() >= Game.HEIGHT - bottom - Game.bird.DIM)
					{
						bottomHit = true;
					}
					if (bottomHit || topHit)
					{
						hitBird = true;
						return true;
					}
				}
			}
				
		return false;
	}
	
	@Override
	public void tick() {
		this.x += velX;
	}

	@Override
	public void render(Graphics g) {

		g.drawImage(topTexture.get(), x, -topTexture.get().getHeight(null) + top, null); // top pipe
		g.drawImage(bottomTexture.get(), x, Game.HEIGHT - bottom, null);
		
//		g.setColor(Color.green);
//		g.fillRect(this.x, 0, PIPE_WIDTH, top); // top pipe
//		g.fillRect(this.x, Game.HEIGHT - bottom, PIPE_WIDTH, bottom); // bottom pipe
	}

}
