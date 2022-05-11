import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.*;
public class ProjectMain extends JFrame{

	public ProjectMain() {
		setBounds(100, 100, 1000, 500);
		setLayout(null);
		
		
		
		
		Player player = new Player(200, 200);
		add(player);
		

		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new ProjectMain();
	}
	
	public void actionPerformed() {
		
	}

}
