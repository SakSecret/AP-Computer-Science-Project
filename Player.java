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
	
	
	
	public Player(int x, int y) {
		super(x, y, 100, 100, "src/test.png");
		insets = getInsets();
		
		if (sprites == null) {
			String[] imagePaths = {"src/mario/rest.png", "src/mario/run.png"};
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
				setImage(sprites[1]);
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
	}
	
	public void decelerate(double x, double y) {
		if (!(xVelocity < x)) {
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
