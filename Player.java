import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.Random;

public class Player extends GameObject{
	
	protected static int DIM;
	private Random rnd;
	Texture birdTexture;
	
	public Player(int x, int y, ID id) {
		super(x, y, id);
		try {
			birdTexture = new Texture("bird");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		DIM = birdTexture.get().getHeight(null);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		// collision
		if(y <= 0)
			y = 0;
		if(y >= Game.HEIGHT - DIM*2)
			y = Game.HEIGHT - DIM*2;
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(birdTexture.get(), x, y, null);
		//		g.setColor(Color.white);
//		g.fillRect(x, y, DIM, DIM);
	}
	
	public void fall()
	{
		velY += Game.GRAV;
		if (velY > Game.TER_VEL)
			velY = Game.TER_VEL;
	}

}
