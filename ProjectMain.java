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
	/**ã€€if the average computer can't handle loading every single object in the level, either add sections of the level that load or find a way to check if an object should be in a location.
	 * 
	 * 
	 * Useful Notes:
	 * sometimes when changing sprites, both the old and the new sprite will be displayed, to fix this
	 * just slightly move the new sprite, one pixel will do
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
	private JLabel gameOverScreen = new JLabel("Game Over");
	private int coins = 0;
	private JLabel coinCounter = new JLabel ("Coins: 0");
	
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
		objects.add(new Platform(100, 400));
		for (int i = 0; i < 1000; i++) {
			objects.add(new Platform(i*80, 400));
			objects.add(new Coin(i*100, 200));
		}
		objects.add(new Platform(300, 200));
		objects.add(new Goomba(400, 300));
		gameOverScreen.setBounds(0, 0, getWidth(), getHeight());
		gameOverScreen.setFont(new Font("Serif", Font. BOLD, 80));
		gameOverScreen.setVisible(false);
		coinCounter.setBounds(0, 0, 100, 100);
		add(coinCounter);
		add(gameOverScreen);
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
		// TODO reorder the actionPerformed loop later, I don't want to think about it now
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

		//player.update(objects);
		if (keyPressedList[2]) {
			player.jump();
		}
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
			if (frame%30 == 0) {
				platform.setLocation(player.getX(), platform.getY());
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
		System.out.println(player.getHitbox().intersects(objects.get(2).getHitbox()));
		if (frame%1 == 0) {
			if (player.getY() > 1000) {
				gameOverScreen.setVisible(true);
			}
			for (int i = 0; i < objects.size(); i++) { //wtf is this idk, remove later if causes problems
				if (!objects.get(i).isVisible() && objects.get(i) instanceof Enemy) {
					objects.remove(i);
					i--; //if something goes wrong, check this
				}
			}
		}
		
		for (GameObject o: objects) { //remove this if causes problems
			o.update(objects);
		}
		Coin.updateCoinFrame();
		coinCounter.setText("Coins: " + Coin.getCoins());
		frame++;
		
	}
	
	public void scroll(int xVal, int yVal) { { //scrolls the screen slowly to the designated x and y
		
	}
	

}
}
