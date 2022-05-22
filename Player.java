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
	private double xVelocity = 0;
	private double yVelocity = 0;
	private boolean airborne = false;
	private Insets insets;
	private final int MAX_VELOCITY = 10;
	private final int TERMINAL_VELOCITY = 10;
	private static ImageIcon[] sprites;
	private int runFrame = 0;
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
		yVelocity = -20;
	}
	public void update() {
		if (airborne) {
			yVelocity += 1;
		}
		else {
			yVelocity = 0;
		}
		//if not colliding on thet bottom with any blocks 
		//if () {
			//airborne = false;
		//}
		if (sprites != null) {
			if (xVelocity != 0) {
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
		setLocation(getX() + (int)xVelocity, getY() + (int)(yVelocity));
	}
	
	public void accelerate(double x, double y) {
		if ((Math.abs(xVelocity) <= Math.abs(MAX_VELOCITY))) {
			xVelocity += x;
		}
		if (!(yVelocity > TERMINAL_VELOCITY)) {
			yVelocity += y;
		}
		decelerating = false;
	}
	
	public void decelerate(double x, double y) {
		if (!(Math.abs(xVelocity) < x) && Math.abs(xVelocity) > 8) {
			xVelocity -= x;
		}
		else {
			xVelocity = 0;
		}
		if (!(yVelocity < y)) {
			yVelocity -= x;
		}
		else {
			//yVelocity = 0;
		}
		decelerating = true;
	}
	public double getXVelocity() {
		return xVelocity;
	}
	
	public double getYVelocity() {
		return yVelocity;
	}
	public void setXVelocity(double val) {
		xVelocity = val;
	}
	public void setYVelocity(double val) {
		yVelocity = val;
	}
	
	
	
};
