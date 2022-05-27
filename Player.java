import javax.swing.JComponent;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
public class Player extends GameObject{
	private Rectangle hitbox;
	private JLabel picLabel;
	private BufferedImage myPicture;
	private boolean airborne = false;
	private Insets insets;
	private final int MAX_VELOCITY = 10;
	private final int TERMINAL_VELOCITY = 10;
	private int runFrame = 0;
	private BufferedImage[] sprites;
	private boolean decelerating = false;
	private int imgIndex = 0; //index of the sprite to be drawn
	public Player(int x, int y) {
		super(x, y, 100, 100, "src/test.png");
		insets = getInsets();
		if (sprites == null) {
			String[] imagePaths = {"src/mario/rest.png", "src/mario/run_01.png", "src/mario/run_02.png", "src/mario/run_03.png", "src/mario/stopping.png"};
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
	public void jump() {
		if (airborne) {
			return;
		}
		System.out.println("actually works");
		setLocation(getX(), getY() - 5); //quick workaround
		setdy(-20);
	}
	public void update(ArrayList<GameObject> list) {
		airborne = true;
		for (int i = 0; i < list.size(); i++) {
			if (isColliding(list.get(i)) && !list.get(i).equals(this)) {
				GameObject obj = list.get(i);
//				setdy(0);
//				airborne = false;
//				setLocation(getX(), list.get(i).getY() - getHeight() + 1);
				if (list.get(i).stomp() != 0) {
					//setdy(-10);
				}
				if (obj.hasPhysics()) {
					if (getY() < obj.getY()) {// if player's upper hitbox is above the object's upper hitbox
						if (getY() + getHeight() < obj.getY()  + obj.getHeight()) {
							setdy(0);
							airborne = false;
							setLocation(getX(), list.get(i).getY() - getHeight() + 1);
						}
					}
					
				}
				if (list.get(i) instanceof Coin) {
					list.get(i).setVisible(false);
					Coin.coinCollected();
					list.get(i).setCollisions(false);
				}
			}
		}
		
		if (airborne) {
			changedy(1);
		}


		//if not colliding on thet bottom with any blocks 
		//if () {
			//airborne = false;
		//}
		if (sprites != null) {
			if (getdx() != 0) {
				if (decelerating) {
					runFrame = 0;
					imgIndex = 4;
					
				}
				else {
					if (runFrame == 4) {
						imgIndex = 1;
						runFrame++;
					}
					else if (runFrame == 8) {
						imgIndex = 2;
						runFrame++;
					}
					else if (runFrame == 12) {
						imgIndex = 3;
						runFrame = 0;
					}
					else {
						runFrame++;
					}
				}
			}
			else {
				imgIndex = 0;
			}
		}
		setLocation(getX() + getdx(), getY() + getdy());
		System.out.println(getdy());
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
		if (Math.abs(getdx()) > Math.abs(x)) {
			if (getdx() > 0) {
				changedx(-x);
			}
			else {
				changedx(x);
			}
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
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(sprites[imgIndex], 0, 0, null);
	}
	
	
	
}
 
