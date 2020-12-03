package jumpking;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ∏  extends JPanel {
	private ImageIcon bg = new ImageIcon("¡°«¡≈∑¿ÃπÃ¡ˆ/∏ /map1.jpg");
	private Image img = bg.getImage();

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setClip(0, 0, 1800, 1800);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}

}
