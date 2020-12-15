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
	static �������� jumapp;

	public ColorCheck(Player player) {
		this.player = player;
		checkmap = new Vector<>();
		Dimension res = Toolkit.getDefaultToolkit().getScreenSize(); // ��ü �ػ�
		checkmap.add("����ŷ�̹���/��/1_����.png");
		checkmap.add("����ŷ�̹���/��/2_����.png");
		checkmap.add("����ŷ�̹���/��/3_����.png");
		checkmap.add("����ŷ�̹���/��/4.png");
		checkmap.add("����ŷ�̹���/��/5.png");
		img = new ImageIcon(checkmap.get(0));
		�̹�������������(res.width + 500, res.height);
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
						System.out.println("���ö�");
					}
					if (player.getY() == res.height) {
						num--;
						img = new ImageIcon(checkmap.get(num));
						System.out.println("��鳻����");
					}
					
					

				}

			}
		}).start();

	}

	public void �̹�������������(int width, int height) {
		Image img2 = img.getImage();
		Image changeImg = img2.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		img = new ImageIcon(changeImg);
	}
}
