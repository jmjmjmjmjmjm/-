package jumpking;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import javax.swing.JPanel;

class 투명도 extends JPanel {
	ImageIcon bg = new ImageIcon("점프킹이미지/맵/투명도.jpg");
	private Image img = bg.getImage();

	protected void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, 1800, 900, 230, 2024, 1800, 3030, this);
		repaint();
	}
}

public class Stage extends JPanel {
	Player player = new Player();
	ImageIcon bgs = new ImageIcon("점프킹이미지/맵/map1.jpg");
	private Image imgs = bgs.getImage();
	int y = 3030;
	int yy = 1824;

	protected void paintComponent(Graphics g) {
		g.drawImage(imgs, 0, 0, 1800, 900, 230, yy, 1800, y, this);
		repaint();
	}

//	public Stage() {
//
//		setFocusable(true);
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				while (true) {
//					if (player.y == 0) {
//						y = y - 1010;
//						yy = y - 608;
//
//						repaint();
//					}
//				}
//			}
//		}).start();
//	}
}
