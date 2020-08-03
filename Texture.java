import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Texture {
	final String top = "C:\\Users\\omer2\\Dropbox\\Java\\My Game\\src\\pipedown.png";
	final String bottom = "C:\\Users\\omer2\\Dropbox\\Java\\My Game\\src\\pipeup.png";
	final String bird = "C:\\Users\\omer2\\Dropbox\\Java\\My Game\\src\\bird.png";
	final String heart = "C:\\Users\\omer2\\Dropbox\\Java\\My Game\\src\\heart.png";

	Image icon;

	public Texture(String selection) throws IOException {
		// load image
		BufferedImage img = null;
		switch (selection) {
		case "top":
			img = ImageIO.read(new File(top));
			break;
		case "bottom":
			img = ImageIO.read(new File(bottom));
			break;
		case "bird":
			img = ImageIO.read(new File(bird));
			break;
		case "heart":
			img = ImageIO.read(new File(heart));
			break;

		default:
			throw new IllegalArgumentException("Unexpected value: " + selection);
		}

		icon = new ImageIcon(img).getImage();

	}

	public Image get() {
		return icon;
	}
}
