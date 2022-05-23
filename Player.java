import javax.swing.JComponent;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class Player extends GameObject{
	private Rectangle hitbox;
	private JLabel picLabel;
	private BufferedImage myPicture;
	private boolean airborne = false;
	private Insets insets;
	private final int MAX_VELOCITY = 10;
	private final int TERMINAL_VELOCITY = 10;
	private int runFrame = 0;
	private ImageIcon[] sprites;
	private boolean decelerating = false;
	public Player(int x, int y) {
		super(x, y, 100, 100, "src/test.png");
		insets = getInsets();
		if (sprites == null) {
			String[] imagePaths = {"src/mario/rest.png", "src/mario/run_01.png", "src/mario/run_02.png", "src/mario/run_03.png", "src/mario/stopping.png"};
			sprites = new ImageIcon[imagePaths.length];
			for (int i = 0; i < imagePaths.length; i++) {
				try {
					File test = new File(imagePaths[i]);
					sprites[i] = new ImageIcon(ImageIO.read(test));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Help");
				}
			}
		}
		
	}
	public void jump() {
		if (airborne) {
			//return;
		}
		changedy(-20);
	}
	public void update() {
		if (airborne) {
			changedy(-1);
		}
		else {
			setdy(0);
		}
		//if not colliding on thet bottom with any blocks 
		//if () {
			//airborne = false;
		//}
		if (sprites != null) {
			if (getdx() != 0) {
				if (decelerating) {
					runFrame = 0;
					setImage(sprites[4]);
					
				}
				else {
					if (runFrame == 4) {
						setImage(sprites[1]);
						runFrame++;
					}
					else if (runFrame == 8) {
						setImage(sprites[2]);
						runFrame++;
					}
					else if (runFrame == 12) {
						setImage(sprites[3]);
						runFrame = 0;
					}
					else {
						runFrame++;
					}
				}
			}
			else {
				setImage(sprites[0]);
			}
		}
		setLocation(getX() + getdx(), getY() + getdy());
	}
	
	public void accelerate(int x, int y) {
		if ((Math.abs(getdx()) <= Math.abs(MAX_VELOCITY))) {
			changedx(x);
		}
		if (!(getdy() > TERMINAL_VELOCITY)) {
			changedy(y);
		}
	}
	
	public void decelerate(int x, int y) {
		if (!(getdx() < x)) {
			changedx(-x);
		}
		else {
			setdx(0);
		}
		if (!(getdy() < y)) {
			changedy(-y);
		}
		else {
			//yVelocity = 0;
		}
	}
	
	
	
};
