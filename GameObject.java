import javax.swing.JPanel;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.awt.Rectangle;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class GameObject extends JPanel{
	private double dx;
	private double dy;
	private Rectangle hitbox;
	private BufferedImage img;
	private JLabel imageLabel;
	private boolean collides; //if true, collisions apply, if false, collisions do not apply
	
	
	public GameObject() {
		dx = 0;
		dy = 0;
		
		hitbox = new Rectangle();
		
		img = null;
	
	}
	public GameObject(int x, int y, int width, int height, String imgSrc) {
		setSize(width, height);
		setLocation(x, y);
		hitbox = new Rectangle(0, 0, width, height);
		collides = true;
		try {
			File test = new File(imgSrc);
			img = ImageIO.read(test);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Help");
			img = null;
		}

		imageLabel = new JLabel(new ImageIcon(img));
		add(imageLabel);
	}
	
	public double getdx() {
		return dx;
	}
	public double getdy() {
		return dy;
	}
	
	public void setdx(double val) {
		dx = val;
	}
	
	public void setdy(double val) {
		dy = val;
	}
	public Rectangle getHitbox() {
		return hitbox;
	}
	public boolean hasCollisions() {
		return collides;
	}
	
	public void setImage(ImageIcon icon) {
		imageLabel.setIcon(icon);
	}
	
	public boolean isColliding(GameObject obj1) {
		if (obj1.hasCollisions() && collides) {
			if (obj1.getHitbox().intersects(hitbox)) {
				return true;
			}
		}
		return false;
	}
	
	public void scroll() {
		setLocation(getX() + 2, getY());
	}

}
