import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Goomba extends Enemy {
	
	private long startTime = -1;
	private int runFrame = 0;
	private BufferedImage[] sprites;

	public Goomba(int x, int y) {
		super(x, y, 100, 100, "src/goomba.png");
		if (sprites == null) {
			String[] imagePaths = {"src/enemy/goomba_rest.png", "src/enemy/goomba_run_01.png", "src/enemy/goomba_run_02.png", "src/enemy/goomba_stomped.png"};
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
		if (startTime != -1) {
			if (System.nanoTime() - startTime > 1000) {
				setVisible(false);
			}
		}
		setdx(2);
	}
	@Override
	public int stomp() {
		// TODO Auto-generated method stub
		setCollisions(false);
		setImage(sprites[3]);
		startTime = System.nanoTime();
		return 1;
	}

	
	public void update(ArrayList<GameObject> list) {
		if (getdx() != 0) {
			if (runFrame == 10) {
				setImage(sprites[1]);
			}
			else if (runFrame == 20) {
				setImage(sprites[2]);
				runFrame = 0;
			}
			runFrame++;
		}
		
		setLocation(getX() + getdx(), getY() + getdy());
	}
	
}
