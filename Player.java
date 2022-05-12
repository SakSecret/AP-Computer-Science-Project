import javax.swing.JComponent;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class Player extends JPanel {
	private Rectangle hitbox;
	private JLabel picLabel;
	private BufferedImage myPicture;
	private double xVelocity = 0;
	private double yVelocity = 0;
	private boolean airborne = false;
	private Insets insets;
	private final int MAX_VELOCITY = 10;
	private final int TERMINAL_VELOCITY = 10;
	public Player(int x, int y) {
		setLocation(x, y);
		setSize(100, 100);
		try {
			File test = new File("src/test.png");
			myPicture = ImageIO.read(test);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Help");
			myPicture = null;
		}
		picLabel = new JLabel(new ImageIcon(myPicture));
		picLabel.setPreferredSize(new Dimension(100, 100));
		add(picLabel);
		insets = this.getInsets();
		hitbox = new Rectangle(0, 0, 100, 100);
		
	}
	public void jump() {
		if (airborne) {
			return;
		}
		yVelocity = 20;
	}
	public void update() {
		if (getY() + getHeight() > getParent().getHeight() - insets.bottom - yVelocity) {
			airborne = false;
		}
		else {
			airborne = true;
		}
		if (airborne) {
			yVelocity = 10;
		}
		else {
			yVelocity = 0;
		}
		setLocation(getX() + (int)xVelocity, getY() + (int)(yVelocity));
	}
	
	public void accelerate(double x, double y) {
		if (!(xVelocity > MAX_VELOCITY)) {
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
			yVelocity = 0;
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
