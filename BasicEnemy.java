//import java.awt.Color;
//import java.awt.Graphics;
//
//public class BasicEnemy extends GameObject{
//
//	protected static final int DIM = 16;
//	
//	public BasicEnemy(int x, int y, ID id) {
//		super(x, y, id);
//		velX = Game.ENEMY_SPEED;
//		velY = Game.ENEMY_SPEED;
//		
//	}
//
//	@Override
//	public void tick() {
//		x += velX;
//		y += velY;
//		
//		// border collision
//		if (y <= 0 || y >= Game.HEIGHT - DIM*3) velY *= -1;
//		if (x <= 0 || x >= Game.WIDTH - DIM*2) velX *= -1;
//		
//	}
//
//	@Override
//	public void render(Graphics g) {
//		g.setColor(Color.red);
//		g.fillRect(x, y, DIM, DIM);
//		
//		
//	}
//
//}
