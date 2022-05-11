import javax.swing.JComponent;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class Player extends JPanel {
	private JLabel picLabel;
	private BufferedImage myPicture;
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
	}
	

	
};