package jumpking;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class ������ extends JPanel {
	ImageIcon bg = new ImageIcon("");
	private Image img = bg.getImage();

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);

	}
}

public class Stage extends JPanel {

	ImageIcon bgs = new ImageIcon("����ŷ�̹���/��/map1.jpg");
	private Image imgs = bgs.getImage();
	int y = 3030;
	int yy = 2024;

	protected void paintComponent(Graphics g) {
		g.drawImage(imgs, 0, 0, 1800, 900, 230, yy, 1800, y, this);
		repaint();
	}

	public Stage() {
		setFocusable(true);
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					y--;
					yy--;
					repaint();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}