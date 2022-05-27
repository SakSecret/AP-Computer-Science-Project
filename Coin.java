import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Coin extends GameObject {
	//TODO implement coin images and 
	private static int coinFrame = 0;
	private static BufferedImage[] sprites;
	
	public Coin(int x, int y) {
		super(x, y, 30, 30, "src/coin.png");
		setCollisions(false);
		
		if (sprites == null) {
			String[] imagePaths = {"src/misc/coin_01.png", "src/misc/coin_02.png"};
			sprites = new BufferedImage[imagePaths.length];
			for (int i = 0; i < imagePaths.length; i++) {
				try {
					File test = new File(imagePaths[i]);
					sprites[i] = ImageIO.read(test);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Help");
				}
			}
			
		}
	}
	
	public void update(ArrayList<GameObject> list) {
		if (coinFrame == 10) {
			setImage(sprites[0]);
		}
		else if (coinFrame == 20) {
			setImage(sprites[1]);
			coinFrame = 0;
		}
	}

	public static void updateCoinFrame() {
		coinFrame++;
	}
	
}
