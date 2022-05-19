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
	
	private Timer t = new Timer(20, this);
	private Player player;
	private Platform platform;
	private boolean[] keyPressedList = {false, false, false};
	private Timer tenFrame;
	private int screenX; //x of the screen in the game world, top left
	private int screenY; //y of the screen in the game world, top left
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private boolean scrolling = false;
	public ProjectMain() {
		setBounds(100, 100, 1000, 500);
		setLayout(null);
		
		addKeyListener(this);
		screenX = 0;
		screenY = 0;
		scrolling = true;
		player = new Player(100, 100);
		platform = new Platform(200, 400);
		
		objects.add(player);
		objects.add(platform);
		
		add(platform);
		add(player);
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
		if (scrolling) {
			if (player.getX() > getWidth()/2) {
				scrolling = false;
			}
			for (GameObject o: objects) {
				o.scroll();
			}
		}
		System.out.println(player.isColliding(platform));

	}
	
	public void scroll(int xVal, int yVal) { { //scrolls the screen slowly to the designated x and y
		
	}
	

}
}
