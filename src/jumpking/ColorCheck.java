package jumpking;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ColorCheck extends JPanel {
	public Player player;
	static Vector<String> checkmap;
	static JLabel bgla;
	static ImageIcon img;
	static 점프어플 jumapp;

	public ColorCheck(Player player) {
		this.player = player;
		checkmap = new Vector<>();
		Dimension res = Toolkit.getDefaultToolkit().getScreenSize(); // 전체 해상도
		checkmap.add("점프킹이미지/맵/1_투명.png");
		checkmap.add("점프킹이미지/맵/2_투명.png");
		checkmap.add("점프킹이미지/맵/3_투명.png");
		checkmap.add("점프킹이미지/맵/4.png");
		checkmap.add("점프킹이미지/맵/5.png");
		img = new ImageIcon(checkmap.get(0));
		이미지사이즈조정(res.width + 500, res.height);
		bgla = new JLabel(img);
		add(bgla);

		new Thread(new Runnable() {

			@Override
			public void run() {
				int num = 0;

				while (true) {
					System.out.println(player.getY());
					if (player.getY() < 1) {
						num++;
						img = new ImageIcon(checkmap.get(num));
						System.out.println("흑백올라감");
					}
					if (player.getY() == res.height) {
						num--;
						img = new ImageIcon(checkmap.get(num));
						System.out.println("흑백내려감");
					}
					
					

				}

			}
		}).start();

	}

	public void 이미지사이즈조정(int width, int height) {
		Image img2 = img.getImage();
		Image changeImg = img2.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		img = new ImageIcon(changeImg);
	}
}
