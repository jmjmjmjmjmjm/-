package jumpking;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.RepaintManager;

public class Bg extends JPanel {
	static Vector<String> v;
	static JLabel bgla;
	static ImageIcon img;
	static 점프어플 jumapp;
	public Bg() { // 기본적 맵세팅
		v = new Vector<>();
		Dimension res = Toolkit.getDefaultToolkit().getScreenSize(); // 전체 해상도
		v.add("점프킹이미지/맵/1.png");
		v.add("점프킹이미지/맵/2.png");
		v.add("점프킹이미지/맵/3.png");
		v.add("점프킹이미지/맵/4.png");
		v.add("점프킹이미지/맵/5.png");
		img = new ImageIcon(v.get(0));
		이미지사이즈조정(res.width + 500, res.height);
		bgla = new JLabel(img);
		add(bgla);
	}

	static void 이미지변경(int num) { // 맵변경
		img = new ImageIcon(v.get(num));
		bgla.setIcon(img);

	}

	public void 이미지사이즈조정(int width, int height) {
		Image img2 = img.getImage();
		Image changeImg = img2.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		img = new ImageIcon(changeImg);
	}

}