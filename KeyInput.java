import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
 
	private Handler handler;
	
	public KeyInput(Handler handler)
	{
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) 
	{
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_SPACE) Game.bird.setVelY(-Game.JUMP_HEIGHT);
	}
	
}
