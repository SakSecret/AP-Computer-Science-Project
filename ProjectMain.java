import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.*;
import java.awt.event.*;

public class ProjectMain extends JFrame implements ActionListener, KeyListener {
	private Timer t = new Timer(30, this);
	private Player player;
	private boolean[] keyPressedList = {false, false, false};
	
	public ProjectMain() {
		setBounds(100, 100, 1000, 500);
		setLayout(null);
		
		
		
		player = new Player(200, 200);
		add(player);
		t.start();
		addKeyListener(this);
		
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
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (keyPressedList[0]) {
			player.setLocation(player.getX() - 2, player.getY());

			
		}
		if (keyPressedList[1]) {
			player.setLocation(player.getX() + 2, player.getY());
			player.jump();
		}
		player.update();
	}
	

}
