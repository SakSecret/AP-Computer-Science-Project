import javax.swing.JPanel;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class GameObject extends JPanel{
	private int dx;
	private int dy;
	private Rectangle hitbox;
	private BufferedImage img;
	//private JLabel imageLabel;
	private boolean collides; //if true, collisions apply, if false, collisions do not apply
	private double xPos = super.getX();
	private double yPos = super.getY();
	private boolean clearNextImage = false;
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
		//imageLabel = new JLabel(new ImageIcon(img));
		//add(imageLabel);
	}
	
	public void update(ArrayList<GameObject> list) {
		return;
	}
	
	public int getdx() {
		return dx;
	}
	public int getdy() {
		return dy;
	}
	
	public void setdx(int val) {
		dx = val;
	}
	
	public void setdy(int val) {
		dy = val;
	}
	
	public void changedx(int val) {
		dx += val;
	}
	public void changedy(int val) {
		dy += val;
	}
	public Rectangle getHitbox() {
		return new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}
	public boolean hasCollisions() {
		return collides;
	}
	public void setCollisions(boolean val) {
		collides = val;
	}
	
	public boolean isColliding(GameObject obj1) {
		if (obj1.hasCollisions() && collides) {
			return obj1.getHitbox().intersects(this.getHitbox());
		}
		return false;
	}
	
	public void scroll(int val) {
		setLocation(getX() + val, getY());
	}
	
	public void setImage(BufferedImage icon) {
		img = icon;
		clearNextImage = true;
	}
	public int stomp() {
		return 0;
	}
	public void markForDeletion() { //sets up to be deleted during garbage cleanup
		setVisible(false);
		//change a variable or something
	}
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		if (clearNextImage) {
			g2.clearRect(0, 0, getWidth(), getHeight());
			clearNextImage = false;
		}
		g2.drawImage(img, 0, 0, null);
	}
	
}
