import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Window extends Canvas {

	JFrame frame;
	JButton restart;
	private static final long serialVersionUID = -8255319694373975038L;
	
	public Window(int width, int height, String title, Game game) {
		frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		restart = new JButton("RESTART");
		restart.setBounds(Game.WIDTH / 2 - 50, Game.HEIGHT / 2, 95 ,50);  
		frame.add(restart);  
		frame.add(game);
		restart.setVisible(false);
		frame.setVisible(true);
		
		
		restart.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				{
					try {
						game.restart();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}  
		});  
	}
	

	
	
	

}
