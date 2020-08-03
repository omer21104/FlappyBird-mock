import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.LinkedList;

public class HUD {
	static LinkedList<Texture> lives = new LinkedList<Texture>();
	Game game;
	
	public HUD(Game game)
	{
		this.game = game;
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.black);
		g.drawString("SCORE: " + game.score, 10, 32);
		
		for (int i = 0; i < lives.size(); i++) {
			g.drawImage(lives.get(i).get(), Game.WIDTH - 75 - (i * 50), 20, null);
		}
		
	}
	
	public void addLives(int lives) throws IOException
	{
		for (int i = 0; i < lives; i++) 
			HUD.lives.add(new Texture("heart"));
	}
	
	public static void removeLife()
	{
		if (lives.size() > 0)
			lives.remove(lives.size() - 1);
	}
	
}
