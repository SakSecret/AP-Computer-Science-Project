import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.*;
import java.awt.event.*;

public class ProjectMain extends JFrame implements ActionListener, KeyListener {

	//To Do:
	//add a thing that infrequently(every 200 millisec maybe?) removes objects that are out of the game window
	/**
	 * 
	 * How to fix issue of hitboxes
	 * The issue is that the intersects() method does not know relative, so it thinks all hitboxes
	 * originate on the origin of the same plane, so fin da way to implement the absolute 
	 */
	private Timer t = new Timer(20, this);
	private Player player;
	private Platform platform;
	//private Block block;
	private boolean[] keyPressedList = {false, false, false};
	private Timer tenFrame;
	private int screenX; //x of the screen in the game world, top left
	private int screenY; //y of the screen in the game world, top left
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private boolean scrolling = false;
	private int frame = 0;
	private int cameraSide = 0; //0 means mario can move right and velocity is still, 1 means he can move left and vleocity is still
	private int offset = 0; //offset of the screen
	
	public ProjectMain() {
		setBounds(100, 100, 1000, 500);
		setLayout(null);
		
		addKeyListener(this);
		screenX = 0;
		screenY = 0;
		scrolling = true;
		player = new Player(100, 100);
		platform = new Platform(200, 400);
		//block = new Block(300, 300);
		objects.add(player);
		objects.add(platform);
		objects.add(new Platform(300, 100));
		//objects.add(block);
		
		for (GameObject o: objects) {
			add(o);
		}
		
		
		//add(game);
		t.start();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new ProjectMain();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_A) {
			keyPressedList[0] = true;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_D) {
			keyPressedList[1] = true;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_W) {
			keyPressedList[2] = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_A) {
			keyPressedList[0] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			keyPressedList[1] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_W) {
			keyPressedList[2] = false;
		}
		
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		boolean moving = false;
		if (keyPressedList[0]) {
			player.accelerate(-3, 0);
			moving = true;
		}
		if (keyPressedList[1]) {
			player.accelerate(3, 0);
			moving = true;
		}
		if (!moving) {
			player.decelerate(3, 0);
		}
		if (keyPressedList[2]) {
			player.jump();
		}
		player.update();
		//game.setLocation(game.getX() + 2, game.getY());

		if (scrolling) {
	//		if (player.getX() > game.getWidth()/2) {
			//	scrolling = false;
		//	}
			//setLocation((int)(Math.random() * 600), (int)(Math.random() * 600));
			int vel = player.getdx(); //limit function calls as player velocity could change during code execution
			if (cameraSide == 1 && player.getdx() < 0) {
				for (GameObject o: objects) {
					o.scroll(-vel);
				}
			}
			else if (cameraSide == 0 && player.getdx() > 0) {
				for (GameObject o: objects) {
					o.scroll(-vel);
				}
			}
			offset += vel;
			if (frame%100 == 0) {
				platform.setLocation(player.getX() + 500, platform.getY());
			}
		}
		if (player.getX() < 50) {
			cameraSide = 1;
			System.out.println("player less than 50");
		}
		else if (player.getX() > 500) {
			cameraSide = 0;
			System.out.println("playaer more than 950");
		}
		else {
			cameraSide = -1;
		}
		System.out.println(player.isColliding(platform));
		System.out.println(player.getHitbox().intersects(platform.getHitbox()));
		frame++;
		
	}
	
	public void scroll(int xVal, int yVal) { { //scrolls the screen slowly to the designated x and y
		
	}
	

}
}
